package lesson_6.logsandtests.task1_chat_loggin.server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

class Server {
    private final Logger logger;
    private Vector<ClientHandler> clients;

    Logger getLogger() {
        return logger;
    }

    Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        logger = Logger.getLogger("servlog");

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            logger.info("Сервер запущен");
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                logger.info(String.format("Клиент подключился (%s)", socket));
                 new ClientHandler(this, socket);
               // clients.add(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.fatal("Server closed");
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

    void broadcastMsg(ClientHandler from, String msg) throws IOException, SQLException {
        for (ClientHandler o : clients) {
            if(!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
        AuthService.saveReplica(from.getNick(), msg);
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
        logger.info(String.format("Клиент(%s) зашёл в чат", client.getNick()));
        broadcastClientList();
    }

    void unsubscribe(ClientHandler client) throws IOException {
        clients.remove(client);
        logger.info(String.format("Клиент(%s) вышел из чата", client.getNick()));
        broadcastClientList();
    }
}
