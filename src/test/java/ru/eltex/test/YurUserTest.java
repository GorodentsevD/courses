package ru.eltex.test;

import org.junit.Assert;
import org.junit.Test;
import ru.eltex.YurUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static ru.eltex.User.csvFile;

public class YurUserTest {

    @Test
    public void toCSV() {

        String fioTest = "test";
        String phoneTest = "test";
        String innTest = "test";

        YurUser yurUserTest = new YurUser(fioTest, phoneTest, innTest);
        yurUserTest.setID(51);
        yurUserTest.toCSV();


        String line = null;
        String buf = null;
        boolean flag;

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));


            while((buf = br.readLine()) != null) {
                line = buf;
            }

            String[] parts = line.split(";");

            int id = yurUserTest.getId();
            String fio = yurUserTest.getFio();
            String phone = yurUserTest.getPhone();

            if (id == Integer.parseInt(parts[0]) && fio.equals(parts[1]) && phone.equals(parts[2])) {
                flag = true;
            }
            else {
                flag = false;
            }
            flag = false;
            Assert.assertTrue("Error in toCSV method", flag);

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
