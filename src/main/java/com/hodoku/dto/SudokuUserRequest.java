package com.hodoku.dto;

public class SudokuUserRequest {
    private String nickName;
    private Integer score;
    private String levels;
    private String streakInfo;
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        return streakInfo;
    }

    public void setStreakInfo(String streakInfo) {
        this.streakInfo = streakInfo;
    }
}
