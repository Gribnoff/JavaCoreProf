package lesson_2_database;

import java.sql.*;

class Solution1 {
    private static Connection conn;
    private static Statement state;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            String tableName = "users";
            //создаём таблицу по шаблону
            createTable(tableName);
            //очищаем таблицу
            clear(tableName);

            //заполняем логинами и никами
            conn.setAutoCommit(false);
            for (int i = 0; i < 100; i++) {
                insert(tableName, i, "login" + i, "nick" + i);
            }
            conn.commit();

            //в каждой 3й записи добавляем -- к логину, в остальных к нику
            conn.setAutoCommit(false);
            for (int i = 0; i < 100; i++) {
                if (i % 3 == 0)
                    update(tableName, "login", "'login--" + i + "'", "id", String.valueOf(i));
                else
                    update(tableName, "nickname", "'nick--" + i + "'", "id", String.valueOf(i));
            }
            conn.commit();

            //удаляем каждую вторую запись
            conn.setAutoCommit(false);
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0)
                    delete(tableName, "id", String.valueOf(i));
            }
            conn.commit();

            //вывод всех столбцов от 50 записи
//            selectAll(tableName, "*");
            select(tableName, "*", "id", "> 50");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:l2.db");
        state = conn.createStatement();
    }

    private static void disconnect() throws SQLException {
        conn.close();
    }

    private static void createTable(String name) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " + name + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login    TEXT UNIQUE, " +
                "nickname TEXT UNIQUE)";
        state.execute(query);
    }

    private static void clear(String name) throws SQLException {
        String query = "DELETE FROM " + name;
        state.execute(query);
    }

    private static void insert(String tableName, int id, String login, String nick) throws SQLException {
        String query = String.format("INSERT INTO %s (id, login, nickname)" +
                "VALUES (%d, '%s', '%s')", tableName, id, login, nick);
        state.execute(query);
    }

    private static void update(String tableName, String columnToUpdate, String newValue, String columnWhere, String valueWhere) throws SQLException {
        String query = String.format("UPDATE %s SET %s=%s WHERE %s=%s", tableName, columnToUpdate, newValue, columnWhere, valueWhere);
        state.execute(query);
    }

    private static void delete(String tableName, String column, String value) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s=%s", tableName, column, value);
        state.execute(query);
    }

    private static void selectAll(String tableName, String column) throws SQLException {
        String query = String.format("SELECT %s FROM %s", column, tableName);
        ResultSet rs = state.executeQuery(query);
        if (column.equals("*"))
            while (rs.next()) {
                System.out.printf("Запись №%d: логин %s // ник %s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        else
            System.out.println(rs.getString(column));
    }

    private static void select(String tableName, String column, String columnWhere, String queryWhere) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s %s", column, tableName, columnWhere, queryWhere);
        ResultSet rs = state.executeQuery(query);
        if (column.equals("*"))
            while (rs.next()) {
                System.out.printf("Запись №%d: логин %s // ник %s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        else
            System.out.println(rs.getString(column));
    }
}
