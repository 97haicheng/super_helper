package com.chao.helper;

/**
 * @author by barry-acer on 2016/3/7.
 */
public class MessageQueueKey {
    /**
     * CP订单创建消息KEY
     */
    public static final String ORDER_CP_CREATE = "payment.order.create";
    public static final String REFUND = "payment.order.refound";
    /**
     * CP支付确认消息KEY
     */
    public static final String ORDER_CP_CONFIRM = "payment.order.confirm";
    /**
     * CP订单失败消息KEY
     */
    public static final String ORDER_CP_FAIL = "payment.order.fail";

    /**
     * SP支付确认消息KEY
     */
    public static final String ORDER_SP_CALLBACK = "payment.order.callback";

    public static final String REFUNDFAIL = "payment.order.refund.fail";

    public static final String REFUNDCONFIRMF = "payment.order.refund.confirmf";
    
    /**
     * 通道关停和启用消息key
     */
    public static final String SUPPLIER_OPEN_CLOSE = "payment.supplier.control";
    /**
     * 通道关停和启用消息key
     */
    public static final String PROVIDER_REGISTER = "payment.provider.register";

    /**
     * 通道关停和启用消息key
     */
    public static final String SUPPLIER_REGISTER = "payment.supplier.register";
    
    
    
    /**
     * 支付平台接口被调用的key
     */
    public static final String LOG_RECIEVE = "log.recieve";
    /**
     * 支付平台调用SP接口的key
     */
    public static final String LOG_REQUEST = "log.request";
    
    
}
