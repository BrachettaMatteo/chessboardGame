package it.unicam.cs.pa.chessboardGame.app;

import it.unicam.cs.pa.chessboardGame.app.games.dama.damaGame;
import it.unicam.cs.pa.chessboardGame.structure.game;
import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 */
public class App extends Application implements Initializable {
    /**
     * (UI) grid selection game
     */
    @FXML
    public GridPane gridSelectGame;
    /**
     * (UI) label for show error of textField<code>namePlayer</code>
     */
    @FXML
    public Label labelErrorName;
    /**
     * (UI) btn for start game
     */
    @FXML
    public Button btnStartGame;
    /**
     * (UI) TextField for insert name player
     */
    @FXML
    private TextField namePlayer;
    /**
     * Identify current stage
     */
    static Stage currentStage;
    /**
     * List content all Game
     */
    public ArrayList<game> listGame = new ArrayList<>(List.of(
            new damaGame("test"),
            new testGame("game 1"),
            new testGame("game 2"),
            new testGame("game 3"),
            new testGame("game 4"),
            new testGame("game 5"),
            new testGame("game 6"),
            new testGame("game 7")
    ));
    /**
     * Identify game select
     */
    static game selectGame;
    /**
     * Name of player
     */
    static String playerName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerName = "";
        gridSelectGame.setHgap(5);
        btnStartGame.setDisable(true);
        labelErrorName.setWrapText(true);

        this.createButtonForGame();
        this.checkName();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("chessBoardGame");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }

    /**
     * Add listener to textField and check error
     */
    private void checkName() {
        namePlayer.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (namePlayer.getText().toUpperCase().contains("BOT")) {
                    namePlayer.setText("");
                    namePlayer.setStyle("-fx-border-color: red");
                    labelErrorName.setText("the playerName cannot be contain the word Bot");
                    btnStartGame.setDisable(true);

                } else if (namePlayer.getText().isEmpty()) {
                    labelErrorName.setText("the playerName cannot be empty");
                    btnStartGame.setDisable(true);
                } else {
                    namePlayer.setStyle("");
                    labelErrorName.setText("");
                    btnStartGame.setDisable(false);
                }
            } else {
                labelErrorName.setText("");
                btnStartGame.setDisable(true);
            }

        });
    }

    /**
     * Generates buttons for each game in the list <code>listGame</code>
     */
    private void createButtonForGame() {
        for (int i = 0; i < listGame.size(); i++) {
            Button btn = new Button();
            btn.setId(String.valueOf(i));
            btn.getStyleClass().add("btnChoice");
            btn.setText(listGame.get(i).getName());
            btn.setOnAction(event -> {
                if (!this.namePlayer.getText().isEmpty() && !playerName.equals(this.namePlayer.getText())) {
                    selectGame = listGame.get(Integer.parseInt(btn.getId()));
                    selectGame.addPlayer(namePlayer.getText());
                    btnStartGame.setDisable(false);
                    playerName = namePlayer.getText();
                }
            });
            gridSelectGame.add(btn, i, 0);
        }
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
     * Action for start game. It redirects to page <code>board-view</code> for play game
     *
     * @throws IOException if FXMLLoader is generate error
     */
    @FXML
    public void startGame() throws IOException {
        if (!playerName.isEmpty() || selectGame != null) {
            selectGame.start();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/board-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            currentStage.setScene(scene);
            currentStage.show();
        }
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