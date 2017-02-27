package hk.linktech.myflow.task;

import hk.linktech.flow.beans.channel.Channels;
import hk.linktech.flow.beans.trade.TradeNotifyCh;
import hk.linktech.flow.beans.trade.TradeWorksDetails;
import hk.linktech.flow.service.ChannelsService;
import hk.linktech.flow.service.TradeNotifyChService;
import hk.linktech.flow.utils.DigestUtils;
import hk.linktech.flow.utils.HttpClientUtil;
import hk.linktech.flow.utils.JacksonUtils;
import hk.linktech.flow.utils.LogPrinter;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 回调任务执行器
 */
@Component
public class CallBackTaskExecutor {


    @Autowired
    private ChannelsService channelsService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private TradeNotifyChService tradeNotifyChService;
    
    private String requestName = "回调渠道接口-";
    /**
     * 回调渠道接口
     * @param orderId 订单标识
     */
    public void callback(TradeWorksDetails twd) {
        
    	Channels ch = channelsService.queryById(twd.getChId());
    	if(ch == null)
    	{
    		LogPrinter.logInfo(twd.getChOrderId()+"", LogPrinter.logicInfo, new Object[]{requestName+"未查询到渠道信息"});
    		return;
    	}
    	if(ch.getChUrl() == null || "".equals(ch.getChUrl()))
    	{
    		LogPrinter.logInfo(twd.getChOrderId()+"", LogPrinter.logicInfo, new Object[]{requestName+"未查询到渠道回调地址"});
    		return;
    	}
    	taskExecutor.execute(new Task(ch, twd,tradeNotifyChService));
    }


    private static class Task implements Runnable {

        private static Logger logger = LoggerFactory.getLogger(Task.class);

        private Channels channel;
        private TradeWorksDetails twd;
        private TradeNotifyChService tradeNotifyChService;
        private String requestJson;

        public Task(Channels channel,TradeWorksDetails twd,TradeNotifyChService tradeNotifyChService) {

            logger.info("CallBack Task : {}", "方法开始");
            this.channel = channel;
            this.twd = twd;
            this.tradeNotifyChService = tradeNotifyChService;
        }

        public HttpRequestBase createRequest() {

            logger.info("CallBack createRequest : {}", "方法开始");

            //设置时间戳
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String timeStamp = df.format(new Date());

            Map<String, Object> result = new HashMap<>();

            result.put("chOrderId", twd.getChOrderId());
            result.put("orderId", twd.getWorkId());
            result.put("mobile", twd.getMobile());
            result.put("pay_amount", twd.getChConProPrice());
            result.put("pay_type", "1");
            result.put("code", twd.getStatusCode());
            result.put("msg", twd.getStatusMsg());
            result.put("timestamp", timeStamp);

            try {
                result.put("sign", DigestUtils.getSignature(result, channel.getChUrl()));
            } catch (IOException e) {
                logger.error("create request fail chOrderId="+twd.getChOrderId(), e);
            } catch (Throwable e) {
                logger.error("create request fail chOrderId="+twd.getChOrderId(), e);
            }

            HttpPost post = new HttpPost(channel.getChUrl());
            post.addHeader("charset", "utf-8");
            post.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
            
            try {
                String json = JacksonUtils.toJSon(result);
                requestJson = json;
                if(json != null) {
                    post.setEntity(new StringEntity(json, "UTF-8"));
                }
                logger.info("CallBack Message, URL : {}, JSON : {}", channel.getChUrl(), json);
            } catch (Exception e) {
                logger.error("create request fail", e);
            }

            //  设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(35000).setConnectTimeout(5000)
                    .build();
            post.setConfig(requestConfig);
            logger.info("CallBack createRequest : {}", "方法结束");
            return post;
        }

