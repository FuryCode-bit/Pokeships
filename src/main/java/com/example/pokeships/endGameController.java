package com.example.pokeships;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class endGameController implements Initializable {
    @FXML
    private Button backMenu;

    @FXML
    private ImageView bg;

    @FXML
    private ImageView title;

    @FXML
    public void handleBtn(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;

        if(event.getTarget() == backMenu) {
            stage = (Stage) backMenu.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
