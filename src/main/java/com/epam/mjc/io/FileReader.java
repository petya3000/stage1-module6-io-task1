package com.epam.mjc.io;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class FileReader {

    public static void main(String[] args) {

            String str = "Name: Anna\n" +
                    "Age: 25\n" +
                    "Email: anna@mailserver.com\n" +
                    "Phone: 1234567890";
            String [] els = str.split("(\\n[a-zA-Z]+:\\s)|(Name: )");

        List<String> list = Arrays.stream(els).collect(Collectors.toList());
        list.remove(0);
        //list.stream().forEach(s -> System.out.println(s.length()));


    }

    public Profile getDataFromFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = fileInputStream.read()) != -1)
                stringBuilder.append((char) ch);

            String [] els = stringBuilder.toString().split("(\\r\\n[a-zA-Z]+:\\s)|(Name: )|(\\r\\n)");

            List<String> list = Arrays.stream(els).collect(Collectors.toList());
            list.remove(0);


             String name = list.get(0), email = list.get(2);
             int age;
             long phone;
             try {
                 age = Integer.parseInt(list.get(1));
                 phone = Long.parseLong(list.get(3));
             } catch (NumberFormatException e){
                 e.printStackTrace();
                 return null;
             }

            return new Profile(name, age, email, phone);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
