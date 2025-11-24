package com.hodoku.controller;

import com.hodoku.dto.*;
import com.hodoku.service.SudokuPuzzleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin(origins = "*")
public class SudokuPuzzleController {
    private final static Logger logger = LoggerFactory.getLogger(SudokuPuzzleController.class);
    @Autowired
    private SudokuPuzzleService sudokuPuzzleService;

    @PostMapping("/get")
    public ResponseEntity<String> getSudoku(@RequestHeader Map<String, String> headers, @RequestBody SudokuRequest request) {
        //获取请求头Header
        String userId = headers.get("x-userid");
        request.setUserId(userId);
        logger.info("headers={}", headers);
        String sudoku = sudokuPuzzleService.getSudoku(request);

        if (sudoku == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sudoku);
    }
    @PostMapping("/hint")
    public ResponseEntity<SolutionStepResponse> getHint(@RequestHeader Map<String, String> headers, @RequestBody SudokuHintRequest request) {
        //获取请求头Header
        String userId = headers.get("x-userid");
        request.setUserId(userId);
        logger.info("headers={}", headers);
        SolutionStepResponse hint = sudokuPuzzleService.getHint(request);

        if (hint == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(hint);
    }

    @PostMapping("/solve")
    public ResponseEntity<SolveResponse> solve(@RequestBody SudokuSolveRequest request) {
        SolveResponse solution = sudokuPuzzleService.solve(request.getPuzzle());
        return ResponseEntity.ok(solution);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Hodoku API is running");
    }
}
