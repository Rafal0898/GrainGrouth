package sample.simulation;

import sample.gui.Controller;

public class Board {
    private int width;
    private int height;
    private Cell[] board;

    public Board(int width, int height, Cell[][] array) {
        this.width = width;
        this.height = height;
        this.board = new Cell[width * height];
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.setCell(i, j, array[i][j]);
            }
        }
    }

    public Cell[][] convertTo2D() {
        Cell[][] array = Controller.createEmptyArray(this.width, this.height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                array[i][j] = this.getCell(i, j);
            }
        }
        return array;
    }

    public Cell getCell(int x, int y) {
        while (x < 0 || y < 0) {
            x += this.width;
            y += this.height;
        }
        x = x % width;
        y = y % height;
        int index = y * width + x;
        return board[index];
    }

    public void setCell(int x, int y, Cell cell) {
        while (x < 0 || y < 0) {
            x += this.width;
            y += this.height;
        }
        x = x % width;
        y = y % height;
        int index = y * width + x;
        board[index] = cell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
