package sample.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.simulation.Cell;

public class Drawing {
    public static void draw(Cell[][] currentTimeStep, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int squareSize = Controller.getSquareSize();
        int currentRow = 0;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Cell[] singleRaw : currentTimeStep) {
            int currentColumn = 0;
            for (Cell singleCell : singleRaw) {
                if (singleCell.isState()) {
                    int x = squareSize * currentColumn ;
                    int y = squareSize * currentRow;
                    gc.setFill(singleCell.getColor());
                    gc.fillRect(x, y, squareSize, squareSize);
                }
                currentColumn++;
            }
            currentRow++;
        }
    }
    public static void drawForSelfDefined(Color color, int x, int y, Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int squareSize = Controller.getSquareSize();
        gc.setFill(color);
        gc.fillRect(x, y, squareSize, squareSize);
    }
}