package sample.simulation;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Cell {
    private int id;
    private boolean state;
    public static Map<Integer, Color> map = new HashMap<>();

    public Cell(int id, boolean state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return map.get(this.id);
    }

    public boolean isState() {
        return state;
    }
}
