package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int ch;
            StringBuilder stringBuilder = new StringBuilder("");
            while ((ch = fileInputStream.read()) != -1) {
                char chr = (char) ch;
                stringBuilder.append((char) ch);
            }
            fileInputStream.close();
            String str = stringBuilder.toString();
            int start = str.indexOf(':'), end = str.indexOf('\n');
            String name = str.substring(start + 2, end - 1);
            str = str.substring(end + 1, str.length() - 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String age = str.substring(start + 2, end - 1);
            Integer intAge = age.isEmpty() ? null : Integer.parseInt(age);
            str = str.substring(end + 1, str.length() - 1);

            start = str.indexOf(':');
            end = str.indexOf('\n');
            String email = str.substring(start + 2, end - 1);
            str = str.substring(end + 1, str.length() - 1);

            phone:

            start = str.indexOf(':');
            end = str.length() - 1;
            String phone = str.substring(start + 1, end);
            Long longPhone = Long.parseLong(age);
            return new Profile(name, intAge, email, longPhone);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
