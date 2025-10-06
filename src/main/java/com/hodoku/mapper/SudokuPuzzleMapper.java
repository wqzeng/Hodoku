package com.hodoku.mapper;

import com.hodoku.dto.SudokuPuzzle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数独题目数据库访问接口
 */
@Mapper
public interface SudokuPuzzleMapper {

    /**
     * 根据难度级别查询数独题目
     * @param level 难度级别
     * @return 数独题目列表
     */
    List<SudokuPuzzle> findByLevel(@Param("level") Integer level);

    /**
     * 保存数独题目
     * @param puzzle 数独题目
     * @return 影响行数
     */
    int savePuzzle(SudokuPuzzle puzzle);

    /**
     * 根据ID查询数独题目
     * @param id 题目ID
     * @return 数独题目
     */
    SudokuPuzzle findById(@Param("id") Long id);

    /**
     * 更新数独题目
     * @param puzzle 数独题目
     * @return 影响行数
     */
    int updatePuzzle(SudokuPuzzle puzzle);

    /**
     * 删除数独题目
     * @param id 题目ID
     * @return 影响行数
     */
    int deletePuzzle(@Param("id") Long id);
}
