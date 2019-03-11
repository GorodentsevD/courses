package ru.eltex;

//import org.apache.log4j.*;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.*;

/** Класс Физическое лицо
 * @author Дмитрий Городенцев
 * @version 1.0
 */

public class FizUser extends User implements CSV {

    private static Logger logger = Logger.getLogger(FizUser.class.getSimpleName());


    /** Поле имени физ.лица */
    private String fio;

    /** Поле идентификатора физ.лица */
    private int id;

    /** Поле номера телефона физ.лица */
    private String phone;

    /** Поле ИНН физ.лица */
    private String inn;

    /** Поле СНИЛС физ.лица */
    private String snils;

    /** Конструктор - создание нового объекта с определенными значениями */
    public FizUser() {

        this.id = 0;
        this.fio = " ";
        this.phone = " ";
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

        this.fio = fio;
        this.id = index;
        this.phone = phone;
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

    /** @return СНИЛС */
    public String getSnils() {

        return snils;
    }

    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @throws IOException - Если возникли ошибки при записи в файл
     */
    @Override
    public void toCSV() {
        logger.info("Запись в CSV");
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(id + csvSplitter + fio + csvSplitter + phone + csvSplitter + inn + csvSplitter + snils + "\n");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            logger.error("exception", ex);
        }

    }

}
