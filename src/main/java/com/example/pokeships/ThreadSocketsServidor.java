package com.example.pokeships;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ThreadSocketsServidor extends Thread {

    private Socket socket;
    private Label labelStatus;

    public ThreadSocketsServidor(Socket s, Label labelStatus) {
        this.socket = s;
        this.labelStatus = labelStatus;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {

            //Saída de Dados do Servidor
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

            //Entrada de Dados no Servidor
            DataInputStream entrada = new DataInputStream(socket.getInputStream());


            Platform.runLater(()->labelStatus.setText("Waiting to play!"));

            socket.close();
        } catch (IOException ioe) {
            System.out.println("Erro: " + ioe.toString());
        }
    }
}
