/**
 * Класс Юридическое лицо
 * @author Дмитрий Городенцев <gorodentsevd@gmail.com>
 * @version 1.0.2
 */

package ru.eltex;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class YurUser extends User implements CSV, SQL {

    private static Logger logger = Logger.getLogger(YurUser.class.getSimpleName());

    /** Поле ИНН юр.лица */
    private String inn;

    /** Конструктор - создание нового объекта с определенными значениями */
    public YurUser() {

        this.inn = " ";
        index++;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param fio   - имя юр.лица
     * @param phone - номер телефона юр.лица
     * @param inn - ИНН юр.лица
     */
    public YurUser(String fio, String phone, String inn) {

        super(fio, phone);
        this.inn = inn;


        logger.debug("Создание объекта YurUser");
    }

    /**
     * метод получения поля inn
     * @param inn - ИНН юр.лица */
    public void setINN(String inn) {
        this.inn = inn;
    }

    /**
     * метод возвращает значение ИНН юр.лица
     * @return ИНН юр.лица */
    public String getINN() {
        return inn;
    }

    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(this.getId() + csvSplitter + this.getFio() + csvSplitter + this.getPhone() +
                    csvSplitter + inn + "\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }
    }

    @Override
    public void addToDB(String url, String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String SQL = "INSERT INTO users(id, fio, phone, inn) VALUE (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, this.getId());
            preparedStatement.setString(2, this.getFio());
            preparedStatement.setString(3, this.getPhone());
            preparedStatement.setString(4, this.getINN());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
