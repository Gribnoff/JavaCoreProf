package lesson_2.database.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

class Server {
    private Vector<ClientHandler> clients;

    Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                 new ClientHandler(this, socket);
               // clients.add(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert socket != null;
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    void broadcastMsg(ClientHandler from, String msg) throws IOException {
        for (ClientHandler o : clients) {
            if(!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
    }

    boolean isNickBusy(String nick) {
        for (ClientHandler o: clients){
            if(o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    private void broadcastClientList() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientList ");
        for (ClientHandler o: clients) {
            sb.append(o.getNick());
            sb.append(" ");
        }
        String out = sb.toString();

        for(ClientHandler o: clients) {
            o.sendMsg(out);
        }
    }

    void sendPersonalMsg(ClientHandler from, String nickTo, String msg) throws IOException {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + ": " + msg);
    }

    void subscribe(ClientHandler client) throws IOException {
        clients.add(client);
        broadcastClientList();
    }

    void unsubscribe(ClientHandler client) throws IOException {
        clients.remove(client);
        broadcastClientList();
    }
}
