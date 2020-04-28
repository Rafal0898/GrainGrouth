package sample.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.simulation.Cell;

public class Drawing {
    public static int squareSize;

    public static void draw(Cell[][] currentTimeStep, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int squareSize = Controller.getSquareSize();
        int currentRow = 0;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Cell[] singleRow : currentTimeStep) {
            int currentColumn = 0;
            for (Cell singleCell : singleRow) {
                if (singleCell.isState()) {
                    int x = squareSize * currentColumn;
                    int y = squareSize * currentRow;
                    gc.setFill(singleCell.getColor());
                    gc.fillRect(x, y, squareSize, squareSize);
                }
                currentColumn++;
            }
            currentRow++;
        }
    }

    public static void drawForSelfDefined(Color color, int x, int y, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int squareSize = Controller.getSquareSize();
        gc.setFill(color);
        gc.fillRect(x, y, squareSize, squareSize);
    }

    public static void drawEnergy(Cell[][] currentTimeStep, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int squareSize = Controller.getSquareSize();
        int currentRow = 0;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Cell[] singleRow : currentTimeStep) {
            int currentColumn = 0;
            for (Cell singleCell : singleRow) {
                Color color;
                if(singleCell.getEnergy() == 0) color = Color.BLUE;
                else if (singleCell.getEnergy() == 1) color = Color.GREEN;
                else if(singleCell.getEnergy() == 2) color = Color.YELLOW;
                else if(singleCell.getEnergy() < 5) color = Color.ORANGE;
                else color = Color.RED;

                int x = squareSize * currentColumn;
                int y = squareSize * currentRow;
                gc.setFill(color);
                gc.fillRect(x, y, squareSize, squareSize);
                currentColumn++;
            }
            currentRow++;
        }
    }

    public static void setSquareSize(int size) {
        squareSize = size;
    }
}