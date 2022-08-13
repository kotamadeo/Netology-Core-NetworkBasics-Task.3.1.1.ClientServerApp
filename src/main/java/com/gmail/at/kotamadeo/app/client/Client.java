package com.gmail.at.kotamadeo.app.client;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
@Data
public class Client implements Runnable {
    private String message;
    private int port;
    private void getResponse(int port, String message) {
        try (Socket client = new Socket(InetAddress.getLocalHost(), port);
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            System.out.println("Подключились к хосту: " + client.getRemoteSocketAddress());
            out.println(message);
            String responseName = bufferedReader.readLine();
            String responseMessage = bufferedReader.readLine();
            System.out.printf("Ответ сервера: %n%s%n%s%n", responseName, responseMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        getResponse(port, message);
    }
}
