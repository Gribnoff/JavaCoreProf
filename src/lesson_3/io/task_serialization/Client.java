package lesson_3.io.task_serialization;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    public static void main(String[] args) {
        new Client();
    }

    private Client() {
        try (Socket socket = new Socket(IP_ADDRESS, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Клиент подключился клиент");

            while (true) {
                System.out.println("Введите через пробел сначала возраст, потом имя объекта Player(для выхода введите -1):");
                int age = sc.nextInt();
                if (age == -1)
                    break;
                String name = sc.nextLine();

                Player player = new Player(age, name);
                out.writeObject(player);
                System.out.println("Отправляем объект на сервер");
            }

        } catch (IOException e) {e.printStackTrace();}
    }
}
