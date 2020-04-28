package sample.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class Cell {
    private int id;
    private boolean state;
    private double xCenterOfGravity;
    private double yCenterOfGravity;
    private int energy;
    public static Map<Integer, Color> map = new HashMap<>();

    public Cell(int id, boolean state) {
        this.id = id;
        this.state = state;

        Random generator = new Random();
        xCenterOfGravity = Math.abs(generator.nextDouble() % 1);
        yCenterOfGravity = Math.abs(generator.nextDouble() % 1);
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

    public double getxCenterOfGravity() {
        return xCenterOfGravity;
    }

    public double getyCenterOfGravity() {
        return yCenterOfGravity;
    }

    public void setEnergy(int energy) {
        this.energy=energy;
    }

    public int getEnergy(){
        return energy;
    }
}
