package lesson_6.logsandtests.task1_chat_loggin.server;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ArrayList<String> blackList;
    private final Logger logger;

    String getNick() {
        return nick;
    }

    boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    private String nick;

    ClientHandler(Server server, Socket socket) {
        logger = server.getLogger();
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();
//            blackList.add("nick2");
            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/auth")) {
                            String[] tokens = str.split(" ");
                            String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);

                            if(newNick != null) {
                                if(!server.isNickBusy(newNick)) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(this);

                                    ResultSet history = AuthService.getHistory();
                                    while (history.next()) {
                                        String from = history.getString(1);
                                        if (!blackList.contains(from))
                                            sendMsg(history.getString(1) + " " + history.getString(2));
                                    }

                                    break;
                                } else {
                                    logger.info(String.format("Клиент(%s) пытался повторно зайти в чат", socket));
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                logger.info(String.format("Клиент(%s) пытался зайти в чат, используя некорректные данные", socket));
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }
                    while (true) {
                        String str = in.readUTF();
                        if(str.startsWith("/")) {
                            logger.info(String.format("Клиент(%s) прислал команду: %s", nick, str));
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }

                            if(str.startsWith("/w ")) {
                                String[] tokens = str.split(" ",3);
                                server.sendPersonalMsg(this, tokens[1], tokens[2]);
                               // String[] tokens = str.split(" ");
                            }

                            if(str.startsWith("/blacklist ")) {
                                String[] tokens = str.split(" ");
                                blackList.add(tokens[1]);
                                sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                            }

                        } else {
                            logger.info(String.format("Клиент(%s) прислал сообщение: %s", nick, str));
                            server.broadcastMsg(this,nick + " " + str);
                        }
                        System.out.println("Client: " + str);
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        server.unsubscribe(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.setDaemon(true);
            t1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendMsg(String msg) throws IOException {
        out.writeUTF(msg);
    }
}
