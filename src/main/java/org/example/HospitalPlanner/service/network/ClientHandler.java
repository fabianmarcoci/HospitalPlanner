package org.example.HospitalPlanner.service.network;

import org.example.HospitalPlanner.service.AccountManager;
import org.example.HospitalPlanner.config.ApplicationContextProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String username = in.readLine();
            String password = in.readLine();

            AccountManager accountManager = ApplicationContextProvider.getApplicationContext().getBean(AccountManager.class);
            String role = accountManager.submitLogin(username, password);

            if (role != null) {
                out.println(role);
            } else {
                out.println("Failed");
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
