package ru.geekbrains;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoServer {


    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8222)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ThreadForTX threadForTX = new ThreadForTX(out);
            ThreadForEcho threadForEcho = new ThreadForEcho(in, out);
            threadForTX.start();
            threadForEcho.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ThreadForEcho extends Thread {
        DataInputStream in;
        DataOutputStream out;

        public ThreadForEcho(DataInputStream in, DataOutputStream out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            while (true) {
                String str = null;
                try {
                    str = in.readUTF();
                    if (str.equals("/end")) {
                        break;
                    }
                    System.out.println("Сообщение от клиента: "+ str);
                    out.writeUTF("Эхо: " + str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadForTX extends Thread {
        DataOutputStream out;
        Scanner sc = new Scanner(System.in);

        public ThreadForTX(DataOutputStream out) {
            this.out = out;
        }

        @Override
        public void run() {
            String str = sc.nextLine();
            while (!str.equals("")) {
                try {
                    out.writeUTF("Сообщение от сервера: " + str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                str = sc.nextLine();
            }
        }
    }
}

