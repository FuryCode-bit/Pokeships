package com.example.pokeships;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author ravar
 */
public class FXMLServidorController extends Thread implements Initializable {

    static int qtdClientes = 0;
    int port;

    @FXML
    private Button buttonIniciarServidor;

    @FXML
    private TextField textFieldPorta;
    @FXML
    private Label labelCliente1Status, labelCliente2Status;

    @FXML
    private Label infoLbl;

    public static Label vetLabelStatus[] = new Label[2];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vetLabelStatus[0] = this.labelCliente1Status;
        vetLabelStatus[1] = this.labelCliente2Status;

    }

    @FXML
    public void handleButtonIniciarServidor() throws IOException {
        String num = textFieldPorta.getText();
        infoLbl.setText("Running on port: " + num);
        int port = Integer.parseInt(num);
        System.out.println("Porta: " + port);

        ThreadSocketsConexao thread = new ThreadSocketsConexao(port , FXMLServidorController.qtdClientes);
        thread.start();

    }
}