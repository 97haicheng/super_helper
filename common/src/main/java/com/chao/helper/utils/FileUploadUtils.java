package com.chao.helper.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by think on 2017/1/16.
 * 文件操作方法类
 */
public class FileUploadUtils {

    /**
     * 统一文件名，然后保存文件
     * @param mfile 文件
     * @param fileFromFileName 传入的文件名
     * @param path 保存文件的绝对地址
     * @return 保存后的文件名
     * @throws IOException
     */
    public static String saveFileToPath(MultipartFile mfile, String  fileFromFileName, String path) throws IOException{
        //根据表单提交过来的文件名，改为统一的文件名格式
        String fileName = currentFileName(fileFromFileName, com.chao.helper.utils.StringUtils.getRandom(6));
        System.out.println("文件绝对地址：" + path + fileName);
        boolean flag = write(path + fileName, mfile.getInputStream());
        //将文件保存到指定目录，成功返回文件名，失败返回 null
        if (flag) {
            return fileName;
        }
        throw new  IOException();
    }

    /**
     * 生成统一格式文件名
     * @param fileName
     * @param random
     * @return
     */
    private static String currentFileName(String fileName, String random) {
        String[] currenName = fileName.split("\\.");
        return currenName[0] + "-" + random + "." + currenName[currenName.length - 1];
    }

    /**
     * 读取文本文件返回数组
     *
     * @Description: 读取文本文件返回数组
     * @author zhangYuanHui
     * @param filePath
     *            文件路径及名称 如：D:/测试.txt
     * @param encoding
     *            编码格式，用于处理中文乱码
     * @return List<String>
     * @throws IOException
     */
    public static List<String> readFile(String filePath, String encoding) throws IOException {
        if (StringUtils.isNotEmpty(encoding)) {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), encoding);
            BufferedReader read = new BufferedReader(isr);
            return IOUtils.readLines(read);
        }
        InputStream input = new FileInputStream(new File(filePath));
        return IOUtils.readLines(input);

    }

    /**
     * 文件上传帮助
     *
     * @Description: 文件上传帮助
     * @author zhangYuanHui
     * @param fileFrom
     *            上传文件
     * @param fileFromFileName
     *            上传文件名称
     * @param path
     *            文件保存路径
     * @return Boolean
     * @throws IOException
     */
    public static boolean saveFile(final File fileFrom, final String fileFromFileName, final String path)
            throws IOException {
        boolean result = false;
        if (null != fileFrom) {
            File savefile = new File(new File(path), fileFromFileName);
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            org.apache.commons.io.FileUtils.copyFile(fileFrom, savefile);
            result = true;
        }
        return result;
    }

    /**
     * 将文件写入到磁盘指定目录
     *
     * @Description: 将文件写入到磁盘指定目录
     * @author zhangYuanHui
     * @param fileName
     *            文件路径及名称（包含文件后缀）
     * @param data
     *            字节数组
     * @return Boolean
     */
    public static boolean write(final String fileName, final byte[] data) {
        boolean result = false;
        File file = new File(fileName);
        OutputStream output = null;
        try {
            // 判断如果路径不存在，创建路径
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            output = new FileOutputStream(file);
            IOUtils.write(data, output);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                output = null;
            }
        }
        return result;
    }

    /**
     * 将文件写入到磁盘指定目录
     *
     * @Description: 将文件写入到磁盘指定目录
     * @author zhangYuanHui
     * @param fileName
     *            文件路径及名称（包含文件后缀）
     * @param input
     *            文件输入流
     * @return Boolean
     */
    public static boolean write(final String fileName, InputStream input) {
        boolean result = false;
        try {
            return write(fileName, IOUtils.toByteArray(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 拷贝文件到另一个目录
     *
     * @Description: 拷贝文件到另一个目录
     * @author zhangYuanHui
     * @param srcPath
     *            源文件路径 及名称
     * @param targetPath
     *            目标文件路径及名称
     * @param delSrcFile
     *            是否删除源文件 true删除 false 不删除
     */
    public static void copy(final String srcPath, final String targetPath, boolean delSrcFile) {
        InputStream input = null;
        OutputStream output = null;
        File inputFile = new File(srcPath);
        try {
            // 判断文件是否存在
            if (inputFile.exists()) {
                File outputFile = new File(targetPath);
                // 判断输出路径是否存在，不存在则创建路径
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }
                input = new FileInputStream(inputFile);
                output = new FileOutputStream(outputFile);
                IOUtils.copy(input, output);
            }
        } catch (IOException e) {
            // 报异常不可删除源文件
            delSrcFile = false;
            e.printStackTrace();
        } finally {
            // 关闭输入输出流，释放资源
            try {
                if (null != input) {
                    input.close();
                }
                if (null != output) {
                    output.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 删除文件
            if (delSrcFile) {
                inputFile.delete();
            }
        }
    }

    /**
     * 将文件转换为字节
     *
     * @Description: 将文件转换为字节
     * @author zhangYuanHui
     * @param fileName
     *            文件名称及路径
     * @return byte[]
     */
    public static byte[] getFileByteArray(String fileName) {
        File file = new File(fileName);
        InputStream input = null;
        byte[] data = null;
        try {
            if (!file.exists()) {
                return null;
            }
            input = new FileInputStream(file);
            data = IOUtils.toByteArray(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                input = null;
            }
        }
        return data;
    }
}
