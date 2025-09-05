package com.hodoku.dto;

public class SudokuSolveRequest {
    private String puzzle;
    private String maxDifficulty;

    // Getters and Setters
    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getMaxDifficulty() {
        return maxDifficulty;
    }

    public void setMaxDifficulty(String maxDifficulty) {
        this.maxDifficulty = maxDifficulty;
    }
}