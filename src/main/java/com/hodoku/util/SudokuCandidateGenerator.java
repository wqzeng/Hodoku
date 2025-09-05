package com.hodoku.util;

import sudoku.Sudoku2;
import sudoku.ClipboardMode;

import java.util.*;

public class SudokuCandidateGenerator {

    public static Map<Integer, List<Integer>> generateCandidates(String puzzle) {
        // Create a Sudoku2 instance and set the puzzle
        Sudoku2 sudoku = new Sudoku2();
        sudoku.setSudoku(puzzle);

        // Generate candidates map with sorted keys
        Map<Integer, List<Integer>> candidates = new TreeMap<>();

        // For each cell in the grid
        for (int i = 0; i < 81; i++) {
            // If the cell is not filled yet
            if (sudoku.getValue(i) == 0) {
                // Get all possible candidates for this cell
                int[] cellCandidates = sudoku.getAllCandidates(i);
                List<Integer> candidateList = new ArrayList<>();

                // Add all candidates to the list
                for (int candidate : cellCandidates) {
                    candidateList.add(candidate);
                }

                // Only add to map if there are candidates (should always be true for empty cells)
                if (!candidateList.isEmpty()) {
                    candidates.put(i, candidateList);
                }
            }
        }

        return candidates;
    }

    public static void main(String[] args) {
        String puzzle = "000407000074000200800006047002000910080000000400350000003040100046800032718000000";

        System.out.println("Puzzle:");
        System.out.println(puzzle);
        System.out.println();

        // Create and set the sudoku
        Sudoku2 sudoku = new Sudoku2();
        sudoku.setSudoku(puzzle);

        System.out.println("Puzzle in grid format:");
        System.out.println(sudoku.getSudoku(ClipboardMode.PM_GRID));
        System.out.println();

        // Generate candidates
        Map<Integer, List<Integer>> candidates = generateCandidates(puzzle);

        System.out.println("Candidates for empty cells:");
        for (Map.Entry<Integer, List<Integer>> entry : candidates.entrySet()) {
            System.out.println("Cell " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println();
        System.out.println("JSON format for API request:");
        System.out.println("{");
        System.out.println("  \"puzzle\": \"" + puzzle + "\",");
        System.out.println("  \"candidates\": {");

        boolean first = true;
        for (Map.Entry<Integer, List<Integer>> entry : candidates.entrySet()) {
            if (!first) {
                System.out.println(",");
            }
            System.out.print("    \"" + entry.getKey() + "\": " + entry.getValue());
            first = false;
        }
        System.out.println();
        System.out.println("  }");
        System.out.println("}");
    }
}
