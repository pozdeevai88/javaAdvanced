package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server {

    List<ClientHandler> clients = new ArrayList<>();
    List<Message> messages = new ArrayList<>();

    Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            AuthService authService = new AuthService();
            // Обработчик клиентов
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    new ClientHandler(authService, this, socket);
                }).start();
            }
        } catch (IOException e) {
            System.out.println("Сервер прекратил работу с ошибкой");
            e.printStackTrace();
        }
    }

    synchronized void onNewMessage(Client client, String message) {
        String[] messageData = message.split(" ");

        if (messageData[0].equals("/w")) {
            // Личное сообщение
            for (int i = 0; i < clients.size(); i++) {
                ClientHandler recipient = clients.get(i);
                if (recipient.client.name.equals(messageData[1])) {
                    recipient.sendMessage(client, messageData[2]);
                } else {
                    System.out.println("Имя указано неверно, или клиент не вошёл в чат");
                }
            }
        } else {
            // Общее сообщение
            messages.add(new Message(client, message));

            for (int i = 0; i < clients.size(); i++) {
                ClientHandler recipient = clients.get(i);
                if (!recipient.client.login.equals(client.login)) {
                    recipient.sendMessage(client, message);
                }
            }
        }
    }

    synchronized void onNewClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            clientHandler.sendMessage(message.client, message.text);
        }
        onNewMessage(clientHandler.client, "Вошел в чат");
    }

    synchronized void onClientDisconnected(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        onNewMessage(clientHandler.client, "Покинул чат");
    }

    public static void main(String[] args) {
        new Server();
    }
}