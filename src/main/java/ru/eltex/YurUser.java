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
        index++;

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

    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @throws IOException - Если возникли ошибки при записи в файл
     */
    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(this.getId() + csvSplitter + this.getFio() + csvSplitter + this.getPhone() + csvSplitter + inn + "\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }
    }
}
