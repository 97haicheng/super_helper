package com.chao.helper.application;

import com.alibaba.dubbo.rpc.RpcException;
import com.chao.helper.MessageQueueKey;
import com.chao.helper.application.exception.RestApiException;
import com.chao.helper.controller.BaseController;
import com.chao.helper.enumeration.ErrorCodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler implements HandlerExceptionResolver {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (ex != null) {
            Object instance = ((HandlerMethod) handler).getBean();
            int code = ErrorCodeInfo.ERROR_129999.getCode();
            String message = ErrorCodeInfo.getErrorMsg(code);
            if (ex instanceof RestApiException) {
                RestApiException rae = (RestApiException) ex;
                if (rae.getParameters() == null || rae.getParameters().length == 0) {
                    logger.info(rae.getMessage());
                } else {
                    logger.info(rae.getMessage() + ", parameter:{}", rae.getParameters());
                }
                code = rae.getCode();
                message = rae.getMessage();
            } else if (ex instanceof RpcException) {
                logger.error("DUBBO Exception", ex);
            } else {
                logger.error("Unknown Exception", ex);
            }

            String result = null;
            if(instance instanceof BaseController) {
                try{
                    result = ((BaseController) instance).resultHandle(code, message, null, response);
                } catch (ServletException e) {
                    logger.error("handler ServletException", e);
                } catch (IOException e) {
                    logger.error("handler IOException", e);
                } catch (Throwable t) {
                    logger.error("handler Throwable", t);
                }
            }

            Object orderPaymentId = request.getAttribute("orderPaymentId");
            Object workOrderId = request.getAttribute("workOrderId");
            logger.info("[resolveException]orderPaymentId:{}, workOrderId:{}, errorCode:{}",
            		orderPaymentId, workOrderId, code);
            
            //发送消息
            rabbitTemplate.send(MessageQueueKey.ORDER_CP_FAIL,
                    MessageBuilder.withBody(new byte[0])
                            .setHeader("orderPaymentId", orderPaymentId == null ? 0 : orderPaymentId)
                            .setHeader("workOrderId", workOrderId == null ? 0 : workOrderId)
                            .setHeader("errorCode", code)
                            .setHeader("timeStamp", System.currentTimeMillis())
                            .build());


            if (result == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
        return null;
    }
}
