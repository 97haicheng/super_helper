package com.chao.helper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by think on 2017/1/16.
 */
public class LogPrinter {

    protected final static Logger logger = LoggerFactory.getLogger(LogPrinter.class);

    public static String fgFlag = "|";

    // http请求参数
    public static String reqInfo = "lb_reqParams";
    // http响应参数
    public static String resInfo = "lb_resParams";
    // 逻辑判断类型参数
    public static String logicInfo = "lb_logicInfo";
    // 错误提示信息
    public static String errInfo = "lb_errorInfo";
    // 调用结果状态编
    public static String resCode = "lb_theResult";
    // 调用结果状态编码
    public static String genInfo = "lb_generInfo";
    // 金额操作
    public static String moneyInfo = "lb_moneyInfo";

    public static void logUseTime(String processMark, long startTime, long endTime){
        long time = endTime - startTime;
//		System.out.println("[execution time]processMark:{} " + processMark + " useTime:" + time);
        logger.info("[execTime]process:{}, useTime:{}", processMark, time);
    }

    public static void logDebug(String userId, String logType, Object[] os){
        logger.debug("thread:{},userId:{},logType:{},logMsg:{}",
                Thread.currentThread().getName(), userId, logType, getAllMsg(os));
    }

    public static void logInfo(String userId, String logType, Object[] os){
        logger.info("thread:{},userId:{},logType:{},logMsg:{}",
                Thread.currentThread().getName(), userId, logType, getAllMsg(os));
    }

    public static String getAllMsg(Object[] os){
        StringBuffer buffer = new StringBuffer();
        for (Object o : os) {
            buffer.append(o == null ? "" : o.toString());
            buffer.append(fgFlag);
        }
        return buffer.toString();
    }
    public static void main(String[] args) {
        LogPrinter.logUseTime("test", System.currentTimeMillis(), System.currentTimeMillis());

    }

}
