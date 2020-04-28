package sample.gui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import sample.simulation.Calculations;
import sample.simulation.Cell;
import sample.simulation.MonteCarloMethod;

public class AnimationMonteCarlo extends AnimationTimer {
    private Canvas canvas;
    private Cell[][] array;
    private boolean ifPBCs;
    private boolean ifABCs;
    private String neighbourhoodType;
    private int radiusNeighbourhood;
    private int monteCarloSteps;
    private double kt;
    private long delay;

    private long lastUpdate = 0;
    private int counter = 0;

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= delay) {
            array = MonteCarloMethod.doMonteCarloMethod(array, neighbourhoodType, radiusNeighbourhood,
                    ifPBCs, ifABCs, kt);
            Controller.setCurrentGeneration(array);
            Drawing.draw(array, canvas);
            lastUpdate = now;
            counter++;
            if(counter == monteCarloSteps) this.stop();
        }
    }

    public AnimationMonteCarlo(Canvas canvas, Cell[][] array, boolean ifPBCs, boolean ifABCs, String neighbourhoodType,
                               int radiusNeighbourhood, long delay, double kt, int monteCarloSteps) {
        this.canvas = canvas;
        this.array = array;
        this.ifPBCs = ifPBCs;
        this.ifABCs = ifABCs;
        this.neighbourhoodType = neighbourhoodType;
        this.radiusNeighbourhood = radiusNeighbourhood;
        this.delay = delay;
        this.kt = kt;
        this.monteCarloSteps = monteCarloSteps;
    }
}
