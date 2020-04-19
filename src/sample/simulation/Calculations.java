package sample.simulation;

import sample.gui.Controller;

public class Calculations {
    private static int xSize;
    private static int ySize;

    public static Cell[][] calculateNextTimeStep(Cell[][] currentTimeStep,
                                                 boolean ifPeriodicBoundaryConditions,
                                                 boolean ifAbsorbingBoundaryConditions,
                                                 String neighbourhoodType,
                                                 int radiusNeighbourhood) {
        setxSize(currentTimeStep.length);
        setySize(currentTimeStep[0].length);
        Cell[][] nextGeneration = Controller.createEmptyArray(xSize, ySize);

        Board currentTimeStepBoard = new Board(xSize, ySize, currentTimeStep);
        Board nextGenerationBoard = new Board(xSize, ySize, nextGeneration);

        Neighbourhood.setSize(xSize, ySize);
        switch (neighbourhoodType) {
            case "Von Neumann": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateVonNeumannPeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateVonNeumannAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "Moore": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateMoorePeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateMooreAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "Hexagonal right": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateHexagonalRightPeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateHexagonalRightAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "Hexagonal left": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateHexagonalLeftPeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateHexagonalLeftAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "Hexagonal random": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateHexagonalRandomPeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateHexagonalRandomAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "Pentagonal random": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculatePentagonalRandomPeriodic(currentTimeStepBoard, nextGenerationBoard);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculatePentagonalRandomAbsorbing(currentTimeStep, nextGeneration);
                }
                break;
            }
            case "With a radius": {
                if (ifPeriodicBoundaryConditions) {
                    Neighbourhood.calculateWithRadiusPeriodic(currentTimeStepBoard, nextGenerationBoard, radiusNeighbourhood);
                    nextGeneration = nextGenerationBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Neighbourhood.calculateWithRadiusAbsorbing(currentTimeStep, nextGeneration, radiusNeighbourhood);
                }
                break;
            }
        }
        return nextGeneration;
    }

    public static void setxSize(int xSize) {
        Calculations.xSize = xSize;
    }

    public static void setySize(int ySize) {
        Calculations.ySize = ySize;
    }
}