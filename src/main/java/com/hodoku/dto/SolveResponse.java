package com.hodoku.dto;

import java.util.List;

public class SolveResponse {
    private List<SolutionStepResponse> steps;
    private Boolean isSolved;
    private String difficultyLevel;
    private Integer totalScore;
    private String solution;

    // Getters and Setters
    public List<SolutionStepResponse> getSteps() {
        return steps;
    }

    public void setSteps(List<SolutionStepResponse> steps) {
        this.steps = steps;
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}