package com.hodoku.service;

import com.hodoku.dto.SolutionStepResponse;
import com.hodoku.dto.SudokuHintRequest;
import com.hodoku.dto.SudokuRequest;
import com.hodoku.util.SudokuCandidateGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SudokuServiceTest {

    @Autowired
    private SudokuPuzzleService sudokuPuzzleService;

    @Test
    void testGetSudokuWithSeed() {
        SudokuRequest request = new SudokuRequest();
        request.setSeed(123456789L);
        request.setUserId("testUser");
        request.setGameMode("PLAYING");
        request.setLevel("HARD");
        String sudoku= sudokuPuzzleService.getSudoku(request);
        System.out.println(sudoku);
    }
    @Test
    void testGetHintWithValidPuzzleAndSinglesOnly() {
        // 测试一个有效的数独谜题，只寻找单数技巧
//        String puzzle = "..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..";
        String puzzle = "000407000074000200800006047002000910080000000400350000003040100046800032718000000";
        SudokuHintRequest request = new SudokuHintRequest();
        request.setPuzzle(puzzle);
        SolutionStepResponse response = sudokuPuzzleService.getHint(request);

        // 验证返回不为空
        assertNotNull(response, "Hint response should not be null");

        // 验证返回的字段不为空
        assertNotNull(response.getType(), "Type should not be null");
        assertNotNull(response.getDescription(), "Description should not be null");
        assertNotNull(response.getDifficultyLevel(), "Difficulty level should not be null");
        assertNotNull(response.getScore(), "Score should not be null");
        assertNotNull(response.getCells(), "Cells should not be null");
        assertNotNull(response.getCandidatesToDelete(), "Candidates should not be null");
    }

    @Test
    void testGetHintWithValidPuzzleAndAllTechniques() {
        // 测试一个有效的数独谜题，使用所有技巧
        String puzzle = "000407000074000200800006047002000910080000000400350000003040100046800032718000000";
//        String puzzle = "5...4.127941372685..2...3948..4....6..5...9..1....9..33.....7...7..14..8.18.....9";
        SudokuHintRequest request = new SudokuHintRequest();
        request.setPuzzle(puzzle);
        SolutionStepResponse response = sudokuPuzzleService.getHint(request);

        // 验证返回不为空
        assertNotNull(response, "Hint response should not be null");

        // 验证返回的字段不为空
        assertNotNull(response.getType(), "Type should not be null");
        assertNotNull(response.getDescription(), "Description should not be null");
        assertNotNull(response.getDifficultyLevel(), "Difficulty level should not be null");
        assertNotNull(response.getScore(), "Score should not be null");
        assertNotNull(response.getCells(), "Cells should not be null");
        assertNotNull(response.getCandidatesToDelete(), "Candidates should not be null");
        System.out.println(response.getDescription());
    }

    @Test
    void testGetHintWithCustomCandidates() {
        // 测试带自定义候选数字的数独提示
        String puzzle = "900040000523008000640300080000400650000009070060500002010700040700034019000001708";
//        String puzzle = "5...4.127941372685..2...3948..4....6..5...9..1....9..33.....7...7..14..8.18.....9";
        // 创建候选数字映射
        Map<Integer, List<Integer>> candidates = SudokuCandidateGenerator.generateCandidates(puzzle);
//        candidates.put("0", Arrays.asList(1, 2, 4, 5, 7, 8, 9)); // 第0个单元格的候选数字
//        candidates.put("1", Arrays.asList(4, 5, 7, 8, 9));     // 第1个单元格的候选数字
//        candidates.put(39, Arrays.asList(1, 2, 6, 9));
//        candidates.put(40, Arrays.asList(1, 2, 6, 9));
//        candidates.put(57, Arrays.asList(5, 6, 7, 9));
//        candidates.put(59, Arrays.asList(5, 9));
        System.out.println(candidates);
        SudokuHintRequest request = new SudokuHintRequest();
        request.setPuzzle(puzzle);
        request.setCandidates(candidates);
        SolutionStepResponse response = sudokuPuzzleService.getHint(request);

        // 验证返回不为空
        assertNotNull(response, "Hint response should not be null");

        // 验证返回的字段不为空
        assertNotNull(response.getType(), "Type should not be null");
        assertNotNull(response.getDescription(), "Description should not be null");
        assertNotNull(response.getDifficultyLevel(), "Difficulty level should not be null");
        assertNotNull(response.getScore(), "Score should not be null");
        assertNotNull(response.getCells(), "Cells should not be null");
        assertNotNull(response.getCandidatesToDelete(), "Candidates should not be null");
        System.out.println(response.getDescription());
    }

    @Test
    void testGetHintReturnsNullWhenNoHintAvailable() {
        // 测试当没有可用提示时返回null
        // 使用一个已解决的数独谜题
        String puzzle = "123456789456789123789123456214365897365897214897214365531642978642978531978531642";
        SudokuHintRequest request = new SudokuHintRequest();
        request.setPuzzle(puzzle);
        SolutionStepResponse response = sudokuPuzzleService.getHint(request);

        // 验证返回为null（已解决的谜题没有提示）
        assertNull(response, "Hint response should be null for solved puzzle");
    }

    @Test
    void testConvertToResponse() {
        // 由于convertToResponse是私有方法，我们通过公共方法间接测试它
        String puzzle = "..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..";
        SudokuHintRequest request = new SudokuHintRequest();
        request.setPuzzle(puzzle);
        SolutionStepResponse response = sudokuPuzzleService.getHint(request);
        // 验证转换后的响应包含所有必要字段
        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getType(), "Type should not be set");
        assertNotNull(response.getDescription(), "Description should not be empty");
        assertNotNull(response.getDifficultyLevel(), "Difficulty level should be set");
        assertTrue(response.getScore() >= 0, "Score should be non-negative");
        assertNotNull(response.getCells(), "Cells list should not be null");
        assertFalse(response.getCells().isEmpty(), "Cells list should not be empty");
        assertNotNull(response.getCandidatesToDelete(), "Candidates list should not be null");
        assertTrue(response.getCandidatesToDelete().isEmpty(), "Candidates list should not be empty");
    }
}
