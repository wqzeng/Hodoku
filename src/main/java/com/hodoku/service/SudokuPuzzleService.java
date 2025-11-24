package com.hodoku.service;

import com.hodoku.dto.SolutionStepResponse;
import com.hodoku.dto.SolveResponse;
import com.hodoku.dto.SudokuHintRequest;
import com.hodoku.dto.SudokuRequest;
import generator.BackgroundGenerator;
import generator.BackgroundGeneratorThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import solver.SudokuSolver;
import solver.SudokuSolverFactory;
import sudoku.*;
import sudoku.Options;
import sudoku.DifficultyType;
import sudoku.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SudokuPuzzleService {
    private final static Logger logger = LoggerFactory.getLogger(SudokuPuzzleService.class);

    public String getSudoku(SudokuRequest request) {
        GameMode mode = GameMode.valueOf(request.getGameMode());
        DifficultyLevel difficultyLevel = new DifficultyLevel(DifficultyType.valueOf(request.getLevel()), 0, "", null, null);
        String sudoku =BackgroundGeneratorThread.getInstance().getSudoku(difficultyLevel, mode);
//        BackgroundGenerator generator = new BackgroundGenerator();
//        if(request.getSeed() != null){
//            generator.setSeed(request.getSeed());
//        }
//        String sudoku =generator.generate(difficultyLevel, mode);
        logger.info("{} Getting puzzle,gameMode: {},level:{}: puzzle:{}", request.getUserId(),request.getGameMode(),request.getLevel(), sudoku);
        return sudoku;
    }

    public SolutionStepResponse getHint(SudokuHintRequest request) {
        String puzzle = request.getPuzzle();
        Map<Integer, List<Integer>> candidates = request.getCandidates();
        // Validate input puzzle
        if (puzzle == null || puzzle.trim().isEmpty()) {
            throw new IllegalArgumentException("Puzzle cannot be null or empty");
        }

        Sudoku2 sudoku = new Sudoku2();
        sudoku.setSudoku(puzzle);

        // If candidates are provided, update the sudoku state with the current candidates
        if (candidates != null && !candidates.isEmpty()) {
            // First, reset all candidates
            sudoku.resetCandidates();

            // Then set only the provided candidates
            for (Map.Entry<Integer, List<Integer>> entry : candidates.entrySet()) {
                int cellIndex = entry.getKey();

                // Clear all candidates for this cell first
                for (int candidate = 1; candidate <= 9; candidate++) {
                    sudoku.setCandidate(cellIndex, candidate, false);
                }

                List<Integer> cellCandidates = entry.getValue();

                // Set the provided candidates after validation
                for (Integer candidate : cellCandidates) {
                    // Validate candidate value
                    if (candidate != null && candidate >= 1 && candidate <= 9) {
                        sudoku.setCandidate(cellIndex, candidate, true);
                    }
                }
            }

            // Validate the sudoku state after setting candidates
            sudoku.checkSudoku();
        }

        // Get solver instance from factory for better resource management
        SudokuSolver solver = SudokuSolverFactory.getDefaultSolverInstance();
        solver.setSudoku(sudoku.clone());
        solver.solve();
        SolutionStep step = solver.getHint(sudoku, false);
        if (step == null) {
            logger.error("{} Getting hint for puzzle: {},mode:{},levelIndex:{},candidates:{},Hint step is null",request.getUserId(),puzzle,request.getSodokuMode(),request.getLevelIndex(),candidates);
            return null;
        }
        logger.info("{} Getting hint for puzzle: {},mode:{},levelIndex:{},candidates:{},Hint step: {},values:{},indices:{},candidate:{},candidates2Del:{},fins:{},endoFins:{},chains:{},chainLength:{}," +
                        "anz2Del:{},baseEntities:{},entityName:{},entityNum:{},entityName2:{},entity2Num:{},alses:{},cannibalistic:{}",request.getUserId(),puzzle,request.getSodokuMode(),request.getLevelIndex(),candidates,
                step.toString(2), step.getValues(),step.getIndices(),step.getCandidateString(),step.getCandidatesToDelete(),
                step.getFins(),step.getEndoFins(),step.getChains(),step.getChainLength(),step.getAnzCandidatesToDelete(),step.getBaseEntities(),
                step.getEntityName(),step.getEntityNumber(),
                step.getEntityName2(),step.getEntity2Number(),
                step.getAlses(),step.getCannibalistic());

        return convertToResponse(step);
    }

    public SolveResponse solve(String puzzle) {
        Sudoku2 sudoku = new Sudoku2();
        sudoku.setSudoku(puzzle);

        // Create a new solver instance for each solve operation to ensure thread safety
        SudokuSolver currentSolver = SudokuSolverFactory.getInstance();
        try {
            // Solve the puzzle
            DifficultyLevel maxLevel = Options.getInstance().getDifficultyLevel(DifficultyType.EXTREME.ordinal());
            boolean solved = currentSolver.solve(maxLevel, sudoku, false, null, false);

            SolveResponse response = new SolveResponse();
            response.setIsSolved(solved);
            response.setDifficultyLevel(currentSolver.getLevelString());
            response.setTotalScore(currentSolver.getScore());

            if (solved) {
                response.setSolution(sudoku.getSudoku(ClipboardMode.VALUES_ONLY));
            }

            // Convert steps to response format
            List<SolutionStepResponse> stepResponses = new ArrayList<>();
            for (SolutionStep step : currentSolver.getSteps()) {
                stepResponses.add(convertToResponse(step));
            }
            response.setSteps(stepResponses);

            return response;
        } finally {
            // Return the solver instance back to the factory
            SudokuSolverFactory.giveBack(currentSolver);
        }
    }

    private SolutionStepResponse convertToResponse(SolutionStep step) {
        SolutionStepResponse response = new SolutionStepResponse();
        response.setType(step.getType().name());
        response.setDescription(step.toString(2));
        response.setDifficultyLevel(StepConfig.getLevelName(step.getType().getStepConfig().getLevel()));
        response.setScore(step.getType().getStepConfig().getBaseScore());
        response.setValues(step.getValues());
        response.setCandidatesToDelete(step.getCandidatesToDelete());
        response.setEntity(step.getEntity());
        response.setEntityNumber(step.getEntityNumber());
        response.setEntity2(step.getEntity2());
        response.setEntity2Number(step.getEntity2Number());
        response.setFins(step.getFins());
        response.setChains(step.getChains());

        // Handle cells
        List<Integer> cells = new ArrayList<>();
        for (int index : step.getIndices()) {
            cells.add(index);
        }
        response.setCells(cells);

        return response;
    }
}
