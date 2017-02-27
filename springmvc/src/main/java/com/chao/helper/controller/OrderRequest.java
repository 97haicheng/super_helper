package com.chao.helper.controller;

import com.alibaba.fastjson.JSONArray;
import com.chao.helper.MessageQueueKey;
import com.chao.helper.Order;
import com.chao.helper.application.exception.RestApiException;
import com.chao.helper.beans.UserTable;
import com.chao.helper.enumeration.ErrorCodeInfo;
import com.chao.helper.service.UserTableService;
import com.chao.helper.utils.DigestUtils;
import com.chao.helper.utils.JacksonUtils;
import com.chao.helper.utils.LogPrinter;
import com.codahale.metrics.annotation.Timed;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求订购接口
 * @author winner
 * @time 2017-01-14
 */
@Controller
public class OrderRequest extends BaseController {

	@Autowired
	private UserTableService userTableService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private String requestName = "订购请求接口-";
	/**
	 * 方法说明：请求订购接口
	 * author Winner
	 * time 2017-01-09
	 */
	@RequestMapping("/v1/payRequest")
	@Timed
	public void payRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//响应接口参数集合
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		request.setCharacterEncoding("utf-8");
		Order order = new Order();
		order.setChOrderId(request.getParameter("chOrderId"));
		order.setChId(request.getParameter("chId"));
		order.setAgCodeProId(request.getParameter("agCodeProId"));
		order.setType(request.getParameter("type"));
		order.setMobiles(request.getParameter("mobiles"));
		order.setTimestamp(request.getParameter("timestamp"));
		order.setSign(request.getParameter("sign"));
		LogPrinter.logInfo(order.getChOrderId(), LogPrinter.reqInfo,new Object[]{requestName+"订单信息=" + JSONArray.toJSON(order)});
		try {
			// 检验输入参数
			validateParams4Request(order);
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.reqInfo,new Object[]{requestName+"校验参数通过"});
			// 校验业务信息
			validateOrderInfo(order);
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.reqInfo,new Object[]{requestName+"校验业务信息通过"});
			// 校验签名	
			validateSign4PayRequest(order);
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.reqInfo,new Object[]{requestName+"校验签名信息通过"});
			
			// 校验手机号是否能够订购该产品，如果没有查出省份信息默认为可以调用订购
			JSONArray jsonArray = JSONArray.parseArray(String.valueOf(order.getMobiles()));
			List<String> mobiles_success = new ArrayList<String>();
			List<String> mobiles_error = new ArrayList<String>();
			// 存储手机号的码表信息，用于落地运营商和省份信息
			Map<String, UserTable> map = new HashMap<String,UserTable>();
			//记录错误手机号码
			String errorMobile = "";
			for(int i=0;i<jsonArray.size();i++){
				// 验证手机号的归属是否支持产品订购
				String mobile = jsonArray.getString(i);
				UserTable ut = userTableService.queryUserTableByUsernum(mobile.substring(0,7));

			}
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.reqInfo,new Object[]{requestName+"校验手机号通过","验证成功="+mobiles_success.size(),"验证失败="+mobiles_error.size(),"错误手机号码="+errorMobile});
			//发送MQ给Billing处理业务
			// 发送消息
			rabbitTemplate.send(MessageQueueKey.ORDER_CP_CREATE,
					MessageBuilder.withBody(new byte[0])
							.setHeader("orderId", 111)
							.setHeader("timeStamp", System.currentTimeMillis())
							.build());
			resMap.put("errMobile", JacksonUtils.toJSon(mobiles_error));
		} catch (RestApiException e) {
			int code = ErrorCodeInfo.getErrorCode(e.getMessage());
			resultHandle(code, e.getMessage(), resMap, response);
			return;
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			resultHandle(ErrorCodeInfo.ERROR_129999.getCode(), ErrorCodeInfo.ERROR_129999.getMessage(),resMap, response);
			return;
		}

		resultHandle(0, "success", resMap, response);
		
	}
	/**
	 * 验证参数信息
	 * @param order
	 * author Winner
	 * time 2017-01-10 
	 */
	protected void validateParams4Request(Order order) {
		LogPrinter.logInfo(order.getChOrderId(), LogPrinter.genInfo,new Object[]{requestName+"----------验证参数开始----------"});	
		//检验订单
		if(order.getChOrderId() == null || "".equals(order.getChOrderId()))
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		if(order.getChOrderId().length() > 32)
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110002);
		}
		//检验渠道
		if(order.getChId() == null || "".equals(order.getChId()))
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		if(order.getChId().length() > 11)
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110002);
		}
		//检验商品代码
		if(order.getAgCodeProId() == null || "".equals(order.getAgCodeProId()))
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		//类型，用于区分网页调用和API调用
		if(order.getType() == null || "".equals(order.getType()))
		{
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		// 校验手机号
		if (order.getMobiles() == null || "".equals(order.getMobiles())) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		JSONArray jsonArray = JSONArray.parseArray(String.valueOf(order.getMobiles()));
		if (jsonArray == null || jsonArray.size() < 1 || jsonArray.size() > 100) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		// 校验时间戳
		if (order.getTimestamp() == null || "".equals(order.getTimestamp())) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}

		if (order.getTimestamp().length() != 10) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110002);
		}

		// 校验签名
		if (order.getSign() == null || "".equals(order.getSign())) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}
		long timestamp = Long.parseLong(order.getTimestamp());
		// 服务器时间校验
		long now = System.currentTimeMillis() / 1000;
		if ((now - timestamp) > getEffectiveTime()) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110004);
		}
		
		LogPrinter.logInfo(order.getChOrderId(),LogPrinter.genInfo,new Object[]{requestName+"----------验证参数结束----------"});
	}
	/**
	 * 方法说明：验证合作方基础信息
	 */
	private void validateOrderInfo(Order order) {
		LogPrinter.logInfo(order.getChOrderId(),LogPrinter.genInfo,new Object[]{requestName+"----------业务信息验证开始----------"});
		// 校验渠道信息
		int chid = 0;
		try {
			chid = Integer.parseInt(order.getChId());
		} catch (NumberFormatException e) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110002);
		}
		LogPrinter.logInfo(order.getChOrderId(),LogPrinter.genInfo,new Object[]{requestName+"----------业务信息验证结束----------"});
	}
	/**
	 * 验证订购接口签名
	 * @param order
	 * @author winner
	 * @time 2017-01-11
	 */
	protected void validateSign4PayRequest(Order order) {
		// 检测签名是否合法
		LogPrinter.logInfo(order.getChOrderId(),LogPrinter.genInfo,new Object[]{requestName+"----------验签开始----------"});
		Map<String, Object> params = new HashMap<>();
		params.put("chOrderId", order.getChOrderId());
		params.put("chId", order.getChId());
		params.put("agCodeProId", order.getAgCodeProId());
		params.put("type", order.getType());
		params.put("mobiles", order.getMobiles());
		params.put("timestamp", order.getTimestamp());
		//params.put("sign", order.getSign());

		try {
			String localSign = DigestUtils.getSignature(params, "111");
			//logger.debug(，加密后字符串:{}", params.toString(), localSign.toUpperCase());
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.logicInfo,new Object[]{"接口参数签名="+order.getSign()});
			LogPrinter.logInfo(order.getChOrderId(),LogPrinter.genInfo,new Object[]{"服务器的签名="+localSign});
			if (!localSign.equals(order.getSign())) {
				throw new RestApiException(ErrorCodeInfo.ERROR_110003);
			}
		} catch (Exception e) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110003, e);
		}
		LogPrinter.logInfo(order.getChOrderId(), LogPrinter.genInfo,new Object[]{requestName+"----------验签结束----------"});
	}


	/******************** 确认订购接口的处理：结束 ********************/
	/**
	 * 统一返回值接口
	 * @author winner
	 * @time 2017-01-11
	 */
	public String resultHandle(int code, String message,
			Map<String, Object> params, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("err", String.valueOf(code));
		resultMap.put("msg", message);
		
		if (null != params) {
			for (String key : params.keySet()) {
				resultMap.put(key, params.get(key));
			}
		}
		if(resultMap.get("chKey") != null && !"".equals(resultMap.get("chKey").toString()))
		{
			String chKey = resultMap.get("chKey")+"";
			resultMap.remove("chKey");
			String sign = DigestUtils.getSignature(resultMap, chKey);
			resultMap.put("sign", sign);
		}

		String result = JacksonUtils.toJSon(resultMap);
		//logger.debug("response, data:{}", result);
		LogPrinter.logInfo("",LogPrinter.resInfo,new Object[]{"response, data:{}", result});

		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		return result;
	}

	long getEffectiveTime() {
		return 12 * 60 * 60;
	}

	public String getInterfaceName() {
		// TODO Auto-generated method stub
		return null;
	}

}
