package com.hodoku.service;

import com.hodoku.dto.SudokuUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 数独用户服务测试类
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SudokuUserServiceTest {

    @Autowired
    private SudokuUserService sudokuUserService;

    @Test
    public void testCreateUser() {
        // 测试创建用户
        SudokuUser user = sudokuUserService.createUser(
            "test_openid_001",
            "测试用户1",
            "微信小程序"
        );

        assert user != null;
        assert user.getId() != null;
        System.out.println("创建用户成功，ID: " + user.getId());
        System.out.println("用户信息: " + user);
    }

    @Test
    public void testGetUserByOpenId() {
        // 先创建一个用户
        sudokuUserService.createUser("test_openid_002", "测试用户2", "APP");

        // 测试根据openId查询用户
        SudokuUser user = sudokuUserService.getUserByOpenId("test_openid_002");
        assert user != null;
        assert "测试用户2".equals(user.getUserName());
        System.out.println("查询用户成功: " + user);
    }

    @Test
    public void testUpdateUser() {
        // 先创建一个用户
        SudokuUser user = sudokuUserService.createUser(
            "test_openid_003",
            "测试用户3",
            "网页版"
        );

        // 更新用户信息
        user.setUserName("更新后的用户名");
        user.setPlatform("移动端");
        boolean result = sudokuUserService.updateUser(user);

        assert result;

        // 验证更新结果
        SudokuUser updatedUser = sudokuUserService.getUserByOpenId("test_openid_003");
        assert "更新后的用户名".equals(updatedUser.getUserName());
        assert "移动端".equals(updatedUser.getPlatform());
        System.out.println("更新用户成功: " + updatedUser);
    }

    @Test
    public void testUserLogin() {
        // 先创建一个用户
        sudokuUserService.createUser("test_openid_004", "测试用户4", "APP");

        // 测试用户登录
        boolean result = sudokuUserService.userLogin("test_openid_004", "192.168.1.100");
        assert result;

        // 验证登录信息更新
        SudokuUser user = sudokuUserService.getUserByOpenId("test_openid_004");
        assert "192.168.1.100".equals(user.getLoginIp());
        assert user.getLoginTimes() > 0;
        System.out.println("用户登录测试成功: " + user);
    }

    @Test
    public void testGetUsersByPlatform() {
        // 创建多个不同平台的用户
        sudokuUserService.createUser("user_wx_001", "微信用户1", "微信小程序");
        sudokuUserService.createUser("user_wx_002", "微信用户2", "微信小程序");
        sudokuUserService.createUser("user_app_001", "APP用户1", "APP");

        // 测试根据平台查询
        List<SudokuUser> wxUsers = sudokuUserService.getUsersByPlatform("微信小程序");
        assert wxUsers.size() >= 2;
        System.out.println("微信小程序用户数量: " + wxUsers.size());

        List<SudokuUser> appUsers = sudokuUserService.getUsersByPlatform("APP");
        assert appUsers.size() >= 1;
        System.out.println("APP用户数量: " + appUsers.size());
    }

    @Test
    public void testSearchUsersByName() {
        // 创建测试用户
        sudokuUserService.createUser("search_001", "张三", "APP");
        sudokuUserService.createUser("search_002", "张四", "APP");
        sudokuUserService.createUser("search_003", "李五", "APP");

        // 测试模糊搜索
        List<SudokuUser> users = sudokuUserService.searchUsersByName("张");
        assert users.size() >= 2;
        System.out.println("搜索'张'的结果数量: " + users.size());

        for (SudokuUser user : users) {
            assert user.getUserName().contains("张");
            System.out.println("搜索结果: " + user.getUserName());
        }
    }

    @Test
    public void testBatchOperations() {
        // 测试批量创建
        List<SudokuUser> users = Arrays.asList(
            new SudokuUser("batch_001", "批量用户1", "测试平台"),
            new SudokuUser("batch_002", "批量用户2", "测试平台"),
            new SudokuUser("batch_003", "批量用户3", "测试平台")
        );

        int createCount = sudokuUserService.createUsersBatch(users);
        assert createCount == 3;
        System.out.println("批量创建用户数量: " + createCount);

        // 验证创建结果
        long platformUserCount = sudokuUserService.getUserCountByPlatform("测试平台");
        assert platformUserCount >= 3;
        System.out.println("测试平台用户总数: " + platformUserCount);
    }

    @Test
    public void testDeleteOperations() {
        // 创建测试用户
        SudokuUser user = sudokuUserService.createUser("delete_test", "待删除用户", "删除测试");
        assert user != null;
        Long userId = user.getId();

        // 测试根据ID删除
        boolean deleteResult = sudokuUserService.deleteUser(userId);
        assert deleteResult;

        // 验证删除结果
        SudokuUser deletedUser = sudokuUserService.getUserById(userId);
        assert deletedUser == null;
        System.out.println("删除用户测试成功");
    }

    @Test
    public void testPagination() {
        // 创建多个测试用户
        for (int i = 1; i <= 15; i++) {
            sudokuUserService.createUser("page_test_" + i, "分页测试用户" + i, "分页测试");
        }

        // 测试分页查询
        List<SudokuUser> page1 = sudokuUserService.getUsersByPage(1, 5);
        List<SudokuUser> page2 = sudokuUserService.getUsersByPage(2, 5);

        assert page1.size() == 5;
        assert page2.size() == 5;

        System.out.println("第1页用户数量: " + page1.size());
        System.out.println("第2页用户数量: " + page2.size());

        // 测试总数统计
        long totalCount = sudokuUserService.getTotalUserCount();
        System.out.println("用户总数: " + totalCount);
        assert totalCount >= 15;
    }
}
