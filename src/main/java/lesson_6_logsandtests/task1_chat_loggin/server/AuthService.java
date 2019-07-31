package lesson_6_logsandtests.task1_chat_loggin.server;

import java.sql.*;

class AuthService {

    private static Connection connection;
    private static Statement stmt;

    static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userTestDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static String getNickByLoginAndPass(String login, String pass) throws SQLException {

        String sql = String.format("select nickname FROM userTable where" +
                " login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()) {
            return rs.getString(1);
        }
        return null;
    }


    static void disconnect() throws SQLException {
        connection.close();
    }

    static void saveReplica(String nick, String text) throws SQLException {
        String query = String.format("INSERT INTO chatHistory (nick, replica) " +
                "VALUES ('%s', '%s')", nick, text);
        stmt.execute(query);
    }

    static ResultSet getHistory() throws SQLException {
        return stmt.executeQuery("SELECT * FROM chatHistory");
    }

}
