package it.unicam.cs.pa.chessboardGame.app;

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

import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Spinner;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author matteo Brachetta
 */
public class boardController implements Initializable {
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
     * List content all possible move for pawn.
     */
    ObservableList<String> move = FXCollections.observableArrayList(App.selectGame.getNameAllPossibleMove());
    /**
     * Opponent for player
     */
    private player opponentPlayer;
    /**
     * player game
     */
    private player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int max = App.selectGame.getBoard().getSize();
        SpinnerValueFactory<Integer> spinnerRowValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        SpinnerValueFactory<Integer> spinnerColumnValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max);
        SpinnerValueFactory<String> spinnerMoveValue = new SpinnerValueFactory.ListSpinnerValueFactory<>(move);
        spinnerSelectRow.setValueFactory(spinnerRowValue);
        spinnerSelectColumn.setValueFactory(spinnerColumnValue);
        spinnerSelectMove.setValueFactory(spinnerMoveValue);
        System.out.println(App.selectGame.getName());
        this.createBoard();
        this.updateBoard();
        opponentPlayer = App.selectGame.getBoard().getPawns().stream().filter(pawn -> !Objects.equals(pawn.getOwner().getName(), App.playerName)).toList().get(0).getOwner();
        player = App.selectGame.getBoard().getPawns().stream().filter(pawn -> Objects.equals(pawn.getOwner().getName(), App.playerName)).toList().get(0).getOwner();
    }

    /**
     * Create board ui for game. It's create box for content pawn.
     */
    private void createBoard() {
        int max = App.selectGame.getBoard().getSize();
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
        List<pawn> lp = new ArrayList<>(App.selectGame.getBoard().getPawns());
        for (pawn pw : lp) {
            this.addPawnToBoard(App.selectGame.getBoard().getPositionPawn(pw.getId()), pw);
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
            if (pawn.getType()) {
                c.setFill(Color.WHITE);
            } else c.setFill(Color.valueOf("#0BA358"));
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
        pawn selectPawn = App.selectGame.getBoard().getPawn(p);
        if (selectPawn != null && App.selectGame.getBoard().getPawnToMove(player.getId()).contains(selectPawn)) {
            this.labelError.setText("");
            this.checkMove(selectPawn);
        } else this.labelError.setText("error Position, the position isn't move");

    }

    /**
     * Check move and execute move
     *
     * @param selectPawn pawn to move
     */
    private void checkMove(pawn selectPawn) {
        switch (spinnerSelectMove.getValue()) {
            case "forward" -> {
                try {
                    selectPawn.getMovement().forward();
                    this.finishTurn();
                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "left" -> {
                try {
                    selectPawn.getMovement().left();
                    this.finishTurn();
                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "right" -> {
                try {
                    selectPawn.getMovement().right();
                    this.finishTurn();
                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "forwardLeft" -> {
                try {
                    selectPawn.getMovement().forwardLeft();
                    this.finishTurn();
                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }

            case "forwardRight" -> {
                try {
                    System.out.println("select FR");
                    selectPawn.getMovement().forwardRight();
                    this.finishTurn();

                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "back" -> {
                try {
                    selectPawn.getMovement().back();
                    this.finishTurn();
                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "backRight" -> {
                try {
                    selectPawn.getMovement().backRight();
                    this.finishTurn();

                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }
            case "backLeft" -> {
                try {
                    selectPawn.getMovement().backLeft();
                    this.finishTurn();

                } catch (Exception e) {
                    if (!selectPawn.getId().equals(opponentPlayer.getId()))
                        this.labelError.setText(e.getMessage());
                }
            }

            default -> this.labelError.setText("error Movement, the movement is not accepted by the pawn");
        }

    }

    /**
     * Executes the opponent's move and checks the win. If he finds the winner, lunch the <code>this.endGame()</code>.
     */
    private void finishTurn() {
        List<pawn> lp = App.selectGame.getBoard().getPawnToMove(opponentPlayer.getId());
        if (App.selectGame.getWin() == null) {
            int rand = ThreadLocalRandom.current().nextInt(0, lp.size());
            lp.get(rand).getMovement().randomMove();
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
        alert.setHeaderText("The " + App.selectGame.getWin().getName() + "is the winner");
        alert.setContentText("Do you want to Start a new game?");
        alert.getButtonTypes().setAll(buttonNewGame, buttonGoBack);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonNewGame) {
                App.selectGame.getBoard().restart();
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = App.getCurrentStage();
        stage.setScene(scene);
        App.setCurrentStage(stage);
        App.getCurrentStage().show();
    }
}
