
package ru.eltex;

import java.sql.*;

/**
 * Интерфейс для взаимодействия с СУБД MySQL
 * @author Городенцев Дмитрий gorodentsevd@gmail.com
 * @version 1.0.1
 */
public interface SQL {

    /**
     * Инициализация соединения с базой данных, отправка полей объектов в базу данных, закрытие соединения
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
     */
    void addToDB(String url, String user, String password);


    /**
     * Инициализация соединения с базой данных
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
     * @return Connection объект для осуществления работы методов отправки и закрытия соединения
     * @since 1.0.1
     */
    Connection connectToDB(String url, String user, String password);

    /**
     * Отправка полей объекта в базу данных
     * @param conn - реализация интерфейса Connection
     * @return PreparedStatement объект для осуществления работы метода закрытия соединения
     * @since 1.0.1
     */
    PreparedStatement sendToDB(Connection conn);

    /**
     * Закрытие соединения
     * @param conn - реализация интерфейса Connection
     * @param preparedStatement - реализация интерфейса PreparedStatement
     * @since 1.0.1
     */
    void closeConnection(Connection conn, PreparedStatement preparedStatement);


    /**
     * Метод вывода базы данных в консоль
     * @param url - адрес базы данных
     * @param user - логин
     * @param password - пароль
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
                String inn = resultSet.getString("inn");
                String snils = resultSet.getString("snils");

                System.out.println("\n==================\n");
                System.out.println("id: " + id);
                System.out.println("fio: " + fio);
                System.out.println("phone: " + phone);
                System.out.println("inn: " + inn);
                System.out.println("snils: " + snils);
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
     */
    static void resetTable(String url, String user, String password) {
        String SQL = "DELETE FROM users";
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
