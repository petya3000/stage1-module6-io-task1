package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = fileInputStream.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            String str = stringBuilder.toString();
            int start = str.indexOf(':');
            int end = str.indexOf('\n');
            String name = str.substring(start + 2, end - 1);
            str = str.substring(end + 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String age = str.substring(start + 2, end - 1);
            Integer intAge = age.isEmpty() ? null : Integer.parseInt(age);
            str = str.substring(end + 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String email = str.substring(start + 2, end - 1);
            str = str.substring(end + 1);


            start = str.indexOf(':');
            end = str.indexOf('\n');
            String phone = str.substring(start + 2, end - 1);
            Long longPhone;
            if (phone.isEmpty() || phone.equals(" "))
                longPhone = 0L;
            else
                longPhone = Long.parseLong(phone);

            return new Profile(name, intAge, email, longPhone);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
