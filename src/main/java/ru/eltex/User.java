package ru.eltex;

import org.apache.log4j .*;

import java.io.FileWriter;
import java.io.IOException;


/** Класс пользователь
 * @author Дмитрий Городенцев
 * @version 1.0
 */
public class User implements CSV {

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
    public User(String fio, String phone) {
        this.fio = fio;
        this.id = index;
        this.phone = phone;
        index++;

        logger.debug("Создание объекта User");
    }

    /** Конструктор - создание нового объекта с определенными значениями */
    public User() {
        this.fio = " ";
        this.id = -1;
        this.phone = " ";
    }

    /**
     * Метод записи полей объекта в файл phonebook.csv
     * @throws IOException - Если возникли ошибки при записи в файл
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


/*
javadoc
gradle javadoc

ан        | где                     | описание
@author    класс, интерфейс
@version        -//-
@since     класс, инерф, метод,      с какой версии
            поле
@param     метод
@return    метод
@exception  -//-
@throws     -//-
@deplecated класс, интерфейс, метод
            или поле уже не использ
*/
    /**
     * телеф книга
     * @author Name
     * @version 1.1
     */


    /*
      ДЗ - 1) ПРОДОКУМЕНТИРОВАТЬ ВСЕ КЛАССЫ И ИНТЕРФЕЙСЫ
           2) Log4g - read write create(конструкторы классов и CSV)
           3) log4g -> в файл
                       ...log.debug
                       ...log.error
                            ...

               GITHUB:
                Java ELTEX
                       -lesson1
                       -lesson2
                       -PhoneBook
    * БИБЛИОТЕКА LOG4J
    * УРОВНИ:
    *   fatal -> error -> warning -> info ; debug -только для программистов
    *  import org.apache.logug.logger;
    *  рекомендуется 1 логгер на все классы
    *  final static Logger logger = Logger.getlogger(Phonebook.class)
    *   ...write (...) {
    *           Logger.debug("test")
    *                 .info()
    *                 .warn()
    *                 .error()
    *                 .fatal()
    *      }
    *
    *   log4j
    *       info - логика программы
    *       debug - информация для разработчика
    *       waring
    *       error
    *
    *
    * */

    /*
    13.03

      ( Spring Boot ) backend     <->     frontend
        html, REST

                      DB(MySQL, mongo)    JQuery, Angular


        http://192.168.0.102 : 8080/getusers
                                   /getfio
                                         |
                                      интерфейсы

        http(TCP) {get, post}
                                GET(255 символов) / get_users
                                      http/1.0

                                POST(передача)

        ServerSocket server = new ServerSocket(8080);
        while(true) {
            Socket client = server.accept();
            Runnable r = new MyThread(client);
            Thread t = new Thread(r);
            t.start();
        }

        class MyThread implements Runnable {
            public MyThread(Socket client) {
                this.client = client;
            }
            public void run() {
                InputStream inStream = this.client.getInputStream();
                OutputStream outStream = this.client.getOutputStream();

                Scanner in = new Scanner(inStream);
                PrintWriter out = new PrintWriter(outStream);
                out.write("hello");
                out.flush();
            }
        }



        вопросы как убить процесс, доки...


        ДЗ: 1) клиент сервер + потоки
            2) веб-сервер
            3) метод get_users - выдат всех пользователей, передать текстом
     */





















