package com.hodoku.service;

import com.hodoku.dto.Sudoku;
import com.hodoku.mapper.SudokuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数独游戏进度服务类
 */
@Service
public class SudokuService {

    @Autowired
    private SudokuMapper sudokuMapper;

    /**
     * 创建数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 创建的数独游戏进度
     */
    public Sudoku createSudoku(Sudoku sudoku) {
        Sudoku existSudoku=sudokuMapper.findByIndex(sudoku.getOpenId(), sudoku.getGameMode(), sudoku.getDifficulty(), sudoku.getIndex());
        if(existSudoku!=null){
            sudoku.setId(existSudoku.getId());
            sudokuMapper.updateSudoku(sudoku);
        }else{
            sudokuMapper.insertSudoku(sudoku);
        }
        return sudoku;
    }

    /**
     * 保存所有数独游戏进度
     * @param sudokuList 数独游戏进度
     * @return 布尔值
     */
    public boolean saveSudoku(String openId,List<Sudoku> sudokuList) {
        if(CollectionUtils.isEmpty(sudokuList)){
            return false;
        }
        List<Sudoku> existSudokuList=sudokuMapper.findByOpenId(openId);
        //existSudokuList按gameMode, difficulty, index为组合键转换成map
        Map<String, Sudoku> existSudokuMap = existSudokuList.stream().collect(Collectors.toMap(sudoku -> sudoku.getGameMode() + "_" + sudoku.getDifficulty() + "_" + sudoku.getIndex(), sudoku -> sudoku));
        for (Sudoku sudoku : sudokuList){
            String key = sudoku.getGameMode() + "_" + sudoku.getDifficulty() + "_" + sudoku.getIndex();
            if (existSudokuMap.containsKey(key)) {
                sudoku.setId(existSudokuMap.get(key).getId());
                sudokuMapper.updateSudoku(sudoku);
            }else{
                sudoku.setOpenId(openId);
                sudokuMapper.insertSudoku(sudoku);
            }
        }
        return true;
    }

    /**
     * 根据ID获取数独游戏进度
     * @param id 游戏进度ID
     * @return 数独游戏进度
     */
    public Sudoku getSudokuById(Long id) {
        return sudokuMapper.findById(id);
    }

    /**
     * 根据openId获取数独游戏进度列表
     * @param openId 账号
     * @return 数独游戏进度列表
     */
    public List<Sudoku> getSudokuByOpenId(String openId) {
        return sudokuMapper.findByOpenId(openId);
    }

    /**
     * 更新数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 更新后的数独游戏进度
     */
    public Sudoku updateSudoku(Sudoku sudoku) {
        sudokuMapper.updateSudoku(sudoku);
        return sudoku;
    }

    /**
     * 删除数独游戏进度
     * @param id 游戏进度ID
     * @return 是否删除成功
     */
    public boolean deleteSudoku(Long id) {
        return sudokuMapper.deleteSudoku(id) > 0;
    }

    /**
     * 获取不同模式数独游戏进度
     * @return 数独游戏进度列表
     */
    public List<Sudoku> getSudokuByGameMode(String openId,String gameMode) {
        return sudokuMapper.findByGameMode(openId, gameMode);
    }
}
