/**
 * Класс Юридическое лицо
 * @author Дмитрий Городенцев
 * @version 1.0
 */

package ru.eltex;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class YurUser extends User implements CSV {

    private static Logger logger = Logger.getLogger(YurUser.class.getSimpleName());


    /** Поле имени юр.лица */
    private String fio;

    /** Поле идентификатор юр.лица */
    private int id;

    /** Поле номера телефона юр.лица */
    private String phone;

    /** Поле ИНН юр.лица */
    private String inn;

    /** Конструктор - создание нового объекта с определенными значениями */
    public YurUser() {

        this.fio = " ";
        this.id = -1;
        this.phone = " ";
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

        this.id = index;
        this.fio = fio;
        this.phone = phone;
        this.inn = inn;
        index++;

        logger.debug("Создание объекта YurUser");
    }

    /**
     * метод получения поля inn
     * @param inn - ИНН юр.лица */
    public void setInn(String inn) {
        this.inn = inn;
    }

    /**
     * метод возвращает значение ИНН юр.лица
     * @return ИНН юр.лица */
    public String getInn() {
        return inn;
    }

    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @throws IOException - Если возникли ошибки при записи в файл
     */
    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(id + csvSplitter + fio + csvSplitter + phone + csvSplitter + inn + "\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }
    }
}