        public void processResponse(CloseableHttpResponse response) {

            logger.info("CallBack processResponse : {}", "方法开始");

            StatusLine sl = response.getStatusLine();

            String responseContent = null;
            try {
                responseContent = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (sl.getStatusCode() != 200) {
                logger.error("callback fail, info", responseContent);
            } else {
                logger.info("callback success, info", responseContent);
            }

            logger.info("CallBack processResponse : {}", "方法结束");
            // TODO 处理返回
        }

        public void run() {
        	
        	logger.info("CallBack run : 渠道回调 : {},订单号 : {}","方法开始",twd.getChOrderId());
        	String httpStatus = "";
        	/**
        	 * 增加渠道回调策略
        	 * 策略：如第一次回调失败，间隔一分钟后第二次回调，如第二次回调失败间隔5分钟后第三次回调
        	 * 优化点：后期回调次数和间隔可以根据渠道不同的产品进行配置
        	 * Author：Winner
        	 * Time：2016-10-17
        	 */
        	for(int i=1;i<4;i++)
        	{
        		logger.info("CallBack run : 第{}次回调,订单号 : {}", i,twd.getChOrderId());
	            logger.info("CallBack run : getHttpClient : {},订单号 : {}", "方法开始",twd.getChOrderId());
	            CloseableHttpClient httpClient = HttpClientUtil.getHttpClient(getTaskKey(),100);
	            logger.info("CallBack run : getHttpClient : {},订单号 : {}", "方法结束",twd.getChOrderId());

	            HttpRequestBase request = createRequest();
	            if (request == null) {
	                //支付信息不完整（尚有未完成的支付信息）
	                return ;
	            }
	           
	            CloseableHttpResponse response = null;
	            try {
	                logger.info("CallBack run : create Request : {}" + request.toString());
	                response = HttpClientUtil.sendRequest(httpClient, request);
	                logger.info("CallBack run : response : {}" + response);
	                processResponse(response);
	                
	                if (response != null) {
	                    httpStatus = String.valueOf(response.getStatusLine().getStatusCode());
	                }else{
	                    httpStatus = "9999";
	                } 
	
	            } catch (Exception e) {
	            	httpStatus = "9998";
	                logger.error("CallBack run : send request error,ChannelOrderId="+twd.getChOrderId(), e);
	            } finally {
                    try {
                        response.close();
                        //此处捕获异常存在问题，已经修改response.close()使用IO异常捕获如果出现连接为空的情况捕获不住
                    } catch (Exception e) {
                    	httpStatus = "9997";
                    	logger.error("CallBack run : close response error, ChannelOrderId="+twd.getChOrderId(), e);
                    }
                    try {
						request.releaseConnection();
					} catch (Exception e1) {
						httpStatus = "9996";
						logger.error("CallBack run : close connection error, ChannelOrderId="+twd.getChOrderId(), e1);
					}
	                logger.info("CallBack run : 第{}次回调结果:{},订单号 : {}", i,httpStatus,twd.getChOrderId());
	                //终止循环的条件,回调成功或最后一次处理
	                if("200".equals(httpStatus) || i == 3)
	                {
	                	break;
	                }
                	try {
                		logger.info("CallBack run : 第{}次回调结果:{},线程休眠开始,订单号 : {}", i,httpStatus,twd.getChOrderId());
						//线程休眠
						if(i == 1)//休眠1分钟
						{
							Thread.sleep(1 * 60 * 1000);
						}
						if(i == 2)//休眠5分钟
						{
							Thread.sleep(5 * 60 * 1000);
						}
						logger.info("CallBack run : 第{}次回调结果:{},线程休眠结束,订单号 : {}", i,httpStatus,twd.getChOrderId());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						httpStatus = "9995";
						logger.error("CallBack run : thread sleep error, ChannelOrderId="+twd.getChOrderId(), e);
						//如果休眠线程出现异常则直接终止循环
						break;
					}
	            }
        	}
        	/**
             * 入库标准
             * 1、HTTP状态200
             * 2、最后一次回调
             * Author：Winner
             * Time：2016-10-17
             */
        	logger.info("CallBack run : 回调结果:{}入库,订单号 : {}", httpStatus,twd.getChOrderId());
        	TradeNotifyCh tradeNotifyCh = new TradeNotifyCh();
        	tradeNotifyCh.setOrderId(twd.getOrderId());
        	tradeNotifyCh.setWorkId(twd.getWorkId());
        	tradeNotifyCh.setWorkDetailId(twd.getId());
        	tradeNotifyCh.setMobile(twd.getMobile());
        	tradeNotifyCh.setChId(twd.getChId());
        	tradeNotifyCh.setChOrderId(twd.getChOrderId());
        	tradeNotifyCh.setHttpStatus(httpStatus);
        	tradeNotifyCh.setCode(twd.getStatusCode());
        	tradeNotifyCh.setMsg(twd.getStatusMsg());
        	tradeNotifyCh.setUrl(channel.getChUrl());
        	tradeNotifyCh.setPara(requestJson);
        	tradeNotifyCh.setCreateTime(new Timestamp(new Date().getTime()));
        	
            try {
            	tradeNotifyChService.insertTradeNotifyCh(tradeNotifyCh);
			} catch (Exception e) {
				logger.error("CallBack run : insertCallBackLog error, ChannelOrderId="+twd.getChOrderId(), e);
			}
            logger.info("CallBack run : 渠道回调 : {},订单号 : {}","方法结束",twd.getChOrderId());
            
        }

        public String getTaskKey() {
            return "CALL-BACKS";
        }
    }
}
