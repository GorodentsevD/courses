package ru.eltex.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.eltex.CSV;
import ru.eltex.User;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;
import static ru.eltex.User.csvFile;

public class UserTest {



    @Test
    public void toCSV() {

        int id = 50;
        String fio = "Test";
        String phone = "test";

        User userTest = new User(fio, phone);
        userTest.setID(id);
        userTest.toCSV();

        String line = null;
        String buf = null;
        boolean flag;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));

            line = br.readLine();
            if(line == null) {
                System.out.println("Error, file is empty");
                exit(-1);
            }

            while((buf = br.readLine()) != null) {
                line = buf;
            }

            String[] parts = line.split(";");

            if (id == Integer.parseInt(parts[0]) && fio.equals(parts[1]) && phone.equals(parts[2])) {
                flag = true;
            }
            else {
                flag = false;
            }

            System.out.println("msg");
            Assert.assertTrue("MSG", flag);

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}