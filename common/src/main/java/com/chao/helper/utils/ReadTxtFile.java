package com.chao.helper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by QuChao on 2017/6/7.
 * Description :
 */
public class ReadTxtFile {

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
            String encoding="UTF8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){


                    if(lineTxt.contains("<H3")){
                        Pattern p = Pattern.compile("<H3 (.*)</H3>");
                        Matcher m = p.matcher(lineTxt);
                        while(m.find()){
                            System.out.println(m.group(1).substring(m.group(1).indexOf(">")+1,m.group(1).length()));
                        }
                    }

                    if(lineTxt.contains("<A")){
                        Pattern p = Pattern.compile("<A (.*)</A>");
                        Matcher m = p.matcher(lineTxt);
                        while(m.find()){
                            System.out.println(m.group(1).substring(m.group(1).indexOf(">")+1,m.group(1).length())
                                    + "           " +
                                    m.group(1).substring(m.group(1).indexOf("\"")+1,m.group(1).indexOf("\"",m.group(1).indexOf("\"")+1))
                            );
                        }
                    }
//                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }

    public static void main(String argv[]){
        String filePath = "C:\\Users\\Administrator\\Desktop\\bookmarks.html";
//      "res/";
        readTxtFile(filePath);
    }

}
