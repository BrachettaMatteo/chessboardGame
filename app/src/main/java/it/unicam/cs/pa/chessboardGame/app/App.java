package it.unicam.cs.pa.chessboardGame.app;


import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * main class.
 * Its implement start UI game
 *
 * @author Matteo Brachetta
 */
public class App extends Application {

    /**
     * Identify current stage
     */
    static Stage currentStage;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/page/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("chessBoardGame");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }


    /**
     * lunch the ui chessBoard.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        launch();
    }


    /**
     * Get the current Stage
     *
     * @return the current Stage
     */
    public static Stage getCurrentStage() {
        return currentStage;
    }

    /**
     * Set the current Stage
     *
     * @param newCurrentStage new stage for <code>currentStage</code>
     */
    public static void setCurrentStage(Stage newCurrentStage) {
        App.currentStage = newCurrentStage;
    }
}