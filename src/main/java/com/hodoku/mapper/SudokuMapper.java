package com.hodoku.mapper;

import com.hodoku.dto.Sudoku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数独游戏进度数据库访问接口
 */
@Mapper
public interface SudokuMapper {

    /**
     * 插入数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 影响行数
     */
    int insertSudoku(Sudoku sudoku);

    /**
     * 根据ID查询数独游戏进度
     * @param id 游戏进度ID
     * @return 数独游戏进度
     */
    Sudoku findById(@Param("id") Long id);

    Sudoku findByIndex(@Param("openId") String openId,@Param("gameMode") String gameMode,@Param("difficulty") String difficulty,
                         @Param("index") String index);

    /**
     * 根据openId查询数独游戏进度列表
     * @param openId 账号
     * @return 数独游戏进度列表
     */
    List<Sudoku> findByOpenId(@Param("openId") String openId);

    /**
     * 更新数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 影响行数
     */
    int updateSudoku(Sudoku sudoku);

    /**
     * 根据ID删除数独游戏进度
     * @param id 游戏进度ID
     * @return 影响行数
     */
    int deleteSudoku(@Param("id") Long id);

    /**
     * 查询不同模式数独游戏进度
     * @return 数独游戏进度列表
     */
    List<Sudoku> findByGameMode(@Param("openId")String openId, @Param("gameMode")String gameMode);
}
