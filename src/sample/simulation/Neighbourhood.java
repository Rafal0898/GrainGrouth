package sample.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Neighbourhood {
    private static int xSize;
    private static int ySize;

    public static void calculateWithRadiusPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard, int radiusNeighbourhood) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                if (centerCell.isState()) {
                    nextGenerationBoard.setCell(i, j, centerCell);
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
                        for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                            Cell currentCell = currentTimeStepBoard.getCell(rx, ry);
                            if (currentCell.isState()) {
                                double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                                        + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                                if (length <= radiusNeighbourhood)
                                    map.merge(currentTimeStepBoard.getCell(rx, ry).getId(), 1, Integer::sum);
                            }
                        }
                    }
                    if (!map.isEmpty()) {
                        nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                    }
                }
            }
        }
    }

    public static void calculatePentagonalRandomPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                if (centerCell.isState()) {
                    nextGenerationBoard.setCell(i, j, centerCell);
                } else {
                    int randomNumber = generator.nextInt();
                    if (randomNumber % 4 == 0) {
                        Map<Integer, Integer> map = new HashMap<>();
                        if (currentTimeStepBoard.getCell(i, j - 1).isState())
                            map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
                        if (currentTimeStepBoard.getCell(i, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i - 1, j).isState())
                            map.merge(currentTimeStepBoard.getCell(i - 1, j).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i - 1, j - 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i - 1, j - 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i - 1, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i - 1, j + 1).getId(), 1, Integer::sum);

                        if (!map.isEmpty()) {
                            nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                        }

                    } else if (randomNumber % 4 == 1) {
                        Map<Integer, Integer> map = new HashMap<>();
                        if (currentTimeStepBoard.getCell(i, j - 1).isState())
                            map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
                        if (currentTimeStepBoard.getCell(i, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j - 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j - 1).getId(), 1, Integer::sum);

                        if (currentTimeStepBoard.getCell(i + 1, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j + 1).getId(), 1, Integer::sum);

                        if (!map.isEmpty()) {
                            nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                        }
                    } else if (randomNumber % 4 == 2) {
                        Map<Integer, Integer> map = new HashMap<>();
                        if (currentTimeStepBoard.getCell(i - 1, j).isState())
                            map.put(currentTimeStepBoard.getCell(i - 1, j).getId(), 1);
                        if (currentTimeStepBoard.getCell(i, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i - 1, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i - 1, j + 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j + 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j + 1).getId(), 1, Integer::sum);

                        if (!map.isEmpty()) {
                            nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                        }
                    } else {
                        Map<Integer, Integer> map = new HashMap<>();
                        if (currentTimeStepBoard.getCell(i, j - 1).isState())
                            map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
                        if (currentTimeStepBoard.getCell(i - 1, j - 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i - 1, j - 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i + 1, j - 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i + 1, j - 1).getId(), 1, Integer::sum);
                        if (currentTimeStepBoard.getCell(i, j - 1).isState())
                            map.merge(currentTimeStepBoard.getCell(i, j - 1).getId(), 1, Integer::sum);

                        if (!map.isEmpty()) {
                            nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                        }
                    }
                }
            }
        }
    }

    public static void calculateHexagonalRandomPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (generator.nextInt() % 2 == 0)
                    calculateHexagonalLeftPeriodicForSingleCell(currentTimeStepBoard, nextGenerationBoard, i, j);
                else
                    calculateHexagonalRightPeriodicForSingleCell(currentTimeStepBoard, nextGenerationBoard, i, j);
            }
        }
    }

    public static void calculateHexagonalLeftPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                calculateHexagonalLeftPeriodicForSingleCell(currentTimeStepBoard, nextGenerationBoard, i, j);
            }
        }
    }

    public static void calculateHexagonalLeftPeriodicForSingleCell(Board currentTimeStepBoard, Board nextGenerationBoard, int i, int j) {
        Cell centerCell = currentTimeStepBoard.getCell(i, j);
        if (centerCell.isState()) {
            nextGenerationBoard.setCell(i, j, centerCell);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            if (currentTimeStepBoard.getCell(i, j - 1).isState())
                map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
            if (currentTimeStepBoard.getCell(i + 1, j).isState())
                map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i, j + 1).isState())
                map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i - 1, j).isState())
                map.merge(currentTimeStepBoard.getCell(i - 1, j).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i + 1, j - 1).isState())
                map.merge(currentTimeStepBoard.getCell(i + 1, j - 1).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i - 1, j + 1).isState())
                map.merge(currentTimeStepBoard.getCell(i - 1, j + 1).getId(), 1, Integer::sum);

            if (!map.isEmpty()) {
                nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
            }
        }
    }

    public static void calculateHexagonalRightPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                calculateHexagonalRightPeriodicForSingleCell(currentTimeStepBoard, nextGenerationBoard, i, j);
            }
        }
    }

    public static void calculateHexagonalRightPeriodicForSingleCell(Board currentTimeStepBoard, Board nextGenerationBoard, int i, int j) {
        Cell centerCell = currentTimeStepBoard.getCell(i, j);
        if (centerCell.isState()) {
            nextGenerationBoard.setCell(i, j, centerCell);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            if (currentTimeStepBoard.getCell(i, j - 1).isState())
                map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
            if (currentTimeStepBoard.getCell(i + 1, j).isState())
                map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i, j + 1).isState())
                map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i - 1, j).isState())
                map.merge(currentTimeStepBoard.getCell(i - 1, j).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i - 1, j - 1).isState())
                map.merge(currentTimeStepBoard.getCell(i - 1, j - 1).getId(), 1, Integer::sum);
            if (currentTimeStepBoard.getCell(i + 1, j + 1).isState())
                map.merge(currentTimeStepBoard.getCell(i + 1, j + 1).getId(), 1, Integer::sum);

            if (!map.isEmpty()) {
                nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
            }
        }
    }

    public static void calculateMoorePeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                if (centerCell.isState()) {
                    nextGenerationBoard.setCell(i, j, centerCell);
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    if (currentTimeStepBoard.getCell(i, j - 1).isState())
                        map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
                    if (currentTimeStepBoard.getCell(i + 1, j).isState())
                        map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i, j + 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i - 1, j).isState())
                        map.merge(currentTimeStepBoard.getCell(i - 1, j).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i - 1, j - 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i - 1, j - 1).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i + 1, j - 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i + 1, j - 1).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i - 1, j + 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i - 1, j + 1).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i + 1, j + 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i + 1, j + 1).getId(), 1, Integer::sum);

                    if (!map.isEmpty()) {
                        nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                    }
                }
            }
        }
    }

    public static void calculateVonNeumannPeriodic(Board currentTimeStepBoard, Board nextGenerationBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                if (centerCell.isState()) {
                    nextGenerationBoard.setCell(i, j, centerCell);
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    if (currentTimeStepBoard.getCell(i, j - 1).isState())
                        map.put(currentTimeStepBoard.getCell(i, j - 1).getId(), 1);
                    if (currentTimeStepBoard.getCell(i + 1, j).isState())
                        map.merge(currentTimeStepBoard.getCell(i + 1, j).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i, j + 1).isState())
                        map.merge(currentTimeStepBoard.getCell(i, j + 1).getId(), 1, Integer::sum);
                    if (currentTimeStepBoard.getCell(i - 1, j).isState())
                        map.merge(currentTimeStepBoard.getCell(i - 1, j).getId(), 1, Integer::sum);

                    if (!map.isEmpty()) {
                        nextGenerationBoard.setCell(i, j, new Cell(findMostNeighbours(map), true));
                    }
                }
            }
        }
    }

    public static void calculateWithRadiusAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration, int radiusNeighbourhood) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                if (centerCell.isState()) {
                    nextGeneration[i][j] = centerCell;
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
                        for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                            try {
                                // TODO: 23.04.2020 Naprawić sąsiedztwo z promieniem
//                                if (currentTimeStep[rx][ry].isState())
//                                    map.merge(currentTimeStep[rx][ry].getId(), 1, Integer::sum);
                                Cell currentCell = currentTimeStep[rx][ry];
                                if (currentCell.isState()) {
                                    double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                                            + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                                    if (length <= radiusNeighbourhood)
                                        map.merge(currentTimeStep[rx][ry].getId(), 1, Integer::sum);
                                }
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    if (!map.isEmpty()) {
                        nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
                    }
                }
            }
        }

    }

    public static void calculatePentagonalRandomAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                if (centerCell.isState()) {
                    nextGeneration[i][j] = centerCell;
                } else {
                    int randomNumber = generator.nextInt();
                    Map<Integer, Integer> map = new HashMap<>();
                    if (randomNumber % 4 == 0) {
                        try {
                            if (currentTimeStep[i][j - 1].isState())
                                map.put(currentTimeStep[i][j - 1].getId(), 1);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i][j + 1].isState())
                                map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j].isState())
                                map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j - 1].isState())
                                map.merge(currentTimeStep[i - 1][j - 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j + 1].isState())
                                map.merge(currentTimeStep[i - 1][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    } else if (randomNumber % 4 == 1) {
                        try {
                            if (currentTimeStep[i][j - 1].isState())
                                map.put(currentTimeStep[i][j - 1].getId(), 1);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j].isState())
                                map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i][j + 1].isState())
                                map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j - 1].isState())
                                map.merge(currentTimeStep[i + 1][j - 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j + 1].isState())
                                map.merge(currentTimeStep[i + 1][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    } else if (randomNumber % 4 == 2) {
                        try {
                            if (currentTimeStep[i + 1][j].isState())
                                map.put(currentTimeStep[i + 1][j].getId(), 1);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i][j + 1].isState())
                                map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j].isState())
                                map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j + 1].isState())
                                map.merge(currentTimeStep[i - 1][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j + 1].isState())
                                map.merge(currentTimeStep[i + 1][j + 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    } else {
                        try {
                            if (currentTimeStep[i][j - 1].isState())
                                map.put(currentTimeStep[i][j - 1].getId(), 1);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j].isState())
                                map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j].isState())
                                map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i - 1][j - 1].isState())
                                map.merge(currentTimeStep[i - 1][j - 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        try {
                            if (currentTimeStep[i + 1][j - 1].isState())
                                map.merge(currentTimeStep[i + 1][j - 1].getId(), 1, Integer::sum);
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                    }
                    if (!map.isEmpty()) {
                        nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
                    }
                }
            }
        }
    }

    public static void calculateHexagonalRandomAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (generator.nextInt() % 2 == 0)
                    calculateHexagonalLeftAbsorbingForSingleCell(currentTimeStep, nextGeneration, i, j);
                else
                    calculateHexagonalRightAbsorbingForSingleCell(currentTimeStep, nextGeneration, i, j);
            }
        }
    }

    public static void calculateHexagonalLeftAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                calculateHexagonalLeftAbsorbingForSingleCell(currentTimeStep, nextGeneration, i, j);
            }
        }
    }

    public static void calculateHexagonalLeftAbsorbingForSingleCell(Cell[][] currentTimeStep, Cell[][] nextGeneration, int i, int j) {
        Cell centerCell = currentTimeStep[i][j];
        if (centerCell.isState()) {
            nextGeneration[i][j] = centerCell;
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            try {
                if (currentTimeStep[i][j - 1].isState())
                    map.put(currentTimeStep[i][j - 1].getId(), 1);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j].isState())
                    map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i][j + 1].isState())
                    map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j].isState())
                    map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j + 1].isState())
                    map.merge(currentTimeStep[i - 1][j + 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j - 1].isState())
                    map.merge(currentTimeStep[i + 1][j - 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }

            if (!map.isEmpty()) {
                nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
            }
        }
    }

    public static void calculateHexagonalRightAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                calculateHexagonalRightAbsorbingForSingleCell(currentTimeStep, nextGeneration, i, j);
            }
        }

    }

    public static void calculateHexagonalRightAbsorbingForSingleCell(Cell[][] currentTimeStep, Cell[][] nextGeneration, int i, int j) {
        Cell centerCell = currentTimeStep[i][j];
        if (centerCell.isState()) {
            nextGeneration[i][j] = centerCell;
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            try {
                if (currentTimeStep[i][j - 1].isState())
                    map.put(currentTimeStep[i][j - 1].getId(), 1);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j].isState())
                    map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i][j + 1].isState())
                    map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j].isState())
                    map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j - 1].isState())
                    map.merge(currentTimeStep[i - 1][j - 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j + 1].isState())
                    map.merge(currentTimeStep[i + 1][j + 1].getId(), 1, Integer::sum);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }

            if (!map.isEmpty()) {
                nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
            }
        }
    }

    public static void calculateMooreAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                if (centerCell.isState()) {
                    nextGeneration[i][j] = centerCell;
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    try {
                        if (currentTimeStep[i][j - 1].isState())
                            map.put(currentTimeStep[i][j - 1].getId(), 1);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i + 1][j].isState())
                            map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i][j + 1].isState())
                            map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i - 1][j].isState())
                            map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i - 1][j - 1].isState())
                            map.merge(currentTimeStep[i - 1][j - 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i + 1][j - 1].isState())
                            map.merge(currentTimeStep[i + 1][j - 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i - 1][j + 1].isState())
                            map.merge(currentTimeStep[i - 1][j + 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i + 1][j + 1].isState())
                            map.merge(currentTimeStep[i + 1][j + 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    if (!map.isEmpty()) {
                        nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
                    }
                }
            }
        }
    }

    public static void calculateVonNeumannAbsorbing(Cell[][] currentTimeStep, Cell[][] nextGeneration) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                if (centerCell.isState()) {
                    nextGeneration[i][j] = centerCell;
                } else {
                    Map<Integer, Integer> map = new HashMap<>();
                    try {
                        if (currentTimeStep[i][j - 1].isState())
                            map.put(currentTimeStep[i][j - 1].getId(), 1);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i + 1][j].isState())
                            map.merge(currentTimeStep[i + 1][j].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i][j + 1].isState())
                            map.merge(currentTimeStep[i][j + 1].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        if (currentTimeStep[i - 1][j].isState())
                            map.merge(currentTimeStep[i - 1][j].getId(), 1, Integer::sum);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    if (!map.isEmpty()) {
                        nextGeneration[i][j] = new Cell(findMostNeighbours(map), true);
                    }
                }
            }
        }
    }

    private static Integer findMostNeighbours(Map<Integer, Integer> map) {
        int foundId = 0;
        int maxValue = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                foundId = entry.getKey();
            }
        }
        return foundId;
    }

    public static void setSize(int width, int height) {
        xSize = width;
        ySize = height;
    }
}