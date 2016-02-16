package com.shivandev;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class Matcher {

    public static List<String> match(String regString, int index, String content){
        List<String> r = new ArrayList<>();
        java.util.regex.Matcher mr = Pattern.compile(regString,Pattern.DOTALL+Pattern.CASE_INSENSITIVE).matcher(content);
        while (mr.find()) {
            r.add(mr.group(index));
        }
        return r;
    }

    public static List<String> match(String regString, String content){
        List<String> r = new ArrayList<>();
        java.util.regex.Matcher mr = Pattern.compile(regString,Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(content);
        while (mr.find()) {
            for(int i=0;i<mr.groupCount();i++) {
                r.add(mr.group(i));
            }
        }
        return r;
    }
}
