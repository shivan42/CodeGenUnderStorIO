package com.shivandev;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class SqlAnalyze {

    public static String cleanSql(String sql) {
        sql = sql.replaceAll("\\r", "");
        sql = sql.replaceAll("\\n\\n", "\n");
        sql = fixComment(sql);
        sql = sql.replaceAll(",", "\n");
        sql = sql.replaceAll("@", ",");
//        System.out.println(sql);
        return sql;
    }

    private static String fixComment(String sql) {
        String regStr = "comment\\s+'([^']*,[^']*)'";
        java.util.regex.Matcher mr = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(sql);
        while (mr.find()) {
            String r = mr.group().replaceAll(",", "@");
            sql = sql.replace(mr.group(), r);
        }
        regStr = "unique\\s?\\([^)]+,[^)]+\\)|key\\s?\\([^)]+,[^)]+\\)";
        java.util.regex.Matcher mr1 = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE + Pattern.DOTALL).matcher(sql);
        while (mr1.find()) {
            String r = mr1.group().replaceAll(",", "@");
            sql = sql.replace(mr1.group(), r);
        }
        return sql;
    }



    public static List<String> analyzeColumnLine(String sql) {
        String regStr = "create\\s*table\\s*(?:if\\s*not\\s*exists)*\\s*[^\\(]*\\s*\\((.*)\\)\\s*[^\\s\\)]*\\s*(?:(ENGINE|comment)*)";
        List<String> matches = Matcher.match(regStr, 1, sql);
        if (matches != null && matches.size() == 1) {
            String lines = matches.get(0);
            lines = lines.replaceAll("\\s+\\n", "\n");
            String[] la = lines.split("\n");
            return Arrays.asList(la);
        }
        return null;
    }

    public static String analyzeTableName(String sql){
        String regStr = "create\\s+table\\s*(?:if\\s*not\\s*exists)*(([^\\(]+))\\s*\\(";
        List<String> matches = Matcher.match(regStr, sql);
        if(matches!=null&&matches.size()==2){
            return matches.get(1).replaceAll("`|'|\"|\\s","");
        }
        return "table";
    }
    public static String analyzePrimaryKey(String sql){
        String regStr = "primary\\s+key\\s+\\(([^\\)]*)\\)";
        List<String> matches = Matcher.match(regStr, 1, sql);
        if(matches!=null&&matches.size()==1){
            return matches.get(0).replaceAll("`|'|\"|\\s","");
        }
        return null;
    }


    public static Column getColumn(String line) {
        String regStrP = "[`]*(\\w*)[`]*\\s+((\\S*)\\s*[unsigned\\s]*[not\\s]*[null\\s]*[default\\s]*.*[autoincreament\\s]*)\\s*(?:comment\\s*'([^']*)')*";
        String regStr = "comment\\s+'(([^']*))'";
        List<String> l = Matcher.match(regStrP, line.trim());
        List<String> l2 = Matcher.match(regStr, line);
        if(l!=null && l.size()==4){
            Column c = new Column(l.get(1),l.get(3), l.get(2));
            if(l2!=null && l2.size()==2){
                c.setComment(l2.get(1));
            }
            return c;
        }else{
            return null;
        }
    }

    public static boolean isKeyLine(String line) {
        String regStr = "\\s*((primary|unique)\\s+)*key\\s*\\S*\\s*\\(";
        List<String> l = Matcher.match(regStr, line);
        if(l!=null && l.size()>0 && !"`".equals(line.trim().substring(0, 1))){
            return true;
        }
        regStr = "\\s*((unique)\\s+)+\\s*\\S*\\s*\\(";
        l = Matcher.match(regStr, line);
        if(l!=null && l.size()>0 && !"`".equals(line.trim().substring(0, 1))){
            return true;
        } else{
            return false;
        }
    }

/*
    public static void main(String[] args) throws Exception {
//        String s = "CREATE TABLE Meals (Id INTEGER NOT NULL PRIMARY KEY, Changed integer(10) DEFAULT 0 NOT NULL, Deleted integer(10) DEFAULT 0 NOT NULL, Datetime integer(10) NOT NULL, MealTypesId integer(10) NOT NULL, FOREIGN KEY(MealTypesId) REFERENCES MealTypes(Id));";
//        String s = "CREATE TABLE MealTypes (Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name text NOT NULL UNIQUE, UNIQUE ( ID ) ON CONFLICT REPLACE);";
        String s = "CREATE TABLE MealTypes (Id integer(10) NOT NULL, Name text NOT NULL UNIQUE, PRIMARY KEY (Id), UNIQUE ( ID ) ON CONFLICT REPLACE);";
        SqlInfo si = analyze(s);
//        System.out.println(s1);
    }
*/

    public static SqlInfo analyze(String sql) {
        sql = cleanSql(sql);
        List<String> lines = analyzeColumnLine(sql);
        List<Column> colList = new ArrayList<>();
        List<Column> optionsColumnList = new ArrayList<>();
        if(lines!=null && lines.size()>0){
            for(String line:lines){
                if(StringUtils.isNotBlank(line)) {
                    line = line.trim();
                    if (!isKeyLine(line)) {
                        try {
                            Column c = getColumn(line);
                            if (c != null) {
                                colList.add(c);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        optionsColumnList.add(new Column(fixUnique(line)));
                    }
                }
            }
        }
        String k = analyzePrimaryKey(sql);
        String n = analyzeTableName(sql);
        return new SqlInfo(n,k,colList, optionsColumnList);
    }

    private static String fixUnique(String line) {
        String regStr = "\\s*constraint\\s*(\\D*)\\d*\\s*((unique\\s*\\(.*\\)))";
        List<String> l = Matcher.match(regStr, line);
        if(l!=null && l.size()>0){
            return l.get(2) + " ON CONFLICT " + l.get(1);
        }
        return line;
    }
}
