package sample.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarloNeighbourhood {
    public static void smoothWithRadiusPeriodic(Board currentTimeStepBoard, int i, int j, double kt, int radiusNeighbourhood) {
        Cell centerCell = currentTimeStepBoard.getCell(i, j);
        List<Integer> idArray = new ArrayList<>();
        for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
            for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                Cell currentCell = currentTimeStepBoard.getCell(rx, ry);
                double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                        + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                if (length <= radiusNeighbourhood)
                    idArray.add(currentTimeStepBoard.getCell(rx, ry).getId());
            }
        }
        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellWithRadiusPeriodic(currentTimeStepBoard, newState, i, j, radiusNeighbourhood);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }

    public static void smoothPentagonalRandomPeriodic(Board currentTimeStepBoard, int i, int j, double kt) {
        Random generator = new Random();

        int randomNumber = generator.nextInt();
        if (randomNumber % 4 == 0) {
            List<Integer> idArray = new ArrayList<>();
            idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j - 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j + 1).getId());

            checkEnergyPentagonalPeriodic(currentTimeStepBoard, i, j, idArray, kt);
        } else if (randomNumber % 4 == 1) {
            List<Integer> idArray = new ArrayList<>();
            idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j + 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j - 1).getId());

            checkEnergyPentagonalPeriodic(currentTimeStepBoard, i, j, idArray, kt);
        } else if (randomNumber % 4 == 2) {
            List<Integer> idArray = new ArrayList<>();
            idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j + 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j + 1).getId());

            checkEnergyPentagonalPeriodic(currentTimeStepBoard, i, j, idArray, kt);
        } else {
            List<Integer> idArray = new ArrayList<>();
            idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
            idArray.add(currentTimeStepBoard.getCell(i - 1, j - 1).getId());
            idArray.add(currentTimeStepBoard.getCell(i + 1, j - 1).getId());

            checkEnergyPentagonalPeriodic(currentTimeStepBoard, i, j, idArray, kt);
        }
    }

    private static void checkEnergyPentagonalPeriodic(Board currentTimeStepBoard, int i, int j, List<Integer> idArray, double kt) {
        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellPentagonalRandomPeriodic(currentTimeStepBoard, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }


    public static void smoothHexagonalRandomPeriodic(Board currentTimeStepBoard, int i, int j, double kt) {
        Random generator = new Random();
        if (generator.nextInt() % 2 == 0)
            smoothHexagonalLeftPeriodicForSingleCell(currentTimeStepBoard, i, j, kt);
        else
            smoothHexagonalRightPeriodicForSingleCell(currentTimeStepBoard, i, j, kt);
    }

    public static void smoothHexagonalLeftPeriodicForSingleCell(Board currentTimeStepBoard, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j + 1).getId());

        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellHexagonalLeftPeriodic(currentTimeStepBoard, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }

    public static void smoothHexagonalRightPeriodicForSingleCell(Board currentTimeStepBoard, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j + 1).getId());

        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellHexagonalRightPeriodic(currentTimeStepBoard, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }

    public static void smoothMoorePeriodic(Board currentTimeStepBoard, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j - 1).getId());

        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellMoorePeriodic(currentTimeStepBoard, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }

    public static void smoothVonNeumannPeriodic(Board currentTimeStepBoard, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        idArray.add(currentTimeStepBoard.getCell(i, j - 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i + 1, j).getId());
        idArray.add(currentTimeStepBoard.getCell(i, j + 1).getId());
        idArray.add(currentTimeStepBoard.getCell(i - 1, j).getId());

        int energyBefore = currentTimeStepBoard.getCell(i, j).getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellVonNeumannPeriodic(currentTimeStepBoard, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStepBoard.setCell(i, j, newState);
            currentTimeStepBoard.getCell(i, j).setEnergy(energyAfter);
        }
    }

    public static void smoothWithRadiusAbsorbing(Cell[][] currentTimeStep, int i, int j, double kt, int radiusNeighbourhood) {
        Cell centerCell = currentTimeStep[i][j];
        List<Integer> idArray = new ArrayList<>();
        for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
            for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                try {
                    Cell currentCell = currentTimeStep[rx][ry];
                    if (currentCell.isState()) {
                        double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                                + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                        if (length <= radiusNeighbourhood)
                            idArray.add(currentTimeStep[i][j - 1].getId());
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellMooreAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }
    }

    public static void smoothPentagonalRandomAbsorbing(Cell[][] currentTimeStep, int i, int j, double kt) {
        Random generator = new Random();
        int randomNumber = generator.nextInt();
        if (randomNumber % 4 == 0) {
            List<Integer> idArray = new ArrayList<>();
            try {
                idArray.add(currentTimeStep[i][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }

            try {
                idArray.add(currentTimeStep[i - 1][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            checkEnergyPentagonalAbsorbing(currentTimeStep, i, j, idArray, kt);
        } else if (randomNumber % 4 == 1) {
            List<Integer> idArray = new ArrayList<>();
            try {
                idArray.add(currentTimeStep[i][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            checkEnergyPentagonalAbsorbing(currentTimeStep, i, j, idArray, kt);
        } else if (randomNumber % 4 == 2) {
            List<Integer> idArray = new ArrayList<>();
            try {
                idArray.add(currentTimeStep[i + 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j + 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            checkEnergyPentagonalAbsorbing(currentTimeStep, i, j, idArray, kt);
        } else {
            List<Integer> idArray = new ArrayList<>();
            try {
                idArray.add(currentTimeStep[i][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i - 1][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                idArray.add(currentTimeStep[i + 1][j - 1].getId());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            checkEnergyPentagonalAbsorbing(currentTimeStep, i, j, idArray, kt);
        }

    }

    private static void checkEnergyPentagonalAbsorbing(Cell[][] currentTimeStep, int i, int j, List<Integer> idArray, double kt) {
        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellMooreAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }
    }

    public static void smoothHexagonalRandomAbsorbing(Cell[][] currentTimeStep, int i, int j, double kt) {
        Random generator = new Random();
        if (generator.nextInt() % 2 == 0)
            smoothHexagonalLeftAbsorbingForSingleCell(currentTimeStep, i, j, kt);
        else
            smoothHexagonalRightAbsorbingForSingleCell(currentTimeStep, i, j, kt);
    }

    public static void smoothHexagonalLeftAbsorbingForSingleCell(Cell[][] currentTimeStep, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        try {
            idArray.add(currentTimeStep[i][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            idArray.add(currentTimeStep[i + 1][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellHexagonalLeftAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }
    }

    public static void smoothHexagonalRightAbsorbingForSingleCell(Cell[][] currentTimeStep, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        try {
            idArray.add(currentTimeStep[i][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            idArray.add(currentTimeStep[i - 1][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellHexagonalRightAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }

    }

    public static void smoothMooreAbsorbing(Cell[][] currentTimeStep, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        try {
            idArray.add(currentTimeStep[i][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            idArray.add(currentTimeStep[i - 1][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellMooreAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }
    }

    public static void smoothVonNeumannAbsorbing(Cell[][] currentTimeStep, int i, int j, double kt) {
        List<Integer> idArray = new ArrayList<>();
        try {
            idArray.add(currentTimeStep[i][j - 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i + 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i][j + 1].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            idArray.add(currentTimeStep[i - 1][j].getId());
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        int energyBefore = currentTimeStep[i][j].getEnergy();
        Cell newState = new Cell(randomCellId(idArray), true);
        int energyAfter = Energy.getEnergySingleCellVonNeumannAbsorbing(currentTimeStep, newState, i, j);
        int deltaEnergy = energyAfter - energyBefore;

        double probability = Math.exp(-deltaEnergy / kt);
        Random generator = new Random();
        if (deltaEnergy <= 0 || generator.nextDouble() % 1 < probability) {
            currentTimeStep[i][j] = newState;
            currentTimeStep[i][j].setEnergy(energyAfter);
        }
    }

    private static int randomCellId(List<Integer> array) {
        Random generator = new Random();
        for (Integer id : array) {
            if (generator.nextInt() % array.size() == 0) return id;
        }
        return array.get(0);
    }
}
