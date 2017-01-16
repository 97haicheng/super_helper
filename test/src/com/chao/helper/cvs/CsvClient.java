package com.chao.helper.cvs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2016/11/3.
 * 导出cvs
 */
public class CsvClient {
    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = getFile();

        List<Student> stooges = new ArrayList<Student>();
        for (int i=0;i<5;i++){
            Student stu = new Student();
            stu.setAge(i+10);
            stu.setName("name " +i);
            stu.setSex(i/2==0 ?"boy":"girl");
            stooges.add(stu);
        }

        CsvWriter cw = null;

        try {
// J2EE Web下载时为下面注释的代码，传人的参数是HttpServletResponse
//            cw = new CsvWriter(response.getWriter());
            cw = new CsvWriter(new PrintWriter(file));
            for (Student stu : stooges) {
                cw.writeLine(getCsvLine(stu));
            }
            cw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cw != null) {
                    cw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Done.");
    }

    private static List<String> getCsvLine(Student stu) {
        List<String> csvLine = new ArrayList<String>();
        csvLine.add(stu.getName());
        csvLine.add(Integer.toString(stu.getAge()));
        csvLine.add(stu.getSex());
        return csvLine;
    }

    private static File getFile() {
        String path = "C:\\Users\\think\\Desktop\\";
//        String path = "";
        String filename="abc.csv";

        File directory = new File(path);
        if (!directory.exists())
            directory.mkdirs();
        File file = new File(path + filename);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return file;
    }
}
