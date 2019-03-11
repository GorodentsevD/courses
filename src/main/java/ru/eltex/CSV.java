package ru.eltex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.*;

/**
 * Интерфейс CSV для работы с .csv файлами
 * @author Дмитрий Городенцев
 * @version 1.0
 */

public interface CSV {

    Logger logger = Logger.getLogger(User.class.getSimpleName());

    /** Метод, реализующий запись в .csv файлы */
    void toCSV();

    /** default метод для чтения из .csv файла
     * @exception IOException - Если возникли ошибки при чтении из файла
     */
    default void fromCSV () {

        logger.debug("Вызов метода fromCSV\n");
        try (FileReader fr = new FileReader(User.csvFile)) {

            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line); // выводим содержимое файла на экран построчно
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error("exception", e);
        }
    }

    //String[] arr = str.split(;);
}

//phonebook.csv
 /*
    ID ; FIO   ; PHONE
     1 ; Boris ; 900
     2 ; Alex  ; 800
 */
