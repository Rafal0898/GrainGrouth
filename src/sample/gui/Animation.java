package sample.gui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import sample.simulation.Calculations;
import sample.simulation.Cell;

public class Animation extends AnimationTimer {
    private Canvas canvas;
    private Cell[][] array;
    private boolean ifPBCs;
    private boolean ifABCs;
    private String neighbourhoodType;
    private int radiusNeighbourhood;
    private long delay;

    private long lastUpdate = 0;

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= delay) {
            Drawing.draw(array, canvas);
            array = Calculations.calculateNextTimeStep(array, ifPBCs, ifABCs, neighbourhoodType, radiusNeighbourhood);
            lastUpdate = now;
        }
    }

    public Animation(Canvas canvas, Cell[][] array, boolean ifPBCs, boolean ifABCs, String neighbourhoodType, int radiusNeighbourhood, long delay) {
        this.canvas = canvas;
        this.array = array;
        this.ifPBCs = ifPBCs;
        this.ifABCs = ifABCs;
        this.neighbourhoodType = neighbourhoodType;
        this.radiusNeighbourhood = radiusNeighbourhood;
        this.delay = delay;
    }
}
