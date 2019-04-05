
package ru.eltex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Телефонная книга
 * @author Дмитрий Городенцев gorodentsevd@gmail.com
 * @version 1.0.2
 */
public class Main {

    /**
     * Поле для адреса базы данных
     * @since 1.0.2
     */
    public static String url = "jdbc:mysql://127.0.0.1:3306/PhoneBook";

    /**
     * Поле логин для авторизации в СУБД
     * @since 1.0.2
     */
    public static String user = "root";

    /**
     *  Поле пароль для авторизации в СУБД
     *  @since 1.0.2
     */
    public static String password = "00000000";

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        //User user1 = new User("Boris", "900" );
        //User user2 = new User("Alex", "800");

        FizUser fizUser1 = new FizUser("Dima", "999", "777777", "8111111111");
        YurUser yurUser1 = new YurUser("Eltex", "500", "22222");

        //users.add(user1);
        //users.add(user2);
        users.add(fizUser1);
        users.add(yurUser1);

        /*  запись в csv файл

        user1.toCSV();
        user2.toCSV();

        fizUser1.toCSV();
        yurUser1.toCSV();

        System.out.println("\n");
        CSV.fromCSV();

        System.out.println("\nIndex: " +User.index);

        CSV.cleanCSV();

        System.out.println("fizUser1 fio: " + fizUser1.getFio());
        System.out.println("fizUser1 phone: " + fizUser1.getPhone());
        System.out.println("fizUser1 inn: " + fizUser1.getINN());
        System.out.println("fizUser1 snils: " + fizUser1.getSnils() + "\n");

        System.out.println("yurUser1 fio: " + yurUser1.getFio());
        System.out.println("yurUser1 phone: " + yurUser1.getPhone());
        System.out.println("yurUser1 inn: " + yurUser1.getINN());

        */

        SQL.resetTable(url, user, password);

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        conn = fizUser1.connectToDB(url, user, password);
        preparedStatement = fizUser1.sendToDB(conn);
        fizUser1.closeConnection(conn, preparedStatement);

        conn = null;
        preparedStatement = null;

        conn = yurUser1.connectToDB(url, user, password);
        preparedStatement = yurUser1.sendToDB(conn);
        yurUser1.closeConnection(conn, preparedStatement);



        SQL.showTable(url, user, password);

    }
}

