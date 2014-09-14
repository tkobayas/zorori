package com.github.tkobayas.zorori.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

    public static void main(String[] args) throws Exception {
        
        String regex = ".*かいけつゾロリシリーズ(\\d+).*target=\"_blank\">(.*)</a></p></div>";
        Pattern p = Pattern.compile(regex);


        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("zorori.html"), "Shift_JIS"));
        while (br.ready()) {
            
            int num;
            String title;
            
            String line = br.readLine();
            if (!line.contains("series_book")) {
                continue;
            }
            
            Matcher m = p.matcher(line);
            if (m.find()){
               num = Integer.parseInt(m.group(1));
               String value = m.group(2);
               title = value.replace("<br />", "");
            } else {
                continue;
            }
            
            System.out.println("insert into Book (id, num, title, owned, read) values ("
                + (num-1) + ", " + num + ", '" + title + "', true, true)");
        }
        br.close();
    }
}
