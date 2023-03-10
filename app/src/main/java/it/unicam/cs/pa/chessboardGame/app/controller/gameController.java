package it.unicam.cs.pa.chessboardGame.app.controller;

import it.unicam.cs.pa.chessboardGame.app.App;
import it.unicam.cs.pa.chessboardGame.structure.pawn;
import it.unicam.cs.pa.chessboardGame.structure.player;
import it.unicam.cs.pa.chessboardGame.structure.position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.List;

/**
 * Controller for game screen
 *
 * @author matteo Brachetta
 */
public class gameController implements Initializable {
    /**
     * (UI) Spinner for input <code>movement</code>.
     */
    @FXML
    public Spinner<String> spinnerSelectMove;
    /**
     * (UI) Spinner for input <code>position</code> roe.
     */
    @FXML
    public Spinner<Integer> spinnerSelectRow;
    /**
     * (UI) Spinner for input <code>position</code> column.
     */
    @FXML
    public Spinner<Integer> spinnerSelectColumn;
    /**
     * (UI) Label to show error.
     */
    @FXML
    public Label labelError;
    /**
     * (UI) Grid for content chessboard
     */
    @FXML
    public GridPane chessBoard;
    /**
     * (UI) button for return to main screen
     */
    @FXML
    public Button btnGoBack;
    /**
     * List content all possible move for {@code pawn}.
     */
    ObservableList<String> move = FXCollections.observableArrayList(mainController.selectGame.getNameAllPossibleMove());
    /**
     * Opponent for {@code player}
     */
    private player opponentPlayer;
    /**
     * {@code player} game
     */
    private player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int max = mainController.selectGame.getBoard().getSize();
        SpinnerValueFactory<Integer> spinnerRowValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        SpinnerValueFactory<Integer> spinnerColumnValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        SpinnerValueFactory<String> spinnerMoveValue = new SpinnerValueFactory.ListSpinnerValueFactory<>(move);
        spinnerSelectRow.setValueFactory(spinnerRowValue);
        spinnerSelectColumn.setValueFactory(spinnerColumnValue);
        spinnerSelectMove.setValueFactory(spinnerMoveValue);
        System.out.println(mainController.selectGame.getName());
        this.createBoard();
        this.updateBoard();
        Image imageDecline = new Image("img/icon/icons8-back-arrow-ios-16-32.png");
        btnGoBack.setGraphic(new ImageView(imageDecline));
        opponentPlayer = mainController.selectGame.getBoard().getPawns().stream().filter(pawn -> !Objects.equals(pawn.getOwner().getName(), mainController.playerName)).toList().get(0).getOwner();
        player = mainController.selectGame.getBoard().getPawns().stream().filter(pawn -> Objects.equals(pawn.getOwner().getName(), mainController.playerName)).toList().get(0).getOwner();
    }

    /**
     * Create board ui for game. It's create box for content pawn.
     */
    private void createBoard() {
        int max = mainController.selectGame.getBoard().getSize();
        int count = 1;
        for (int row = max; row > 0; row--) {
            count++;
            for (int column = 1; column < max + 1; column++) {
                Pane box = new Pane();
                box.setId("[" + column + " - " + row + "]");
                box.setStyle("-fx-pref-width: 50px;-fx-pref-height: 50px;-fx-background-color: #000000");
                if (count % 2 == 0)
                    box.setStyle("-fx-pref-width: 50px;-fx-pref-height: 50px;-fx-background-color: #E9B470");
                chessBoard.add(box, column, 8 - row);
                count++;
            }
        }
    }

    /**
     * Update the board. Rearrange the pawn in the board with the correct position.
     */
    private void updateBoard() {
        this.freePanels();
        List<pawn> lp = new ArrayList<>(mainController.selectGame.getBoard().getPawns());
        for (pawn pw : lp) {
            this.addPawnToBoard(mainController.selectGame.getBoard().getPositionPawn(pw.getId()), pw);
        }
    }

    /**
     * Remove content of all panes.
     */
    private void freePanels() {
        List<Node> listPane = this.chessBoard.getChildren().stream().filter(element -> {
            Pane p = (Pane) element;
            return !p.getChildren().isEmpty();
        }).toList();
        for (Node panel : listPane) {
            this.freePane(panel);
        }
    }

    /**
     * Remove content of pawn.
     *
     * @param panel node to free.
     */
    private void freePane(Node panel) {
        Pane pane = (Pane) this.chessBoard.getChildren().stream().filter(node -> node.getId().equals(panel.getId())).toList().get(0);
        pane.getChildren().clear();

    }

    /**
     * Add Pawn to Board.
     *
     * @param position position to insert new pawn.
     * @param pawn     pawn for insert.
     */
    private void addPawnToBoard(position position, pawn pawn) {
        Pane select = (Pane) chessBoard.getChildren().stream().filter(box -> box.getId().equals(position.toString())).findAny().orElse(null);
        Circle c = new Circle(20, 20, 18);
        if (select != null) {
            c.setId("pawn");
            c.setFill(new ImagePattern(pawn.getImage()));
            select.getChildren().add(c);
        } else System.out.println("select is null");
    }

    /**
     * Action connected to the button. Performs the movement entered by the player.
     * It's check and execute action for move pawn.
     */
    @FXML
    public void move() {

        position p = new position(this.spinnerSelectColumn.getValue(), this.spinnerSelectRow.getValue());
        pawn selectPawn = mainController.selectGame.getBoard().getPawn(p);
        try {
            player.executeMove(selectPawn, this.spinnerSelectMove.getValue().toUpperCase());
            this.labelError.setText("");
            this.finishTurn();
        } catch (Exception e) {
            this.labelError.setText(e.getMessage());
        }

    }


    /**
     * Executes the opponent's move and checks the win. If he finds the winner, lunch the <code>this.endGame()</code>.
     */
    private void finishTurn() {
        if (mainController.selectGame.getWin() == null) {
            opponentPlayer.executeAutomaticMove(mainController.selectGame.getBoard());
        } else {
            try {
                this.endGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.updateBoard();
    }

    /**
     * Get information for end game and show alert.
     *
     * @throws IOException if not correct work FXMLLoader
     */
    private void endGame() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType buttonNewGame = new ButtonType("new game");
        ButtonType buttonGoBack = new ButtonType("go back");
        alert.setTitle("End Game");
        alert.setHeaderText("The " + mainController.selectGame.getWin().getName() + " is the winner");
        alert.setContentText("Do you want to Start a new game?");
        alert.getButtonTypes().setAll(buttonNewGame, buttonGoBack);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonNewGame) {
                mainController.selectGame.getBoard().restart();
                this.updateBoard();
            } else {
                this.goBack();
            }
        }
    }

    /**
     * Return to back screen, <code>main-view.fxml</code>.
     *
     * @throws IOException if not correct work <code>FXMLLoader</code>.
     */
    public void goBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/page/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.getCurrentStage();
        stage.setScene(scene);
        App.setCurrentStage(stage);
        App.getCurrentStage().show();
    }
}
