package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    private boolean areShipsPlaced = false;
    private Board enemyBoard, playerBoard;
    private int shipsToPlace = 10;
    private boolean enemyTurn = false;
    private int shipSize = 4;
    private Random random = new Random();

    private Parent createContent() {

        BorderPane board = new BorderPane();
        board.setPrefSize(700, 380);
        board.setPadding(new Insets(10,0,0,0));
        Label title = new Label("Welcome to very, very simple game of BattleShips! \n" +
                "Press left mouse button to place your ship vertically or press right mouse" +
                " button to place ship horizontally." );
        title.setStyle("-fx-font-weight: bold");
        title.setTextAlignment(TextAlignment.CENTER);
        board.setTop(title);
        board.setAlignment(title, Pos.CENTER);
        Label pBoard = new Label("PLAYER BOARD");
        Label eBoard = new Label("ENEMY BOARD");
        HBox bottomLabels = new HBox();
        board.setBottom(bottomLabels);
        bottomLabels.setHgrow(eBoard, Priority.ALWAYS);
        bottomLabels.setHgrow(pBoard,Priority.ALWAYS);
        eBoard.setMaxWidth(Double.MAX_VALUE);
        pBoard.setMaxWidth(Double.MAX_VALUE);
        eBoard.setAlignment(Pos.CENTER);
        pBoard.setAlignment(Pos.CENTER);
        pBoard.setStyle("-fx-font-weight: bold");
        eBoard.setStyle("-fx-font-weight: bold");
        bottomLabels.getChildren().addAll(eBoard,pBoard);

        enemyBoard = new Board(true, event -> {
            if (!areShipsPlaced)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (areShipsPlaced)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipSize, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {

                if (--shipsToPlace == 0) {
                    startGame();
                }
                if (shipsToPlace == 10) {
                    shipSize--;
                }
                if(shipsToPlace == 9) {
                    shipSize--;
                }
                if(shipsToPlace == 7) {
                    shipSize--;
                }
                if(shipsToPlace == 4) {
                    shipSize--;
                }

            }
        });

        HBox hBox = new HBox(30, enemyBoard, playerBoard);
        hBox.setAlignment(Pos.CENTER);
        board.setCenter(hBox);
        return board;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {

        int shipSize = 4;
        int shipsToPlace = 10;
        while (shipSize > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(shipSize, Math.random() < 0.5), x, y)) {

                if(shipsToPlace == 10) {
                    shipSize--;
                }
                if(shipsToPlace == 8) {
                    shipSize--;
                }
                if(shipsToPlace == 5) {
                    shipSize--;
                }
                if(shipsToPlace == 1) {
                    shipSize--;
                }
                shipsToPlace--;
            }
        }

        areShipsPlaced = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Sea Battle");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
