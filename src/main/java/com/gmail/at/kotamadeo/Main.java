package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.app.client.Client;
import com.gmail.at.kotamadeo.app.server.Server;

public class Main {
    private static final int port = 8080;

    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client();
        server.setPort(port);
        client.setPort(port);
        client.setMessage("Игорь-Тестовое сообщение.Не уверен, что сделал правильно нативную многопоточку, " +
                "так как на работе используются библиотеки, да и я с ней не работаю особо)");
        Thread serverThread = new Thread(server);
        Thread clientThread = new Thread(client);
        serverThread.start();
        clientThread.start();
    }
}