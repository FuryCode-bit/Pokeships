package com.example.pokeships;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ravar
 */

public class ThreadSocketsConexao extends Thread {

    private int port;
    private int qtdClientes;

    public ThreadSocketsConexao(int port, int qtdClientes) {
        this.port = port;
        this.qtdClientes = qtdClientes;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;

        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Waiting players to join the game!");

            while (FXMLServidorController.qtdClientes < 2) {
                System.out.println("ChegoU!");
                Socket socket = serverSocket.accept();

                //Mostrando endereço IP do cliente conectado
                System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado");
                FXMLServidorController.qtdClientes++;
                System.out.println(FXMLServidorController.qtdClientes);
                System.out.println(FXMLServidorController.vetLabelStatus[qtdClientes]);
                ThreadSocketsServidor thread = new ThreadSocketsServidor(socket, FXMLServidorController.vetLabelStatus[qtdClientes++]);
                thread.start();
            }

        } catch(IOException e) {
            System.out.println(e);
        }

    }

}
