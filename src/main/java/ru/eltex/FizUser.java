/** Класс Физическое лицо
 * @author Дмитрий Городенцев <gorodentsevd@gmail.com>
 * @version 1.0.2
 */

package ru.eltex;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.*;

public class FizUser extends User implements CSV, SQL {

    private static Logger logger = Logger.getLogger(FizUser.class.getSimpleName());

    /** Поле ИНН физ.лица */
    private String inn;

    /** Поле СНИЛС физ.лица */
    private String snils;

    /** Конструктор - создание нового объекта с определенными значениями */
    public FizUser() {

        this.snils = " ";
        index++;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param fio   - имя физ.лица
     * @param phone - номер телефона физ.лица
     * @param inn - ИНН физ.лица
     * @param snils - СНИЛС физ.лица
     */
    public FizUser(String fio, String phone, String inn, String snils) {

        super(fio, phone);
        this.inn = inn;
        this.snils = snils;

        logger.debug("Создание объекта FizUser");
    }

    /** Задает значение поля snils физ.лица
     * @param snils - СНИЛС физ.лица */
    public void setSnils(String snils) {
        this.snils = snils;
    }

    /** Задает значение поля snils физ.лица
     * @param inn - СНИЛС физ.лица */
    public void setINN(String inn) {
        this.inn = inn;
    }


    /**
     * метод возвращает значение СНИЛС Физ.лица
     * @return СНИЛС */
    public String getSnils() {
        return snils;
    }

    /**
     * метод возвращает значение СНИЛС Физ.лица
     * @return СНИЛС */
    public String getINN() {
        return inn;
    }

    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(this.getId() + csvSplitter + this.getFio() + csvSplitter + this.getPhone() +
                    csvSplitter + inn + csvSplitter + snils + "\n");
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

            String SQL = "INSERT INTO users VALUE (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, this.getId());
            preparedStatement.setString(2, this.getFio());
            preparedStatement.setString(3, this.getPhone());
            preparedStatement.setString(4, this.getINN());
            preparedStatement.setString(5, this.getSnils());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

}
