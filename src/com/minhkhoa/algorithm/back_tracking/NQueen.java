package com.minhkhoa.algorithm.back_tracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NQueen {

    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        List<List<String>> listList = nQueen.solveNQueen(4);
        System.out.println(listList);
    }

    public List<List<String>> solveNQueen(int n) {
        char[][] matrix = this.getInitialArray(n);
        Set<Position> safePositions = this.getSetInitialSafePositions(n);
        Set<Set<Position>> sets = this.performSolveNQueen(matrix, safePositions);
        return null;
    }

    private Set<Set<Position>> performSolveNQueen(char[][] matrix, Set<Position> safePositions) {
        Set<Set<Position>> list = null;
        for (Position current : safePositions) {

            Set<Position> newSafePositions = new HashSet<>();
            if (matrix[current.x][current.y] == '.') {
                matrix[current.x][current.y] = 'Q';
                Set<Position> vulnerableCells = this.getVulnerableCells(current, matrix);
                newSafePositions = new HashSet<>(safePositions);
                newSafePositions.removeAll(vulnerableCells);
            }

            if (checkValid(matrix, newSafePositions)) {
                HashSet<Set<Position>> result = new HashSet<>();
                result.add(this.getSetPositionResult(matrix));
                return result;
            }

            Set<Set<Position>> expectedResult = performSolveNQueen(matrix, newSafePositions);
            if (!Objects.isNull(expectedResult)) {
                if (Objects.isNull(list)) {
                    list = new HashSet<>();
                }
                list.addAll(expectedResult);
            }
            matrix[current.x][current.y] = '.';
        }
        return list;
    }

    private void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private Set<Position> getSetInitialSafePositions(int n) {
        Set<Position> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.add(new Position(i, j));
            }
        }
        return set;
    }

    private char[][] getInitialArray(int n) {
        char[][] matrix = new char[n][n];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = '.';
            }
        }
        return matrix;
    }

    public List<String> getListStringResult(char[][] matrix) {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < matrix[0].length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\"");
            for (int j = 0; j < matrix[0].length; j++) {
                String value = String.valueOf(matrix[i][j]);
                stringBuilder.append(value);
            }
            stringBuilder.append("\"");
            result.add(stringBuilder.toString());
        }
        return result;
    }

    public Set<Position> getSetPositionResult(char[][] matrix) {
        Set<Position> result = new HashSet<>();
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result.add(new Position(i, j));
            }
        }
        return result;
    }

    public boolean checkValid(char[][] matrix, Set<Position> safePosition) {
        if (!safePosition.isEmpty()) {
            return false;
        }

        int counter = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'Q') {
                    if (!this.checkValidACell(new Position(i, j), matrix)) {
                        return false;
                    }
                    counter++;
                }
            }
        }
        return (counter == matrix[0].length);
    }

    private boolean checkValidACell(Position currentPosition, char[][] matrix) {
        Set<Position> vulnerableCells = this.getVulnerableCells(currentPosition, matrix);
        vulnerableCells.remove(currentPosition);
        for (Position positionRunner : vulnerableCells) {
            if (matrix[positionRunner.x][positionRunner.y] == 'Q') {
                return false;
            }
        }
        return true;
    }


    public Set<Position> getVulnerableCells(Position position, char[][] matrix) {
        Set<Position> positionSet = new HashSet<>();

        Set<Position> verticalPositionSet = performGetVulnerableCells(position, matrix, this.getVerticalVulnerableCells());
        Set<Position> horizontalPositionSet = performGetVulnerableCells(position, matrix, this.getHorizontalVulnerableCells());
        Set<Position> leftDiagonalPositionSet = performGetVulnerableCells(position, matrix, this.getLeftDiagonalVulnerableCells());
        Set<Position> rightDiagonalPositionSet = performGetVulnerableCells(position, matrix, this.getRightDiagonalVulnerableCells());

        positionSet.addAll(verticalPositionSet);
        positionSet.addAll(horizontalPositionSet);
        positionSet.addAll(leftDiagonalPositionSet);
        positionSet.addAll(rightDiagonalPositionSet);
        return positionSet;
    }

    private Set<Position> performGetVulnerableCells(Position position, char[][] matrix, VulnerableCells method) {
        return method.getCells(position, matrix);
    }

    private VulnerableCells getHorizontalVulnerableCells() {
        return (position, matrix) -> {
            int y = 0;
            Set<Position> positionSet = new HashSet<>();
            while (y < matrix[0].length) {
                positionSet.add(new Position(position.x, y));
                y++;
            }
            return positionSet;
        };
    }

    private VulnerableCells getVerticalVulnerableCells() {
        return (position, matrix) -> {
            int x = 0;
            Set<Position> positionSet = new HashSet<>();
            while (x < matrix[0].length) {
                positionSet.add(new Position(x, position.y));
                x++;
            }
            return positionSet;
        };
    }

    private VulnerableCells getLeftDiagonalVulnerableCells() {
        return (position, matrix) -> {
            Set<Position> positionSet = new HashSet<>();

            int currentX = position.x;
            int currentY = position.y;
            while (currentX < matrix[0].length && currentY < matrix[0].length) {
                positionSet.add(new Position(currentX, currentY));
                currentX++;
                currentY++;
            }

            currentX = position.x;
            currentY = position.y;
            while (currentX >= 0 && currentY >= 0) {
                positionSet.add(new Position(currentX, currentY));
                currentX--;
                currentY--;
            }

            return positionSet;
        };
    }

    private VulnerableCells getRightDiagonalVulnerableCells() {
        return (position, matrix) -> {
            Set<Position> positionSet = new HashSet<>();

            int currentX = position.x;
            int currentY = position.y;

            while (currentX >= 0 && currentY < matrix[0].length) {
                positionSet.add(new Position(currentX, currentY));
                currentX--;
                currentY++;
            }

            currentX = position.x;
            currentY = position.y;
            while (currentX < matrix[0].length && currentY >= 0) {
                positionSet.add(new Position(currentX, currentY));
                currentX++;
                currentY--;
            }

            return positionSet;
        };
    }

    @FunctionalInterface
    private interface VulnerableCells {
        Set<Position> getCells(Position position, char[][] matrix);
    }

    private static class Position {

        private final int x;

        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return this.x + this.y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position position = (Position) obj;
                return (this.x == position.x && this.y == position.y);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
