/**
 * Телефонная книга
 * @author Дмитрий Городенцев
 * @version 1.1
 */

package ru.eltex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.apache.log4j.*;


public class Main {

    /** Основной метод для запуска проекта */
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        User user1 = new User("Boris", "900" );
        User user2 = new User("Alex", "800");

        FizUser fizUser1 = new FizUser("Dima", "700", "1111", "555333");
        YurUser yurUser1 = new YurUser("Eltex", "500", "22222");

        users.add(user1);
        users.add(user2);
        users.add(fizUser1);
        users.add(yurUser1);

        //Field[] fields = User.class.getFields();

        user1.toCSV();
        user2.toCSV();

        fizUser1.toCSV();
        yurUser1.toCSV();

        System.out.println("\n");
        user1.fromCSV();

        System.out.println("\nIndex: " +User.index);

        user1.cleanCSV();

    }
}

// 1 задание - юр и физ лица наследуются от юзера
//class info есть метод getFields
// Fields[] fields = info.getFields()
// for (...) { fields.getName();
// 2 вывод всех полей при помощи наследования от object



//id - автоинкремент


/*
public class Usr {
    private Integer id;
    private static Integer index = 1;
    pubic Usr () {
        this.id = index;
        index++
    }

    final public String toString // невозможность наследования
}
*/

// 3 задание - static index
// static - позволяет использовать методы и поля без создания объекта



/*
интерфейсы
4 задание csv интерфейс + read/write

файлы
try( FileWriter fw = new FileWriter
    ("phonevook.csv", true)){ //true - запись файла без буферизации
    fw.write("...ln")
    fw.close(); - необязательно делать close тк после блока трай закрывает сам
   try (FileReader fr = new FileReader("phonebook.csv")) {
    string ch = fr.read(buf);

     catch(IOException e){ System.out.println(e.getMessage());
*/