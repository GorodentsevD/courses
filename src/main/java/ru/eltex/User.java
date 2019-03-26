/** Класс пользователь
 * @author Дмитрий Городенцев <gorodentsevd@gmail.com>
 * @version 1.0.1
 */

package ru.eltex;

import org.apache.log4j .*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


public class User implements CSV, SQL {

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

    @Override
    public void addToDB(String url, String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String SQL = "INSERT INTO users(id, fio, phone) VALUE (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, this.getId());
            preparedStatement.setString(2, this.getFio());
            preparedStatement.setString(3, this.getPhone());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}


/*
 *  20.03
 *  TestUser.java
 *
 *
 *      junit
 *    package ru.eltex.test
 *    class TestUser {
 *
 *    @Test
 *    public void userAddToDB() {
 *      ...new User()
 *      <get_count> = count_Before;
 *      <add_toDB>
 *      <get_count> = count_after;
 *      Assert.assertEquals("MSG", count_Before, count_After);
 *    }
 *
 *      Assert.fail("MSG"); - метод аналог exit -1 в Си
 *      Assert.true("MSG", obj);
 *      Assert.null(...); - проверка создание нового объекта
 *      Assert.notNull(...);
 *      Assert.Some("msg", obj1, obj2) - проверка что два объекта принадлежат одному классу
 *      Assert.NotSome(...) - два объекта не принадлежат одному классу
 *
 *
 *
 *      @Before - методы, выпол-ие до тестов
 *      @After -  методы, выпол-ые после тестов
 *
 *      @BeforeClass
 *      @AfterClass
 *      @Ignore
 *
 *      ДЗ) 1 - покрыть проект 5 тестами, 2 не пройдут
 *          тестируем только интерфейс класса, только public методы
 *          Не нужен Main class
 *          Конструкторы тоже тестируем
 *
 *          <apache jmeter; junit>/ Яндекс.Танк - для веба
 *
 *
 *      команда: gradle test
 *
 *      Документирование:
 *
 *      аннотация | где
 *
 *      @author     классы, интерфейсы
 *      @version    -//-
 *      @since      -//- + методы и поля - с какой версии доступно
 *      @param      методы
 *      @return     методы
 *      @exception
 *      @throws
 *      @deprecated
 *
 *      1.0.95
 *              .95 - небольшое изменение
 *              .0. - координальное изменение
 *              1. - полностью переписанное
 *
 *      команда gradle.javadoc - создание  html странички
 *
 *
 *
 *      SQL:
 *
 *      users
 *
 *      |   id  |   fio |   phone   |
 *
 *          1       a       100
 *          2       b       200
 *          3       c       300
 *
 *       $ mysql    <db brower>
 *
 *      >CREATE DATABASE PhoneBook;
 *      >USE PhoneBook;
 *      >CREATE TABLE   users(
 *          id NUMBER/INTEGER,
 *          fio TEXT,
 *          phone VARCHAR(12));
 *      >INSERT INTO users VALUE/S (1, 'b' , '700'), (2, 'a', '900'), (3, 'c', '1000')
 *      >UPDATE users SET phone = '800' WHERE id = 3;
 *      >DELETE FROM users WHERE fio = 'b';
 *      >SELECT * FROM users;
 *
 *      JDBC:
 *
 *      try {
  *         Connection conn = DriverManager get Connection("jdbc:mysql://127.0.0.1:3306/phonebook",
  *                 "admin", "123");
  *         Statement stmt = Connection.createStatement();
  *         ResultSet rs = stmt.executeQuery("SELECT * FROM "users");
  *                            .executeUpdate(INSERT/DELETE/UPDATE);
  *
  *                     CreateTransaction
  *                         INSERT ...
  *                         DELETE ...
  *                     CloseTr
  *
  *         while(rs.next) {
  *             String fio = rs.getString("fio");
  *         }
  *         conn.close();
  *
  *
  *      }catch(SQLException) { } // не переписывать а дополнить Mysql
  *
 *      *  */




















