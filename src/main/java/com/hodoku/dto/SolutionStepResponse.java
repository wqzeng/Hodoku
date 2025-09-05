package com.hodoku.dto;

import sudoku.Candidate;
import sudoku.Chain;

import java.util.List;

public class SolutionStepResponse {
    private String type;
    private int entity;
    private int entityNumber;
    private int entity2; // für LOCKED_CANDIDATES_X
    private int entity2Number;
    private List<Integer> values;
    private List<Integer> indices;
    private String description;
    private List<Integer> cells;
    private List<Candidate> candidatesToDelete;
    private List<Candidate> fins;
    private List<Chain> chains;
    private String difficultyLevel;
    private Integer score;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getCells() {
        return cells;
    }

    public void setCells(List<Integer> cells) {
        this.cells = cells;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public int getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(int entityNumber) {
        this.entityNumber = entityNumber;
    }

    public int getEntity2() {
        return entity2;
    }

    public void setEntity2(int entity2) {
        this.entity2 = entity2;
    }

    public int getEntity2Number() {
        return entity2Number;
    }

    public void setEntity2Number(int entity2Number) {
        this.entity2Number = entity2Number;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public List<Candidate> getCandidatesToDelete() {
        return candidatesToDelete;
    }

    public void setCandidatesToDelete(List<Candidate> candidatesToDelete) {
        this.candidatesToDelete = candidatesToDelete;
    }

    public List<Candidate> getFins() {
        return fins;
    }

    public void setFins(List<Candidate> fins) {
        this.fins = fins;
    }

    public List<Chain> getChains() {
        return chains;
    }

    public void setChains(List<Chain> chains) {
        this.chains = chains;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
