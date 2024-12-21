package days;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    private static final String FILE_PATH = "src/inputs/day1";

    private final List<String> instructionsList;

    public Day1() {
        this.instructionsList = new ArrayList<>();
        readFile();
    }

    private void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + FILE_PATH);
            throw new RuntimeException("Cannot proceed without input file.", e);
        } catch (IOException e) {
            System.err.println("Error: Failed to read file: " + FILE_PATH);
            throw new RuntimeException("I/O error while reading file.", e);
        }
    }

    private void processLine(String line) {
        instructionsList.addAll(Arrays.stream(line.trim().split(""))
                                                      .toList());
    }

    public void printAnswer() {
        try {
            System.out.println("-- Day 1 --");
            System.out.println("Part 1 -> Final floor: " + calcFinalFloor());
            System.out.println("Part 2 -> Character position at which the basement is first entered: " + findFirstBasementEntryCharPosition() + "\n");
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }

    private int calcFinalFloor() {
        int finalFloor = 0;

        for (String instruction : instructionsList) {
            finalFloor += instruction.equals("(") ? 1 : -1;
        }

        return finalFloor;
    }

    private int findFirstBasementEntryCharPosition() {
        int floor = 0;

        for (int i = 0; i < instructionsList.size(); i++) {
            floor += instructionsList.get(i).equals("(") ? 1 : -1;

            if (floor == -1) {
                return i + 1;
            }
        }

        throw new IllegalStateException("The basement is never entered");
    }
}
