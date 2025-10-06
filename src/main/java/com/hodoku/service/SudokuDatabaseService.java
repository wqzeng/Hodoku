package com.hodoku.service;

import com.hodoku.dto.SudokuPuzzle;
import com.hodoku.mapper.SudokuPuzzleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数独数据库服务类
 */
@Service
public class SudokuDatabaseService {

    @Autowired
    private SudokuPuzzleMapper sudokuPuzzleMapper;

    /**
     * 根据难度级别获取数独题目
     * @param level 难度级别 (1-简单, 2-中等, 3-困难, 4-专家)
     * @return 数独题目列表
     */
    public List<SudokuPuzzle> getPuzzlesByLevel(Integer level) {
        return sudokuPuzzleMapper.findByLevel(level);
    }

    /**
     * 保存数独题目到数据库
     * @param puzzleStr 数独题目字符串
     * @param solutionStr 数独解答字符串
     * @param level 难度级别
     * @param description 描述
     * @return 保存的题目对象
     */
    public SudokuPuzzle savePuzzle(String puzzleStr, String solutionStr, Integer level, String description) {
        SudokuPuzzle puzzle = new SudokuPuzzle(puzzleStr, solutionStr, level);
        puzzle.setDescription(description);

        int result = sudokuPuzzleMapper.savePuzzle(puzzle);
        if (result > 0) {
            return puzzle;
        }
        return null;
    }

    /**
     * 根据ID获取数独题目
     * @param id 题目ID
     * @return 数独题目
     */
    public SudokuPuzzle getPuzzleById(Long id) {
        return sudokuPuzzleMapper.findById(id);
    }

    /**
     * 更新数独题目
     * @param puzzle 数独题目对象
     * @return 是否更新成功
     */
    public boolean updatePuzzle(SudokuPuzzle puzzle) {
        return sudokuPuzzleMapper.updatePuzzle(puzzle) > 0;
    }

    /**
     * 删除数独题目
     * @param id 题目ID
     * @return 是否删除成功
     */
    public boolean deletePuzzle(Long id) {
        return sudokuPuzzleMapper.deletePuzzle(id) > 0;
    }
}
