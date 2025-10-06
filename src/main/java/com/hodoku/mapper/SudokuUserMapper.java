package com.hodoku.mapper;

import com.hodoku.dto.SudokuUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数独用户数据库访问接口
 */
@Mapper
public interface SudokuUserMapper {
    
    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    SudokuUser findById(@Param("id") Long id);
    
    /**
     * 根据openId查询用户
     * @param openId 账号
     * @return 用户信息
     */
    SudokuUser findByOpenId(@Param("openId") String openId);
    
    /**
     * 根据平台查询用户列表
     * @param platform 平台
     * @return 用户列表
     */
    List<SudokuUser> findByPlatform(@Param("platform") String platform);
    
    /**
     * 根据用户名模糊查询
     * @param userName 用户名关键字
     * @return 用户列表
     */
    List<SudokuUser> findByUserNameLike(@Param("userName") String userName);
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<SudokuUser> findAll();
    
    /**
     * 分页查询用户
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 用户列表
     */
    List<SudokuUser> findByPage(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 查询用户总数
     * @return 用户总数
     */
    long countAll();
    
    /**
     * 根据平台查询用户数量
     * @param platform 平台
     * @return 用户数量
     */
    long countByPlatform(@Param("platform") String platform);
    
    /**
     * 新增用户
     * @param user 用户信息
     * @return 影响行数
     */
    int insert(SudokuUser user);
    
    /**
     * 批量新增用户
     * @param users 用户列表
     * @return 影响行数
     */
    int insertBatch(@Param("users") List<SudokuUser> users);
    
    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 影响行数
     */
    int update(SudokuUser user);
    
    /**
     * 选择性更新用户信息（只更新非空字段）
     * @param user 用户信息
     * @return 影响行数
     */
    int updateSelective(SudokuUser user);
    
    /**
     * 更新用户登录信息
     * @param openId 账号
     * @param loginIp 登录IP
     * @return 影响行数
     */
    int updateLoginInfo(@Param("openId") String openId, @Param("loginIp") String loginIp);
    
    /**
     * 增加登录次数
     * @param openId 账号
     * @return 影响行数
     */
    int incrementLoginTimes(@Param("openId") String openId);
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据openId删除用户
     * @param openId 账号
     * @return 影响行数
     */
    int deleteByOpenId(@Param("openId") String openId);
    
    /**
     * 批量删除用户
     * @param ids 用户ID列表
     * @return 影响行数
     */
    int deleteBatch(@Param("ids") List<Long> ids);
    
    /**
     * 根据平台删除用户
     * @param platform 平台
     * @return 影响行数
     */
    int deleteByPlatform(@Param("platform") String platform);
}