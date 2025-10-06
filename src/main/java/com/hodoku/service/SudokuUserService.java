package com.hodoku.service;

import com.hodoku.dto.SudokuUser;
import com.hodoku.dto.SudokuUserRequest;
import com.hodoku.mapper.SudokuUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 数独用户服务类
 */
@Service
public class SudokuUserService {
    private final static Logger logger = LoggerFactory.getLogger(SudokuUserService.class);

    @Autowired
    private SudokuUserMapper sudokuUserMapper;

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    public SudokuUser getUserById(Long id) {
        return sudokuUserMapper.findById(id);
    }

    /**
     * 根据openId查询用户
     * @param openId 账号
     * @return 用户信息
     */
    public SudokuUser getUserByOpenId(String openId) {
        return sudokuUserMapper.findByOpenId(openId);
    }

    /**
     * 根据平台查询用户列表
     * @param platform 平台
     * @return 用户列表
     */
    public List<SudokuUser> getUsersByPlatform(String platform) {
        return sudokuUserMapper.findByPlatform(platform);
    }

    /**
     * 根据用户名模糊查询
     * @param userName 用户名关键字
     * @return 用户列表
     */
    public List<SudokuUser> searchUsersByName(String userName) {
        return sudokuUserMapper.findByUserNameLike(userName);
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    public List<SudokuUser> getAllUsers() {
        return sudokuUserMapper.findAll();
    }

    /**
     * 分页查询用户
     * @param page 页码（从1开始）
     * @param size 每页大小
     * @return 用户列表
     */
    public List<SudokuUser> getUsersByPage(int page, int size) {
        int offset = (page - 1) * size;
        return sudokuUserMapper.findByPage(offset, size);
    }

    /**
     * 查询用户总数
     * @return 用户总数
     */
    public long getTotalUserCount() {
        return sudokuUserMapper.countAll();
    }

    /**
     * 根据平台查询用户数量
     * @param platform 平台
     * @return 用户数量
     */
    public long getUserCountByPlatform(String platform) {
        return sudokuUserMapper.countByPlatform(platform);
    }

    /**
     * 创建新用户
     * @param openId 账号
     * @param userName 用户名
     * @param platform 平台
     * @return 创建的用户信息
     */
    @Transactional
    public SudokuUser createUser(String openId, String userName, String platform) {
        // 检查用户是否已存在
        SudokuUser existingUser = sudokuUserMapper.findByOpenId(openId);
        if (existingUser != null) {
            throw new IllegalArgumentException("用户已存在: " + openId);
        }

        SudokuUser user = new SudokuUser(openId, userName, platform);
        int result = sudokuUserMapper.insert(user);
        if (result > 0) {
            return user;
        }
        return null;
    }

    /**
     * 批量创建用户
     * @param users 用户列表
     * @return 成功创建的数量
     */
    @Transactional
    public int createUsersBatch(List<SudokuUser> users) {
        return sudokuUserMapper.insertBatch(users);
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 是否更新成功
     */
    @Transactional
    public boolean updateUser(SudokuUser user) {
        return sudokuUserMapper.update(user) > 0;
    }

    /**
     * 选择性更新用户信息
     * @param user 用户信息
     * @return 是否更新成功
     */
    @Transactional
    public boolean updateUserSelective(SudokuUser user) {
        return sudokuUserMapper.updateSelective(user) > 0;
    }

    /**
     * 用户登录，更新登录信息
     * @param openId 账号
     * @param loginIp 登录IP
     * @return 是否更新成功
     */
    @Transactional
    public boolean userLogin(String openId, String loginIp) {
        return sudokuUserMapper.updateLoginInfo(openId, loginIp) > 0;
    }

    /**
     * 增加用户登录次数
     * @param openId 账号
     * @return 是否更新成功
     */
    @Transactional
    public boolean incrementUserLoginTimes(String openId) {
        return sudokuUserMapper.incrementLoginTimes(openId) > 0;
    }

    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    @Transactional
    public boolean deleteUser(Long id) {
        return sudokuUserMapper.deleteById(id) > 0;
    }

    /**
     * 根据openId删除用户
     * @param openId 账号
     * @return 是否删除成功
     */
    @Transactional
    public boolean deleteUserByOpenId(String openId) {
        return sudokuUserMapper.deleteByOpenId(openId) > 0;
    }

    /**
     * 批量删除用户
     * @param ids 用户ID列表
     * @return 成功删除的数量
     */
    @Transactional
    public int deleteUsersBatch(List<Long> ids) {
        return sudokuUserMapper.deleteBatch(ids);
    }

    /**
     * 根据平台删除用户
     * @param platform 平台
     * @return 成功删除的数量
     */
    @Transactional
    public int deleteUsersByPlatform(String platform) {
        return sudokuUserMapper.deleteByPlatform(platform);
    }

    /**
     * 用户注册或登录（如果用户不存在则创建，存在则更新登录信息）
     * @param openId 账号
     * @param platform 平台
     * @param loginIp 登录IP
     * @return 用户信息
     */
    @Transactional
    public SudokuUser registerOrLogin(SudokuUserRequest request, String openId, String platform, String loginIp) {
        SudokuUser user = sudokuUserMapper.findByOpenId(openId);
        if (user == null) {
            // 用户不存在，创建新用户
            logger.info("用户不存在，新增登录信息");
            user = new SudokuUser(openId, request.getNickName(), platform);
            user.setLoginTimes(1);
            user.setLoginIp(loginIp);
            user.setScore(request.getScore());
            user.setLevels(request.getLevels());
            user.setStreakInfo(request.getStreakInfo());
            sudokuUserMapper.insert(user);
        } else {
            // 用户存在，更新登录次数
            logger.info("用户已存在，更新登录信息");
            user.setUserName(request.getNickName());
            user.setPlatform(platform);
            user.setLoginTimes(user.getLoginTimes() + 1);
            user.setLoginIp(loginIp);
            user.setScore(request.getScore());
            user.setLevels(request.getLevels());
            user.setStreakInfo(request.getStreakInfo());
            sudokuUserMapper.update(user);
        }
        return user;
    }
}
