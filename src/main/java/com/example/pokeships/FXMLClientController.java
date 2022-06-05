package com.example.pokeships;

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
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author ravar
 */
public class FXMLClientController implements Initializable {

    @FXML
    private Button buttonConectar;

    @FXML
    private Label labelClienteID;

    @FXML
    private Label labelClienteStatus;

    @FXML
    private Button back;

    @FXML
    private Label waitingLbl;

    @FXML
    private TextField textFieldIP;
    @FXML
    private TextField textFieldPorta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleButtonConectar() throws IOException, ClassNotFoundException{
        String ip = this.textFieldIP.getText();
        int porta = Integer.parseInt(this.textFieldPorta.getText());

        waitingLbl.setText("Waiting  another player on: " + ip + ":" + this.textFieldPorta.getText());

        Socket socket = new Socket(ip, porta);


        /*ThreadSocketsCliente threadSocketsCliente = new ThreadSocketsCliente(ip, porta, this.labelClienteID, this.labelClienteStatus);
        threadSocketsCliente.start();*/
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;

        if(event.getTarget() == back) {
            stage = (Stage) back.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
