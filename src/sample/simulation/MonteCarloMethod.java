package sample.simulation;

import java.util.LinkedHashSet;
import java.util.Random;

public class MonteCarloMethod {
    public static Cell[][] doMonteCarloMethod(Cell[][] gridToSmooth, String neighbourhoodType,
                                              int radiusNeighbourhood,
                                              boolean ifPeriodicBoundaryConditions,
                                              boolean ifAbsorbingBoundaryConditions,
                                              double kt) {
        int xSize = gridToSmooth.length;
        int ySize = gridToSmooth[0].length;
        Board gridToSmoothBoard = new Board(xSize, ySize, gridToSmooth);
        Energy.setSize(xSize, ySize);

        LinkedHashSet<Integer> xIndexArray = new LinkedHashSet<>();
        LinkedHashSet<Integer> yIndexArray = new LinkedHashSet<>();
        Random generator = new Random();
        int counter = 0;
        while(counter<xSize){
            int randomNumber = Math.abs(generator.nextInt()%xSize);
            if(!xIndexArray.contains(randomNumber)){
                xIndexArray.add(randomNumber);
                counter++;
            }
        }
        counter = 0;
        while(counter<ySize){
            int randomNumber = Math.abs(generator.nextInt()%ySize);
            if(!yIndexArray.contains(randomNumber)){
                yIndexArray.add(randomNumber);
                counter++;
            }
        }

        switch (neighbourhoodType) {
            case "Von Neumann": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyVonNeumannPeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothVonNeumannPeriodic(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyVonNeumannAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothVonNeumannAbsorbing(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "Moore": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyMoorePeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothMoorePeriodic(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyMooreAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothMooreAbsorbing(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "Hexagonal right": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyHexagonalRightPeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalRightPeriodicForSingleCell(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyHexagonalRightAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalRightAbsorbingForSingleCell(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "Hexagonal left": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyHexagonalLeftPeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalLeftPeriodicForSingleCell(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyHexagonalLeftAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalLeftAbsorbingForSingleCell(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "Hexagonal random": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyHexagonalRandomPeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalRandomPeriodic(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyHexagonalRandomAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothHexagonalRandomAbsorbing(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "Pentagonal random": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyPentagonalRandomPeriodic(gridToSmoothBoard);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothPentagonalRandomPeriodic(gridToSmoothBoard, i, j, kt);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyPentagonalRandomAbsorbing(gridToSmooth);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothPentagonalRandomAbsorbing(gridToSmooth, i, j, kt);
                        }
                    }
                }
                break;
            }
            case "With a radius": {
                if (ifPeriodicBoundaryConditions) {
                    Energy.setEnergyWithRadiusPeriodic(gridToSmoothBoard, radiusNeighbourhood);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmoothBoard.getCell(i, j).getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothWithRadiusPeriodic(gridToSmoothBoard, i, j, kt, radiusNeighbourhood);
                        }
                    }
                    gridToSmooth = gridToSmoothBoard.convertTo2D();
                }
                if (ifAbsorbingBoundaryConditions) {
                    Energy.setEnergyWithRadiusAbsorbing(gridToSmooth, radiusNeighbourhood);
                    for(int i : xIndexArray){
                        for(int j : yIndexArray){
                            if (gridToSmooth[i][j].getEnergy() > 0)
                                MonteCarloNeighbourhood.smoothWithRadiusAbsorbing(gridToSmooth, i, j, kt, radiusNeighbourhood);
                        }
                    }
                }
                break;
            }
        }
        return gridToSmooth;
    }
}
