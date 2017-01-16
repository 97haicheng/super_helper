package com.chao.helper.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by think on 2017/1/16.
 */
public class ExcleExport {

    private static HSSFWorkbook wb;

    /**
     * 导出EXCLE
     *
     * @param excleHeadr
     *            sheet页表头
     * @param cellNames:数据在List内Map中的key
     * @param listData
     *            数据
     * @param sheetFile
     *            下载后的文件名，只支持英文
     * @param sheetName
     *            sheet页的名字
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws IOException
     *             异常
     */
    public static void exportExcleByPoint(String excleHeadr, String[] cellNames, List<Map<String, Object>> listData,
                                          String sheetFile, String sheetName, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + sheetFile);// 指定下载的文件名
        response.setContentType("application/vnd.ms-excel");

        wb = new HSSFWorkbook();
        // 在excle中添加一个sheet页
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 添加表头
        HSSFRow row = sheet.createRow(0);
        // 创建单元格并设置表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置居中
        String[] headrs = excleHeadr.split(",");
        HSSFCell cell = null;
        int length = headrs.length;// 考虑复用excle表头数据个数，单独申明出来
        for (int i = 0; i < length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headrs[i]);
            cell.setCellStyle(style);
        }
        // 得到excle数据Map<String, Object> map : listData
        Object obj = null;
        Object object = null;
        for (int j = 0; j < listData.size(); j++) {
            row = sheet.createRow(j + 1);
            Map<String, Object> map = listData.get(j);
            for (int i = 0; i < cellNames.length; i++) {
                obj = new Object();
                object = map.get(cellNames[i]);
                if ("billingType".equals(cellNames[i])) {// 支付方式
                    if (null == object) {
                        object = 0;
                    }
                    long billingType = Long.parseLong(map.get(cellNames[i]).toString());
                    if (billingType == 1) {
                        obj = "点播";
                    } else if (billingType == 2) {
                        obj = "包月";
                    }else{
                        obj = "数据异常";
                    }
                } else if ("countPrice".equals(cellNames[i])) {// 总收入
                    if (null == object) {
                        object = 0;
                    }
                    BigDecimal big = new BigDecimal(Double.parseDouble(object.toString()) / 100);
                    obj = big.setScale(2, BigDecimal.ROUND_HALF_UP);
                } else if ("carrier".equals(cellNames[i])) {// 运营商
                    if (null == object) {
                        object = 0;
                    }
                    long carrier = Long.parseLong(object.toString());
                    if (carrier == 1) {
                        obj = "中国联通";
                    } else if (carrier == 2) {
                        obj = "中国移动";
                    } else if (carrier == 3) {
                        obj = "中国电信";
                    } else {
                        obj = "数据异常";
                    }
                } else {
                    if (null == object) {
                        obj = "";
                    } else {
                        obj = object.toString();
                    }
                }
                row.createCell(i).setCellValue(obj.toString());
            }
        }
        wb.write(response.getOutputStream());// 文件写入
    }

    /**
     *
     * @param headr
     *            标题
     * @param data
     *            数据源
     * @param sheetName
     *            sheet名称
     * @param fileName
     *            文件名
     * @param request
     * @param response
     * @throws IOException
     */
    public static void exportExcle(String[] headr, List<Object[]> data, String sheetName, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 处理中文文件名
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        //sheetName = new String(sheetName.getBytes("UTF-8"), "ISO8859-1");

        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
        response.setContentType("application/vnd.ms-excel");
        wb = new HSSFWorkbook();
        // 在excle中添加一个sheet页
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 添加表头
        HSSFRow row = sheet.createRow(0);
        // 创建单元格并设置表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置居中
        HSSFCell cell = null;
        int length = headr.length;// 考虑复用excle表头数据个数，单独申明出来
        for (int i = 0; i < length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headr[i]);
            cell.setCellStyle(style);
        }

        // 得到excle数据Map<String, Object> map : listData
        Object[] obj = null;
        for (int j = 0; j < data.size(); j++) {
            row = sheet.createRow(j + 1);
            obj = data.get(j);
            for (int i = 0; i < obj.length; i++) {
                row.createCell(i).setCellValue(obj[i] + "");
            }
        }
        wb.write(response.getOutputStream());// 文件写入
    }

}

