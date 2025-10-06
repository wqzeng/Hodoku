package com.hodoku.service;

import com.hodoku.dto.SudokuPuzzle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * 数独数据库服务测试类
 */
@SpringBootTest
@ActiveProfiles("test")
public class SudokuDatabaseServiceTest {
    
    @Autowired
    private SudokuDatabaseService sudokuDatabaseService;
    
    @Test
    public void testSavePuzzle() {
        // 测试保存数独题目
        String puzzle = "530070000600195000098000060800060003400803001700020006060000280000419005000080079";
        String solution = "534678912672195348198342567859761423426853791713924856961537284287419635345286179";
        
        SudokuPuzzle saved = sudokuDatabaseService.savePuzzle(puzzle, solution, 2, "测试题目");
        
        assert saved != null;
        assert saved.getId() != null;
        System.out.println("保存成功，题目ID: " + saved.getId());
    }
    
    @Test
    public void testGetPuzzlesByLevel() {
        // 测试根据难度获取题目
        List<SudokuPuzzle> puzzles = sudokuDatabaseService.getPuzzlesByLevel(2);
        
        assert puzzles != null;
        System.out.println("找到 " + puzzles.size() + " 个中等难度题目");
        
        for (SudokuPuzzle puzzle : puzzles) {
            System.out.println("题目ID: " + puzzle.getId() + ", 描述: " + puzzle.getDescription());
        }
    }
    
    @Test
    public void testGetPuzzleById() {
        // 测试根据ID获取题目
        SudokuPuzzle puzzle = sudokuDatabaseService.getPuzzleById(1L);
        
        if (puzzle != null) {
            System.out.println("找到题目: " + puzzle.getDescription());
            System.out.println("难度级别: " + puzzle.getLevel());
        } else {
            System.out.println("未找到ID为1的题目");
        }
    }
}