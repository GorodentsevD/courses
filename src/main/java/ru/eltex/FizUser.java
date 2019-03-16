/** Класс Физическое лицо
 * @author Дмитрий Городенцев
 * @version 1.0
 */

package ru.eltex;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.*;

public class FizUser extends User implements CSV {

    private static Logger logger = Logger.getLogger(FizUser.class.getSimpleName());

/*
    private String fio;

    private int id;

    private String phone;
*/
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
        index++;

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


    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @throws IOException - Если возникли ошибки при записи в файл
     */
    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(this.getId() + csvSplitter + this.getFio() + csvSplitter + this.getPhone() + csvSplitter + inn + csvSplitter + snils + "\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }

    }

}
