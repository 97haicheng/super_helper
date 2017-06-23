//package com.chao.helper.amqp;
//
//
//import com.alibaba.dubbo.common.logger.Logger;
//import com.alibaba.dubbo.common.logger.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import java.util.Map;
//
//
//
///**
// * 功能包括：
// * 一、订单请求计费
// * 二、回调处理
// *
// */
//@Component
//public class BillingMQListener implements MQListener {
//    private static Logger LOGGER = LoggerFactory.getLogger(BillingMQListener.class);
//
//    @Autowired
//    private BuyFlowBiz buyFlowBiz;
//
//    @RabbitListener(queues = "buyFlow")
//    public void listen(Message message) {
//
//        MessageProperties mp = message.getMessageProperties();
//
//        if (mp == null) {
//            LOGGER.error("message properties is null");
//            return ;
//        }
//
//        Map<String, Object> map = mp.getHeaders();
//
//        if (map == null) {
//            LOGGER.error("message header is null");
//            return ;
//        }
//
//        if (MessageQueueKey.ORDER_CP_CREATE.equals(mp.getReceivedRoutingKey())) {
//            //订购处理
//            long timestamp = ObjectUtils.getLong(map.get("timeStamp"));
//            long time_end = System.currentTimeMillis();
//            LogPrinter.logUseTime("billing_payRequest", timestamp, time_end);
//
//
//
//            LOGGER.info("接受API订单="+map.get("orderId"));
//            System.out.println("接受API订单="+map.get("orderId"));
//            Integer tradeOrderId = Integer.parseInt(map.get("orderId").toString());
//            try {
//				buyFlowBiz.buyFlowAction(tradeOrderId);
//			} catch (Exception e) {
//	    		LogPrinter.logInfo(tradeOrderId.toString(), LogPrinter.logicInfo, new Object[]{"订单号:"
//	    				+ tradeOrderId, e});
//
//				e.printStackTrace();
//			}
//
//
//        } else if (MessageQueueKey.ORDER_CP_CONFIRM.equals(mp.getReceivedRoutingKey())) {
//        	//回调处理
//            long timestamp = ObjectUtils.getLong(map.get("timeStamp"));
//
//        }
//
//
//    }
//
//}
