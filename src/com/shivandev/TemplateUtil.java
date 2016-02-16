package com.shivandev;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class TemplateUtil {

    public static String encoding = "UTF-8";

    public static Template getTemplate(String templatePath) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
            return cfg.getTemplate(templatePath,cfg.getLocale(), encoding, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parse(Template template, Map data) {
        try {
            StringWriter sw = new StringWriter();
            template.process(data, sw);
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
