package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.gui.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/sample.fxml"));
        primaryStage.setOnCloseRequest((e) -> Controller.closeProgram());
        primaryStage.setTitle("Modelowanie wieloskalowe lab3-5 - Rozrost ziaren + Monte carlo");
        primaryStage.setHeight(400);
        primaryStage.setWidth(790);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(790);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
