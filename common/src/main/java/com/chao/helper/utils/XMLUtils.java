package com.chao.helper.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by think on 2017/1/16.
 * xml工具类
 */
public class XMLUtils {

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map<String, Object> doXMLParse(String strxml) {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String, Object> m = new HashMap<>();
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        SAXBuilder builder = new SAXBuilder();
        Document doc;
        try {
            doc = builder.build(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XMLUtils.getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return m;
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(XMLUtils.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    /**
     * 获取xml编码字符集
     * @param strxml
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
        InputStream in = IOUtils.String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        in.close();
        return (String)doc.getProperty("encoding");
    }
    /**
     * 把xml解析为map,key为根节点到子节点的key用“-”连接 ，例如 Response-Order-Submit0-GetSMSVerifyCodeUrl
     * @param strxml
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public static Map<String,String> parseXMLToMap(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String,String> resultMap = new HashMap<String,String>();
        Map<String,String> tmpMap = null;

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        String parentNodeKey = root.getName();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String key = parentNodeKey + "-" + e.getName();
            String value = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                value = e.getTextNormalize();
                resultMap.put(key, value);
            } else {
                tmpMap = new HashMap<String,String>();
                tmpMap = XMLUtils.getChildrenMap(children,key);
                for(Map.Entry<String,String> entry : tmpMap.entrySet()){
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            }
        }

        //关闭流
        in.close();

        return resultMap;
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static Map getChildrenMap(List children,String parentNodeKey) {
        StringBuffer sb = new StringBuffer();
        Map<String,String> resultMap = new HashMap<String,String>();
        Map<String,String> tmpMap = null;
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String key = parentNodeKey + "-" + e.getName();
                String value = "";
                List childList = e.getChildren();
                if(childList.isEmpty()) {
                    value = e.getTextNormalize();
                    resultMap.put(key, value);
                } else {
                    tmpMap = new HashMap<String,String>();
                    tmpMap = XMLUtils.getChildrenMap(childList,key);
                    for(Map.Entry<String,String> entry : tmpMap.entrySet()){
                        resultMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        return resultMap;
    }
}