package com.hodoku.dto;

import java.util.List;
import java.util.Map;

public class SudokuHintRequest {
    private String userId;
    private String puzzle;
    private String sodokuMode;
    private String levelIndex;
    private List<String> techniqueSet;
    private Map<Integer, List<Integer>> candidates; // cellIndex -> candidate values

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getSodokuMode() {
        return sodokuMode;
    }

    public void setSodokuMode(String sodokuMode) {
        this.sodokuMode = sodokuMode;
    }

    public String getLevelIndex() {
        return levelIndex;
    }

    public void setLevelIndex(String levelIndex) {
        this.levelIndex = levelIndex;
    }

    public List<String> getTechniqueSet() {
        return techniqueSet;
    }

    public void setTechniqueSet(List<String> techniqueSet) {
        this.techniqueSet = techniqueSet;
    }

    public Map<Integer, List<Integer>> getCandidates() {
        return candidates;
    }

    public void setCandidates(Map<Integer, List<Integer>> candidates) {
        this.candidates = candidates;
    }
}
