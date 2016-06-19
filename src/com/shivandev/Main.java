package com.shivandev;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShIvan on 14.02.2016.
 */
public class Main {

    private static final String DEFAULT_FULL_SQLFILE_PATH = "sql.ddl";  // in plans to include to the plugin's fields
    private static final String DEFAULT_GENERATED_FILE_PATH = "out" + File.separator;  // in plans to include to the plugin's fields

    private static final Template STORIO_ENTITY_TMPL = TemplateUtil.getTemplate("StorIoEntity.ftl");
    private static final Template TABLE_META_DATA_TMPL = TemplateUtil.getTemplate("TableMetaData.ftl");

    private static String fullSqlFilePath;
    private static String generatedFilePath;
    private static String packageStr = "com.shivandev";
    private static String subjectStr = "";

    static Map root = new HashMap();
    {
        root.put("package", packageStr);  // in plans to include to the plugin's fields
        root.put("subject", subjectStr);  // in plans to include to the plugin's fields
        root.put("today", System.currentTimeMillis());
    }

    public void generate() {
        try {
            // todo в планах
            // подумать на тему пометки на кажом SQL запросе - к какой сущности (подкаталогу, субъекту) он относится (foods, alarms и т.п.)

            FileReader fr = new FileReader(fullSqlFilePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            StringBuilder strToAnalyze = new StringBuilder();
            String result;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line))
                    continue;
                strToAnalyze.append(line);
                if (line.contains(";")) {
                    SqlInfo si = SqlAnalyze.analyze(strToAnalyze.toString());
                    root.put("tableName", si.getTableName());
                    root.put("columnList", si.getColumnList());
                    root.put("optionsColumnList", si.getOptionsColumnList());
                    result = TemplateUtil.parse(TABLE_META_DATA_TMPL, root);
                    FileWriter.write(getFilePath("tables"), si.getTableName() + "Table" + ".java", result);
                    result = TemplateUtil.parse(STORIO_ENTITY_TMPL, root);
                    FileWriter.write(getFilePath("entities"), si.getTableName() + "Entity" + ".java", result);
                    strToAnalyze = new StringBuilder();
                }
            }
        } catch (Exception e) {
            System.out.println("Error while generate classes.");
            e.printStackTrace();
        }
    }

    private String getFilePath(String target) {
        return generatedFilePath + packageStr.replace(".", File.separator) + File.separator + target + File.separator + (!"".equals(subjectStr) ? subjectStr + File.separator : "");
    }

    public static void main(String[] args) {
        if (args != null) {
            switch (args.length) {
                case 4:
                    subjectStr = args[3];
                    root.put("subject", subjectStr);
                case 3:
                    generatedFilePath = args[2];
                case 2:
                    packageStr = args[1];
                    root.put("package", packageStr);
                case 1:
                    fullSqlFilePath = args[0];
                    break;
            }
        } else {
            fullSqlFilePath = DEFAULT_FULL_SQLFILE_PATH;
            generatedFilePath = DEFAULT_GENERATED_FILE_PATH;
        }

        new Main().generate();
    }
}
