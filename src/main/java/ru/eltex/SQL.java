/**
 * Интерфейс для взаимодействия с СУБД MySQL
 * @author Городенцев Дмитрий <gorodentsevd@gmail.com>
 * @version 1.0.0
 */



package ru.eltex;

import java.sql.*;

public interface SQL {


    /**
     * Метод записи полей объекта в базу данных
     * @exception SQLException - Если возникли ошибки при работе с базой данных
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
     */
    void addToDB(String url, String user, String password);

    /**
     * Метод вывода базы данных в консоль
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
     * @exception SQLException - Если возникли ошибки при работе с базой данных
     */
    static void showTable(String url, String user, String password){

        String SQL = "SELECT * FROM users";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fio = resultSet.getString("fio");
                String phone = resultSet.getString("phone");

                System.out.println("\n==================\n");
                System.out.println("id: " + id);
                System.out.println("fio: " + fio);
                System.out.println("phone: " + phone);
            }
                resultSet.close();
                statement.close();
                conn.close();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    /**
     * Метод для стирания полей базы данных
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
     * @exception SQLException - Если возникли ошибки при работе с базой данных
     */
    static void resetTable(String url, String user, String password) {
        String SQL = "DELETE FROM users WHERE id < 100";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement statement = conn.createStatement();

            statement.executeUpdate(SQL);

            statement.close();
            conn.close();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
