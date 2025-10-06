package com.hodoku.dto;

import java.util.Date;

/**
 * 数独用户数据传输对象
 */
public class SudokuUser {

    private Long id;
    private String openId;
    private String userName;
    private String platform;
    private String loginIp;
    private Integer loginTimes;
    private Integer score;
    private String levels;
    private String StreakInfo;
    private Date createTime;
    private Date updateTime;

    // 默认构造函数
    public SudokuUser() {}

    // 带参构造函数
    public SudokuUser(String openId, String userName, String platform) {
        this.openId = openId;
        this.userName = userName;
        this.platform = platform;
        this.loginTimes = 0;
    }

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getStreakInfo() {
        return StreakInfo;
    }

    public void setStreakInfo(String streakInfo) {
        StreakInfo = streakInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SudokuUser{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", userName='" + userName + '\'' +
                ", platform='" + platform + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", score='" + score + '\'' +
                ", loginTimes=" + loginTimes +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
