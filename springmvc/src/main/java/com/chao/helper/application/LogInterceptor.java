package com.chao.helper.application;

import com.chao.helper.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class LogInterceptor implements AsyncHandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //controller方法异步开始执行时就开始执行这个方法，而postHandle需要等到controller异步执行完成后再执行
        logger.debug("afterConcurrentHandlingStarted log, uuid : {}", Thread.currentThread().getName());
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //会在处理方法之前执行，可以用来做一些编码处理、安全限制之类的操作。
        httpServletRequest.setCharacterEncoding("utf-8");

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Thread.currentThread().setName(uuid);
        //请求日志
        Map<String, Object> requestLogMap = new HashMap<>();
        //HTTP Header
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        Map<String, Object> headerMap = new HashMap<>();
        String headerName;
        while (headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            headerMap.put(headerName, httpServletRequest.getHeader(headerName));
        }

        if (!headerMap.isEmpty()) {
            requestLogMap.put("header", headerMap);
        }

        //HTTP Parameter
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        Map<String, Object> parameterMap = new HashMap<>();
        String parameterName;
        while (parameterNames.hasMoreElements()) {
            parameterName = parameterNames.nextElement();
            parameterMap.put(parameterName, httpServletRequest.getParameter(parameterName));
        }

        if (!parameterMap.isEmpty()) {
            requestLogMap.put("parameter", parameterMap);
        }
        //HTTP Attribute
//        Enumeration<String> requestAttributeNames = httpServletRequest.getAttributeNames();
//
//        Map<String, Object> attributeMap = new HashMap<>();
//        String attributeName;
//        while (requestAttributeNames.hasMoreElements()) {
//            attributeName = requestAttributeNames.nextElement();
//            if (!attributeName.startsWith("org.springframework")) {
//                attributeMap.put(attributeName, httpServletRequest.getAttribute(attributeName));
//            }
//        }
//
//		if (!attributeMap.isEmpty()) {
//			requestLogMap.put("attribute", attributeMap);
//		}

        if (o instanceof HandlerMethod) {
            requestLogMap.put("className", ((HandlerMethod) o).getBeanType().getName());
            requestLogMap.put("methodName", ((HandlerMethod) o).getMethod().getName());
        }

        String remoteAddr = getIpAddress(httpServletRequest);
        String messageBody = JacksonUtils.toJSon(requestLogMap);

        logger.debug("preHandle log, uri : {}, remoteAddr : {}, method : {}, request : {}",
                httpServletRequest.getRequestURI(), remoteAddr, httpServletRequest.getMethod(), messageBody);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //是在方法执行后开始返回前执行，可以进行日志记录、修改
//        logger.debug("");
//        logger.debug("postHandle log, state: {}", httpServletResponse.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) throws Exception {
        //最后执行，无论出错与否都会执行这个方法，可以用来记录异常信息和一些必要的操作记录
        logger.debug("afterCompletion log, state:{}, exception:{}", httpServletResponse.getStatus(), ex == null ? "" : ex.getMessage());
    }

    /**
     * 根据请求对象获取ip地址
     * @param request
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
