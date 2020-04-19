package sample.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.simulation.Cell;


public class Controller implements Initializable {
    @FXML
    Button pauseResumeButton;
    @FXML
    Button startButton;
    @FXML
    Canvas canvas;
    @FXML
    CheckBox checkBoxPeriodicBoundaryConditions;
    @FXML
    CheckBox checkBoxAbsorbingBoundaryConditions;
    @FXML
    ComboBox<String> comboBoxNucleation;
    @FXML
    TextField textFieldX;
    @FXML
    TextField textFieldY;
    @FXML
    TextField textFieldR;
    @FXML
    TextField textFieldC;
    @FXML
    Label labelQuantityOrColumn;
    @FXML
    Label labelRadiusOrRow;
    @FXML
    ComboBox<String> comboBoxNeighbourhood;
    @FXML
    TextField textFieldRadius;
    @FXML
    Label labelRadius;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxNucleation.setItems(FXCollections.observableArrayList("Homogeneous", "Random", "With radius", "Self defined"));
        comboBoxNucleation.setValue("Homogeneous");
        comboBoxNeighbourhood.setItems(FXCollections.observableArrayList("Von Neumann", "Moore", "Hexagonal right", "Hexagonal left",
                "Hexagonal random", "Pentagonal random", "With a radius"));
        comboBoxNeighbourhood.setValue("Von Neumann");
        checkBoxPeriodicBoundaryConditions.setSelected(true);
        labelRadius.setVisible(false);
        textFieldRadius.setVisible(false);
        textFieldX.setText("55");
        textFieldY.setText("120");
        textFieldR.setText("5");
        textFieldC.setText("5");
        textFieldRadius.setText("3");
    }

    private static int squareSize = 5;

    Animation animation;

    public void clickStartButton() {
        try {
            animation.stop();
        } catch (NullPointerException nullException) {
            System.out.println("First usage");
        }
        long delay = 0;//300_000_000;

        boolean ifPeriodicBoundaryConditions = checkBoxPeriodicBoundaryConditions.isSelected();
        boolean ifAbsorbingBoundaryConditions = checkBoxAbsorbingBoundaryConditions.isSelected();
        String nucleationType = comboBoxNucleation.getValue();
        String neighbourhoodType = comboBoxNeighbourhood.getValue();
        int xSize = Integer.parseInt(textFieldX.getText());
        int ySize = Integer.parseInt(textFieldY.getText());
        int rOrQuantityInRow = Integer.parseInt(textFieldR.getText());
        int quantityOrQuantityInColumn = Integer.parseInt(textFieldC.getText());
        int radiusNeighbourhood = Integer.parseInt(textFieldRadius.getText());

        if (xSize < 20 && ySize < 20) squareSize = 15;
        else if (xSize < 60 && ySize < 60) squareSize = 10;
        else if (xSize < 200 && ySize < 200) squareSize = 5;
        else if (xSize < 300 && ySize < 300) squareSize = 3;
        else squareSize = 2;

        Cell[][] currentGeneration = initializeArray(nucleationType, xSize, ySize,
                rOrQuantityInRow, quantityOrQuantityInColumn);

        animation = new Animation(canvas, currentGeneration,
                ifPeriodicBoundaryConditions, ifAbsorbingBoundaryConditions,
                neighbourhoodType, radiusNeighbourhood, delay);
        animation.start();
    }

    private int pauseResumeCounter = 0;

    public void clickPauseResumeButton() {
        if (pauseResumeCounter % 2 == 0) {
            try {
                animation.stop();
                pauseResumeCounter++;
            } catch (NullPointerException nullException) {
                System.out.println("There's nothing to stop/resume");
            }
        } else {
            try {
                animation.start();
                pauseResumeCounter = 0;
            } catch (NullPointerException nullException) {
                System.out.println("There's nothing to stop/resume");
            }
        }
    }

    private Cell[][] initializeArray(String nucleationType, int xSize, int ySize, int rOrQuantityInRow, int quantityOrQuantityInColumn) {
        Cell[][] initializedArray = createEmptyArray(xSize, ySize);
        Random generator = new Random();
        int index = 1;
        switch (nucleationType) {
            case "Homogeneous": {
                int xDistance = xSize / quantityOrQuantityInColumn;
                int yDistance = ySize / rOrQuantityInRow;
                for (int i = 0; i < quantityOrQuantityInColumn; i++) {
                    for (int j = 0; j < rOrQuantityInRow; j++) {
                        int r = Math.abs(generator.nextInt() % 256);
                        int g = Math.abs(generator.nextInt() % 256);
                        int b = Math.abs(generator.nextInt() % 256);
                        Cell.map.put(index, Color.rgb(r, g, b));
                        initializedArray[i * xDistance][j * yDistance] = new Cell(index, true);
                        index++;
                    }
                }
                break;
            }
            case "Random": {
                while (index <= quantityOrQuantityInColumn) {
                    int r = Math.abs(generator.nextInt() % 256);
                    int g = Math.abs(generator.nextInt() % 256);
                    int b = Math.abs(generator.nextInt() % 256);
                    int x = Math.abs(generator.nextInt() % xSize);
                    int y = Math.abs(generator.nextInt() % ySize);
                    if (!initializedArray[x][y].isState()) {
                        Cell.map.put(index, Color.rgb(r, g, b));
                        initializedArray[x][y] = new Cell(index, true);
                        index++;
                    }
                }
                break;
            }
            case "With radius": {
                int triesCounter = 0;
                while (index <= quantityOrQuantityInColumn) {
                    int r = Math.abs(generator.nextInt() % 256);
                    int g = Math.abs(generator.nextInt() % 256);
                    int b = Math.abs(generator.nextInt() % 256);
                    int x = Math.abs(generator.nextInt() % xSize);
                    int y = Math.abs(generator.nextInt() % ySize);
                    if (!initializedArray[x][y].isState()) {
                        boolean var = true;
                        for (int i = Math.max(x - rOrQuantityInRow + 1, 0); i < Math.min(x + rOrQuantityInRow + 1, xSize); i++) {
                            for (int j = Math.max(y - rOrQuantityInRow + 1, 0); j < Math.min(y + rOrQuantityInRow + 1, ySize); j++) {
                                if (initializedArray[i][j].isState()) {
                                    var = false;
                                    break;
                                }
                                if (!var) break;
                            }
                        }
                        if (var) {
                            Cell.map.put(index, Color.rgb(r, g, b));
                            initializedArray[x][y] = new Cell(index, true);
                            index++;
                        }
                    }
                    triesCounter++;
                    if (triesCounter > 100 * quantityOrQuantityInColumn) {
                        System.out.println("Could not create more grains");
                        break;
                    }
                }
                break;
            }
            case "Self defined": {

                for (int i = 0; i < xArray.size(); i++) {
                    int x = xArray.get(i);
                    int y = yArray.get(i);
                    Color color = colorsArray.get(i);
                    if (y < xSize && x < ySize) {
                        Cell.map.put(index, color);
                        initializedArray[y][x] = new Cell(index, true);
                        index++;
                    }
                }
                xArray.clear();
                yArray.clear();
                colorsArray.clear();
                break;
            }
            default: {
                System.out.println("ERROR");
            }
        }
        return initializedArray;
    }

    public void setLabelsAndCanvas() {
        String nucleationType = comboBoxNucleation.getValue();
        switch (nucleationType) {
            case "Homogeneous": {
                canvas.setOnMouseClicked(this::doNothing);
                canvas.setOnMouseDragged(this::doNothing);
                labelRadiusOrRow.setText("Quantity in row:");
                labelQuantityOrColumn.setText("Quantity in column:");
                break;
            }
            case "Random": {
                canvas.setOnMouseClicked(this::doNothing);
                canvas.setOnMouseDragged(this::doNothing);
                labelRadiusOrRow.setText("----");
                labelQuantityOrColumn.setText("Quantity");
                break;
            }
            case "With radius": {
                canvas.setOnMouseClicked(this::doNothing);
                canvas.setOnMouseDragged(this::doNothing);
                labelRadiusOrRow.setText("Radius:");
                labelQuantityOrColumn.setText("Quantity");
                break;
            }
            case "Self defined": {
                try {
                    animation.stop();
                } catch (NullPointerException nullException) {
                    System.out.println("There's nothing to stop");
                }
                canvas.setOnMouseClicked(this::handleDraw);
                canvas.setOnMouseDragged(this::handleDraw);
                labelRadiusOrRow.setText("----");
                labelQuantityOrColumn.setText("----");
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                break;
            }
            default: {
                System.out.println("ERROR");
            }

        }
    }

    public void appearRadiusTextField() {
        String nucleationType = comboBoxNeighbourhood.getValue();
        if (nucleationType.equals("With a radius")) {
            labelRadius.setVisible(true);
            textFieldRadius.setVisible(true);
        } else {
            labelRadius.setVisible(false);
            textFieldRadius.setVisible(false);
        }
    }

    List<Integer> xArray = new ArrayList<>();
    List<Integer> yArray = new ArrayList<>();
    List<Color> colorsArray = new ArrayList<>();

    private void handleDraw(MouseEvent event) {
        int xCord = (int) event.getX();
        int yCord = (int) event.getY();
        xCord = xCord - xCord % squareSize;
        yCord = yCord - yCord % squareSize;

        Random generator = new Random();
        int r = Math.abs(generator.nextInt() % 256);
        int g = Math.abs(generator.nextInt() % 256);
        int b = Math.abs(generator.nextInt() % 256);
        Color color = Color.rgb(r, g, b);

        Drawing.drawForSelfDefined(color, xCord, yCord, canvas);

        colorsArray.add(color);
        xArray.add(xCord / squareSize);
        yArray.add(yCord / squareSize);
    }

    private void doNothing(MouseEvent mouseEvent) {
    }

    public static Cell[][] createEmptyArray(int xSize, int ySize) {
        Cell[][] emptyArray = new Cell[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Cell.map.put(0, Color.WHITE);
                emptyArray[i][j] = new Cell(0, false);
            }
        }
        return emptyArray;
    }

    public void uncheckABCs() {
        checkBoxAbsorbingBoundaryConditions.setSelected(false);
    }

    public void uncheckPBCs() {
        checkBoxPeriodicBoundaryConditions.setSelected(false);
    }

    public static int getSquareSize() {
        return squareSize;
    }

    public static void closeProgram() {
        Platform.exit();
        System.exit(0);
    }
}