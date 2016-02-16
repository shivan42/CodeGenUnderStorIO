package com.shivandev;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShIvan on 14.02.2016.
 */
public class Main {

    private static final String DEFAULT_FULL_SQLFILE_PATH = "sql.ddl";  // вынести в поля плагина
    private static final String DEFAULT_GENERATED_FILE_PATH = "out\\";  // вынести в поля плагина

    private static final Template STORIO_ENTITY_TMPL = TemplateUtil.getTemplate("StorIoEntity.ftl");
    private static final Template TABLE_META_DATA_TMPL = TemplateUtil.getTemplate("TableMetaData.ftl");

    private static String fullSqlFilePath;
    private static String generatedFilePath;
    private static String packageStr = "com.shivandev";
    private static String subjectStr = "";

    static Map root = new HashMap();
    {
        root.put("package", packageStr);  // вынести в поля плагина
        root.put("subject", subjectStr);  // вынести в поля плагина
        root.put("today", System.currentTimeMillis());
    }

    public void generate() {
        try {
            // todo в планах
            // подумать на тему пометки на кажом SQL запросе - к какой сущности (подкаталогу, субъекту) он относится (foods, alarms и т.п.)

            FileReader fr = new FileReader(fullSqlFilePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String result;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    return;
                }
                System.out.println(line);
                SqlInfo si = SqlAnalyze.analyze(line);
                root.put("tableName", si.getTableName());
                root.put("columnList", si.getColumnList());
                root.put("optionsColumnList", si.getOptionsColumnList());
                result = TemplateUtil.parse(TABLE_META_DATA_TMPL, root);
                FileWriter.write(getFilePath("tables"), si.getTableName() + "Table" + ".java", result);
                result = TemplateUtil.parse(STORIO_ENTITY_TMPL, root);
                FileWriter.write(getFilePath("entities"), si.getTableName() + "Entity" + ".java", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFilePath(String target) {
        return generatedFilePath + packageStr.replace(".", "\\") + "\\"+ target + "\\" + (!"".equals(subjectStr) ? subjectStr + "\\" : "");
    }

    public static void main(String[] args) {
        if (args != null) {
            fullSqlFilePath = args[0];
            if (args.length > 1 ) {
                packageStr = args[1];
                root.put("package", packageStr);
            }
            if (args.length > 2) {
                generatedFilePath = args[2];
            }
            if (args.length >3 && !"".equals(args[3])) {
                subjectStr = args[3];
                root.put("subject", subjectStr);
            }
        } else {
            fullSqlFilePath = DEFAULT_FULL_SQLFILE_PATH;
            generatedFilePath = DEFAULT_GENERATED_FILE_PATH;
        }
        new Main().generate();

    }
}
