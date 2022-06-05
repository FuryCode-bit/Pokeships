package com.example.pokeships;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import com.example.pokeships.Board.Cell;

public class HelloController implements Initializable {

    private boolean music = true;
    private MediaPlayer player;

    @FXML
    private Button singleBtn, multiBtn, muteBtn;
    @FXML
    private Label Yboard;
    @FXML
    private Label Eboard;

    @FXML
    private GridPane EBoard;
    @FXML
    private GridPane MyBoard;

    private boolean running = false;

    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 800);
        root.setStyle("-fx-background-image: url(https://i.pinimg.com/736x/76/80/76/76807604812ac1f20b91c2136e7bcd96.jpg); -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center center;");

        Image image = new Image("file:///C:\\Users\\macob\\Desktop\\uniBooks\\segundoAno\\SegundoSemestre\\IHC\\pokeships\\src\\main\\resources\\res\\Enemys-Board.png");
        ImageView imgview = new ImageView(image);
        imgview.setFitHeight(60);
        imgview.setPreserveRatio(true);
        imgview.setTranslateY(10);
        imgview.setTranslateX(190);
        root.setTop(imgview);

        Image imagePlayer = new Image("file:///C:\\Users\\macob\\Desktop\\uniBooks\\segundoAno\\SegundoSemestre\\IHC\\pokeships\\src\\main\\resources\\res\\Your-Board.png");
        ImageView imgPlayer = new ImageView(imagePlayer);
        imgPlayer.setFitHeight(60);
        imgPlayer.setPreserveRatio(true);
        imgPlayer.setTranslateY(-350);
        imgPlayer.setTranslateX(190);
        root.setBottom(imgPlayer);



        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                Stage stage3 = new Stage();
                Parent root2 = null;

                try {
                    root2 = FXMLLoader.load(getClass().getResource("/gameEnd.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root2);
                stage3.setScene(scene);
                stage3.show();
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        VBox vbox = new VBox(20, enemyBoard, playerBoard);
        playerBoard.setTranslateY(50);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(12));

        root.setCenter(vbox);

        return root;
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
                Stage stage2 = new Stage();
                Parent root2 = null;

                try {
                    root2 = FXMLLoader.load(getClass().getResource("/gameEnd.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root2);
                stage2.setScene(scene);
                stage2.show();
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    public void comecaJogo(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void handleButton(ActionEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;

        if(event.getTarget() == singleBtn) {
            System.out.println("Singleplayer");
            stage=(Stage) singleBtn.getScene().getWindow();
            comecaJogo(stage);
        } else if(event.getTarget() == multiBtn) {
            System.out.println("Multiplayer");
            stage=(Stage) multiBtn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/multiplayer.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    private void playSound() {
        Media media = null;
        try {
            media = new Media(getClass().getResource("/pok.mp3").toURI().toString());
            player = new MediaPlayer(media);
            player.play();
            System.out.println("playinggg!!");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

   @FXML
    private void toggleSound() {
        if(music) {
            music = false;
            player.stop();
        } else if(!music) {
            music = true;
            player.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playSound();
    }

}