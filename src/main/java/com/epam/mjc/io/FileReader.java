package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = fileInputStream.read()) != -1)
                stringBuilder.append((char) ch);

            String[] els = stringBuilder.toString().split("(\\r\\n[a-zA-Z]+:\\s)|(Name: )|(\\r\\n)");

            List<String> list = Arrays.stream(els).collect(Collectors.toList());
            list.remove(0);


            String name = list.get(0);
            String email = list.get(2);
            int age;
            long phone;
            age = Integer.parseInt(list.get(1));
            phone = Long.parseLong(list.get(3));
            return new Profile(name, age, email, phone);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
