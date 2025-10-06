package com.hodoku.dto;

import java.util.Date;

/**
 * 数独题目数据传输对象
 */
public class SudokuPuzzle {

    private Long id;
    private String puzzle;          // 数独题目字符串
    private String solution;        // 数独解答字符串
    private Integer level;          // 难度级别
    private String description;     // 描述
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

    // 默认构造函数
    public SudokuPuzzle() {}

    // 带参构造函数
    public SudokuPuzzle(String puzzle, String solution, Integer level) {
        this.puzzle = puzzle;
        this.solution = solution;
        this.level = level;
    }

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "SudokuPuzzle{" +
                "id=" + id +
                ", puzzle='" + puzzle + '\'' +
                ", solution='" + solution + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
