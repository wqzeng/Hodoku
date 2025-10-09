package com.hodoku.controller;

import com.hodoku.dto.Sudoku;
import com.hodoku.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数独游戏进度控制器
 */
@RestController
@RequestMapping("/api/sudoku-progress")
@CrossOrigin(origins = "*")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    /**
     * 创建数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 创建的数独游戏进度
     */
    @PostMapping("/create")
    public ResponseEntity<Sudoku> createSudoku(@RequestHeader Map<String, String> headers,@RequestBody Sudoku sudoku) {
        String openId = headers.get("x-wx-openid");
        sudoku.setOpenId(openId);
        Sudoku createdSudoku = sudokuService.createSudoku(sudoku);
        return ResponseEntity.ok(createdSudoku);
    }
    /**
     * 保存数独游戏进度
     * @param sudoku 数独游戏进度
     * @return 创建的数独游戏进度
     */
    @PostMapping("/save")
    public ResponseEntity<Boolean> saveSudoku(@RequestHeader Map<String, String> headers,@RequestBody List<Sudoku> sudokuList) {
        String openId = headers.get("x-wx-openid");
        boolean result = sudokuService.saveSudoku(openId,sudokuList);
        return ResponseEntity.ok(result);
    }
    /**
     * 根据ID获取数独游戏进度
     * @param id 游戏进度ID
     * @return 数独游戏进度
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sudoku> getSudokuById(@PathVariable Long id) {
        Sudoku sudoku = sudokuService.getSudokuById(id);
        if (sudoku == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sudoku);
    }

    /**
     * 根据openId获取数独游戏进度列表
     * @param openId 账号
     * @return 数独游戏进度列表
     */
    @GetMapping("/user/{openId}")
    public ResponseEntity<List<Sudoku>> getSudokuByOpenId(@PathVariable String openId) {
        List<Sudoku> sudokuList = sudokuService.getSudokuByOpenId(openId);
        return ResponseEntity.ok(sudokuList);
    }

    /**
     * 更新数独游戏进度
     * @param id 游戏进度ID
     * @param sudoku 数独游戏进度
     * @return 更新后的数独游戏进度
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sudoku> updateSudoku(@PathVariable Long id, @RequestBody Sudoku sudoku) {
        sudoku.setId(id);
        Sudoku updatedSudoku = sudokuService.updateSudoku(sudoku);
        return ResponseEntity.ok(updatedSudoku);
    }

    /**
     * 删除数独游戏进度
     * @param id 游戏进度ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSudoku(@PathVariable Long id) {
        boolean deleted = sudokuService.deleteSudoku(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 获取所有数独游戏进度
     * @return 数独游戏进度列表
     */
    @GetMapping("/get/{gameMode}")
    public ResponseEntity<List<Sudoku>> getSudokuByGameMode(@RequestHeader Map<String, String> headers,@PathVariable String gameMode) {
        String openId = headers.get("x-wx-openid");
        List<Sudoku> sudokuList = sudokuService.getSudokuByGameMode(openId, gameMode);
        return ResponseEntity.ok(sudokuList);
    }
}
