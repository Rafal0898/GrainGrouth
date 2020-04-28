package sample.simulation;

import java.util.Random;

public class Energy {
    private static int xSize;
    private static int ySize;

    public static void setEnergyWithRadiusPeriodic(Board currentTimeStepBoard, int radiusNeighbourhood) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                centerCell.setEnergy(getEnergySingleCellWithRadiusPeriodic(currentTimeStepBoard, centerCell, i, j, radiusNeighbourhood));
            }
        }
    }

    public static int getEnergySingleCellWithRadiusPeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j, int radiusNeighbourhood){
        int energyCounter = 0;
        for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
            for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                Cell currentCell = currentTimeStepBoard.getCell(rx, ry);
                if (currentCell.getId() != centerCell.getId()) {
                    double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                            + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                    if (length <= radiusNeighbourhood)
                        energyCounter++;
                }
            }
        }
        return energyCounter;
    }

    public static void setEnergyPentagonalRandomPeriodic(Board currentTimeStepBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                centerCell.setEnergy(getEnergySingleCellPentagonalRandomPeriodic(currentTimeStepBoard, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellPentagonalRandomPeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j){
        int energyCounter = 0;
        Random generator = new Random();
        int randomNumber = generator.nextInt();
        if (randomNumber % 4 == 0) {
            if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i - 1, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i - 1, j + 1).getId() != centerCell.getId())
                energyCounter++;


        } else if (randomNumber % 4 == 1) {
            if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j + 1).getId() != centerCell.getId())
                energyCounter++;

        } else if (randomNumber % 4 == 2) {
            if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i - 1, j + 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j + 1).getId() != centerCell.getId())
                energyCounter++;

        } else {
            if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i - 1, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i + 1, j - 1).getId() != centerCell.getId())
                energyCounter++;
            if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
                energyCounter++;
        }
        return energyCounter;
    }


    public static void setEnergyHexagonalRandomPeriodic(Board currentTimeStepBoard) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (generator.nextInt() % 2 == 0)
                    setEnergyHexagonalLeftPeriodicForSingleCell(currentTimeStepBoard, i, j);
                else
                    setEnergyHexagonalRightPeriodicForSingleCell(currentTimeStepBoard, i, j);
            }
        }
    }

    public static void setEnergyHexagonalLeftPeriodic(Board currentTimeStepBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                setEnergyHexagonalLeftPeriodicForSingleCell(currentTimeStepBoard, i, j);
            }
        }
    }

    public static void setEnergyHexagonalLeftPeriodicForSingleCell(Board currentTimeStepBoard, int i, int j) {
        Cell centerCell = currentTimeStepBoard.getCell(i, j);
        centerCell.setEnergy(getEnergySingleCellHexagonalLeftPeriodic(currentTimeStepBoard, centerCell, i, j));
    }

    public static int getEnergySingleCellHexagonalLeftPeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j + 1).getId() != centerCell.getId())
            energyCounter++;
        return energyCounter;
    }

    public static void setEnergyHexagonalRightPeriodic(Board currentTimeStepBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                setEnergyHexagonalRightPeriodicForSingleCell(currentTimeStepBoard, i, j);
            }
        }
    }

    public static void setEnergyHexagonalRightPeriodicForSingleCell(Board currentTimeStepBoard, int i, int j) {
        Cell centerCell = currentTimeStepBoard.getCell(i, j);
        centerCell.setEnergy(getEnergySingleCellHexagonalRightPeriodic(currentTimeStepBoard, centerCell, i, j));
    }

    public static int getEnergySingleCellHexagonalRightPeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j + 1).getId() != centerCell.getId())
            energyCounter++;
        return energyCounter;
    }

    public static void setEnergyMoorePeriodic(Board currentTimeStepBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                centerCell.setEnergy(getEnergySingleCellMoorePeriodic(currentTimeStepBoard, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellMoorePeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j + 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j + 1).getId() != centerCell.getId())
            energyCounter++;
        return energyCounter;
    }

    public static void setEnergyVonNeumannPeriodic(Board currentTimeStepBoard) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStepBoard.getCell(i, j);
                centerCell.setEnergy(getEnergySingleCellVonNeumannPeriodic(currentTimeStepBoard, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellVonNeumannPeriodic(Board currentTimeStepBoard, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        if (currentTimeStepBoard.getCell(i, j - 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i + 1, j).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i, j + 1).getId() != centerCell.getId())
            energyCounter++;
        if (currentTimeStepBoard.getCell(i - 1, j).getId() != centerCell.getId())
            energyCounter++;
        return energyCounter;
    }

    public static void setEnergyWithRadiusAbsorbing(Cell[][] currentTimeStep, int radiusNeighbourhood) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                centerCell.setEnergy(getEnergySingleCellWithRadiusAbsorbing(currentTimeStep, centerCell, i, j, radiusNeighbourhood));
            }
        }
    }

    public static int getEnergySingleCellWithRadiusAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j, int radiusNeighbourhood) {
        int energyCounter = 0;
        for (int rx = i - radiusNeighbourhood; rx <= i + radiusNeighbourhood; rx++) {
            for (int ry = j - radiusNeighbourhood; ry <= j + radiusNeighbourhood; ry++) {
                try {
                    Cell currentCell = currentTimeStep[rx][ry];
                    if (currentCell.getId() != centerCell.getId()) {
                        double length = Math.sqrt(Math.pow(i + centerCell.getxCenterOfGravity() - rx - currentCell.getxCenterOfGravity(), 2)
                                + Math.pow(j + centerCell.getyCenterOfGravity() - ry - currentCell.getyCenterOfGravity(), 2));
                        if (length <= radiusNeighbourhood)
                            energyCounter++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return energyCounter;
    }

    public static void setEnergyPentagonalRandomAbsorbing(Cell[][] currentTimeStep) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                centerCell.setEnergy(getEnergySingleCellPentagonalRandomAbsorbing(currentTimeStep, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellPentagonalRandomAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j) {
        Random generator = new Random();
        int randomNumber = generator.nextInt();
        int energyCounter = 0;
        if (randomNumber % 4 == 0) {
            try {
                if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else if (randomNumber % 4 == 1) {
            try {
                if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else if (randomNumber % 4 == 2) {
            try {
                if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j + 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else {
            try {
                if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i - 1][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
            try {
                if (currentTimeStep[i + 1][j - 1].getId() != centerCell.getId())
                    energyCounter++;
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return energyCounter;
    }

    public static void setEnergyHexagonalRandomAbsorbing(Cell[][] currentTimeStep) {
        Random generator = new Random();
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (generator.nextInt() % 2 == 0)
                    setEnergyHexagonalLeftAbsorbingForSingleCell(currentTimeStep, i, j);
                else
                    setEnergyHexagonalRightAbsorbingForSingleCell(currentTimeStep, i, j);
            }
        }
    }

    public static void setEnergyHexagonalLeftAbsorbing(Cell[][] currentTimeStep) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                setEnergyHexagonalLeftAbsorbingForSingleCell(currentTimeStep, i, j);
            }
        }
    }

    public static void setEnergyHexagonalLeftAbsorbingForSingleCell(Cell[][] currentTimeStep, int i, int j) {
        Cell centerCell = currentTimeStep[i][j];
        centerCell.setEnergy(getEnergySingleCellHexagonalLeftAbsorbing(currentTimeStep, centerCell, i, j));
    }

    public static int getEnergySingleCellHexagonalLeftAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        try {
            if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return energyCounter;
    }

    public static void setEnergyHexagonalRightAbsorbing(Cell[][] currentTimeStep) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                setEnergyHexagonalRightAbsorbingForSingleCell(currentTimeStep, i, j);
            }
        }
    }

    public static void setEnergyHexagonalRightAbsorbingForSingleCell(Cell[][] currentTimeStep, int i, int j) {
        Cell centerCell = currentTimeStep[i][j];
        centerCell.setEnergy(getEnergySingleCellHexagonalRightAbsorbing(currentTimeStep, centerCell, i, j));
    }

    public static int getEnergySingleCellHexagonalRightAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        try {
            if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return energyCounter;
    }


    public static void setEnergyMooreAbsorbing(Cell[][] currentTimeStep) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                centerCell.setEnergy(getEnergySingleCellMooreAbsorbing(currentTimeStep, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellMooreAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        try {
            if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return energyCounter;
    }

    public static void setEnergyVonNeumannAbsorbing(Cell[][] currentTimeStep) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell centerCell = currentTimeStep[i][j];
                centerCell.setEnergy(getEnergySingleCellVonNeumannAbsorbing(currentTimeStep, centerCell, i, j));
            }
        }
    }

    public static int getEnergySingleCellVonNeumannAbsorbing(Cell[][] currentTimeStep, Cell centerCell, int i, int j) {
        int energyCounter = 0;
        try {
            if (currentTimeStep[i][j - 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i + 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i][j + 1].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            if (currentTimeStep[i - 1][j].getId() != centerCell.getId())
                energyCounter++;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return energyCounter;
    }

    public static void setSize(int width, int height) {
        xSize = width;
        ySize = height;
    }
}
