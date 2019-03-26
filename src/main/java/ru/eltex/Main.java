/**
 * Телефонная книга
 * @author Дмитрий Городенцев <gorodentsevd@gmail.com>
 * @version 1.0.2
 */

package ru.eltex;

import java.util.ArrayList;

public class Main {

    /**
     * Поле для адреса базы данных
     * @since 1.0.2
     */
    public static String url = "jdbc:mysql://127.0.0.1:3306/PhoneBook";

    /**
     * Поле логин для авторизации в СУБД
     * @since 1.0.2
     */
    public static String user = "root";

    /**
     *  Поле пароль для авторизации в СУБД
     *  @since 1.0.2
     */
    public static String password = "00000000";

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

        /*
        user1.toCSV();
        user2.toCSV();

        fizUser1.toCSV();
        yurUser1.toCSV();

        System.out.println("\n");
        CSV.fromCSV();

        System.out.println("\nIndex: " +User.index);

        CSV.cleanCSV();

        System.out.println("fizUser1 fio: " + fizUser1.getFio());
        System.out.println("fizUser1 phone: " + fizUser1.getPhone());
        System.out.println("fizUser1 inn: " + fizUser1.getINN());
        System.out.println("fizUser1 snils: " + fizUser1.getSnils() + "\n");

        System.out.println("yurUser1 fio: " + yurUser1.getFio());
        System.out.println("yurUser1 phone: " + yurUser1.getPhone());
        System.out.println("yurUser1 inn: " + yurUser1.getINN());
        */

        SQL.resetTable(url, user, password);

        user1.addToDB(url, user, password);
        user2.addToDB(url, user, password);
        fizUser1.addToDB(url, user, password);
        yurUser1.addToDB(url, user, password);

        SQL.showTable(url, user, password);

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