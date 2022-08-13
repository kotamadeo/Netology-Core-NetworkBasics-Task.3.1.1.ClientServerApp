package com.gmail.at.kotamadeo.app.server;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Data
public class Server implements Runnable {
    private int port;

    private void setConnection(int port) {
        try (ServerSocket socket = new ServerSocket(port);
             Socket client = socket.accept();
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            System.out.println("Новое соединение подтверждено!");
            String[] response = bufferedReader.readLine().split("-");
            out.println(String.format("Привет %s, порт клиента - %d%nСообщение ответа: %s",
                    response[0], client.getPort(), response[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        setConnection(port);
    }
}
