package com.chao.helper.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;


public class PlatformErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(PlatformErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        //TODO 分类异常处理
        logger.error("fail", t);

        for (Throwable _t : t.getSuppressed()) {
            logger.error("fail", _t);
        }
    }
}
