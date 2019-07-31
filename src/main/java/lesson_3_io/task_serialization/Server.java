package lesson_3_io.task_serialization;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String[] args) {
        new Server();
    }


    private Server() {
        Socket socket = null;
        ObjectInputStream in = null;
        try (ServerSocket server = new ServerSocket(8189)){

            System.out.println("Сервер запущен.");
            while (socket == null) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                in = new ObjectInputStream(socket.getInputStream());

            }

            while (true) {
                Player player = (Player) in.readObject();
                System.out.print("На сервере получен ");
                player.info();
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
