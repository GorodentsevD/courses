
package ru.eltex;

import org.apache.log4j .*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

/**
 * Класс пользователя
 * @author Дмитрий Городенцев gorodentsevd@gmail.com
 * @version 1.0.1
 */
public abstract class User implements CSV, SQL {

    public static String csvFile = "src/main/resources/phonebook.csv";

    private static Logger logger = Logger.getLogger(User.class.getSimpleName());

    /** Поле разделитель для CSV */
    public String  csvSplitter = ";";

    /** Поле идентификатор пользователя */
    private Integer id;

    /** Поле индекса, служит для инкрементации идентификатора */
    public static Integer index = 0;

    /** Поле имени пользователя */
    private String fio = null;

    /** Поле номера телефона */
    private String phone = null;



    /**
     * Задает значение поля id
     * @param id - идентификатор
     */
    public void setID(Integer id) {
        this.id = id;
    }

    /**
     * Задает значение поля fio
     * @param fio - имя пользователя
     */
    public void setFio(String fio) {
        this.fio = fio;
    }

    /**
     * Метод возвращает идентификатор пользователя
     * @return идентификатор */
    public Integer getId() {
        return this.id;
    }

    /**
     * Метод возвращает номер телефона пользователя
     * @return номер телефона */
    public String getPhone() { return this.phone; }

    /**
     *  метод возвращает ФИО пользователя
     * @return имя пользователя */
    public String getFio() {
        return this.fio;
    }


    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param fio   - имя пользователя
     * @param phone - номер телефона пользователя
     */
    public User(String fio, String phone) {
        this.fio = fio;
        this.id = index;
        this.phone = phone;
        index++;

        logger.debug("Создание объекта User");
    }

    /** Конструктор - создание нового объекта с определенными значениями */
    public User() {
        this.fio = " ";
        this.id = -1;
        this.phone = " ";
    }

    @Override
    public void toCSV() {
        logger.info("запись в CSV");

        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(id + csvSplitter + fio + csvSplitter + phone + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }
    }

    @Override
    public void addToDB(String url, String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String SQL = "INSERT INTO users(id, fio, phone) VALUE (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, this.getId());
            preparedStatement.setString(2, this.getFio());
            preparedStatement.setString(3, this.getPhone());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    @Override
    public Connection connectToDB(String url, String user, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return conn;
    }

    @Override
    public PreparedStatement sendToDB(Connection conn) {
        PreparedStatement preparedStatement = null;
        try {
            String SQL = "INSERT INTO users(id, fio, phone) VALUE (?, ?, ?)";
            preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, this.getId());
            preparedStatement.setString(2, this.getFio());
            preparedStatement.setString(3, this.getPhone());

            preparedStatement.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return preparedStatement;
    }

    @Override
    public void closeConnection(Connection conn, PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}























