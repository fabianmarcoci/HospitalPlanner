package org.example.HospitalPlanner.service.network;

import org.example.HospitalPlanner.service.network.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
