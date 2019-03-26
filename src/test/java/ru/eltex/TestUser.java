package ru.eltex;

import org.apache.log4j.Logger;
import ru.eltex.CSV;
import ru.eltex.User;

import java.io.FileWriter;
import java.io.IOException;

public class TestUser implements CSV {

    public static String csvFile = "src/main/resources/phonebook.csv";

    private static Logger logger = Logger.getLogger(User.class.getSimpleName());

    /** Поле разделитель для CSV */
    String csvSplitter = ";";

    /** Поле идентификатор пользователя */
    private Integer id;

    /** Поле индекса, служит для инкрементации идентификатора */
    public static Integer index = 0;

    /** Поле имени пользователя */
    private String fio;

    /** Поле номера телефона */
    private String phone;

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

    public TestUser(String fio, String phone) {
        this.fio = fio;
        this.id = index;
        this.phone = phone;
        index++;

        logger.debug("Создание объекта User");
    }

    /** Конструктор - создание нового объекта с определенными значениями */
    public TestUser() {
        this.fio = " ";
        this.id = -1;
        this.phone = " ";
    }

    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @exception IOException - Если возникли ошибки при записи в файл
     */
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
}
