package com.shivandev;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Modified by ShIvan on 14.02.2016.
 * Created by duxing on 2015.07.26 23:38.
 */
public class FileWriter {
    private static String msg;
    public static boolean write(String filePath, String fileNameWithExt, String content){
        return write(filePath, fileNameWithExt, content.getBytes(Charset.forName("UTF-8")));
    }

    public static boolean write(String filePath, String fileNameWithExt, byte[] contentByte){
        File p = new File(filePath);
        p.mkdirs();
        File f = new File(filePath+fileNameWithExt);
        boolean r = false;
            try {
                if(contentByte!=null && contentByte.length>0) {
                    writeToFile(f, contentByte);
                    r = true;
                }else{
                    setMsg("content empty");
                }
            } catch (IOException e) {
                setMsg("exception: "+e.getCause());
            }
        return r;
    }

    public static String getMsg() {
        return msg;
    }

    public static void setMsg(String msg) {
        FileWriter.msg = msg;
    }

    public static void writeToFile(@NotNull File file, @NotNull byte[] text) throws IOException {
        writeToFile(file, text, false);
    }

    public static void writeToFile(@NotNull File file, @NotNull byte[] text, boolean append) throws IOException {
        writeToFile(file, text, 0, text.length, append);
    }

    private static void writeToFile(@NotNull File file, @NotNull byte[] text, final int off, final int len, boolean append)
            throws IOException {
        OutputStream stream = new BufferedOutputStream(new FileOutputStream(file, append));
        try {
            stream.write(text, off, len);
        }
        finally {
            stream.close();
        }
    }
}
