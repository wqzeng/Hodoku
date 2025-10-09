package com.hodoku.dto;

import java.util.Date;

/**
 * 数独游戏进度实体类
 */
public class Sudoku {
    private Long id;
    private String openId;          // 账号
    private String gameMode;        // 模式
    private String difficulty;      // 难度
    private String index;           // 关卡
    private String puzzle;          // 谜题
    private String candidates;      // 候选数
    private Date timeElapsed;       // 已用时间
    private Integer status;         // 状态
    private Integer missCount;      // 错误次数
    private Integer hintCount;      // 提示次数
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

    // 默认构造函数
    public Sudoku() {}

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

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getCandidates() {
        return candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }

    public Date getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Date timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMissCount() {
        return missCount;
    }

    public void setMissCount(Integer missCount) {
        this.missCount = missCount;
    }

    public Integer getHintCount() {
        return hintCount;
    }

    public void setHintCount(Integer hintCount) {
        this.hintCount = hintCount;
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
        return "Sudoku{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", index='" + index + '\'' +
                ", puzzle='" + puzzle + '\'' +
                ", candidates='" + candidates + '\'' +
                ", timeElapsed=" + timeElapsed +
                ", status=" + status +
                ", missCount=" + missCount +
                ", hintCount=" + hintCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
