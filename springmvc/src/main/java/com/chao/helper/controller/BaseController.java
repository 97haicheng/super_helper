package com.chao.helper.controller;

import com.chao.helper.application.exception.RestApiException;
import com.chao.helper.enumeration.ErrorCodeInfo;
import com.chao.helper.utils.JacksonUtils;
import com.chao.helper.utils.LogPrinter;
import com.chao.helper.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 控制层
 */
public abstract class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
//	protected RedisService redisService;
	/**
	 * 校验参数
	 * 
	 * @param order
	 */
	protected void validateParams(Order order) {
		
	}
	/**
	 * @param code
	 *            状态编码
	 * @param message
	 *            状态信息
	 * @param params
	 *            参数
	 * @param response
	 */
	public abstract String resultHandle(int code, String message,
			Map<String, Object> params, HttpServletResponse response)
			throws ServletException, IOException;

	/**
	 * 时效性
	 * 
	 * @return
	 */
	abstract long getEffectiveTime();

	/**
	 * 处理业务之前执行
	 * @param request
	 * @return
	 */
	public Order preCommonHandle(String param){
		LogPrinter.logDebug("", LogPrinter.genInfo,new Object[]{"----------请求预处理开始----------"});
		String cpId = "";
		Order order = null;
		if (getInterfaceName().equals("ObtainMobileRequest")) {// 取手机号请求
			cpId = StringUtils.StringtoNull(param);
		} else if (getInterfaceName().equals("payRequest_payConfirm")) {// 支付请求和确认支付请求
			String data = StringUtils.StringtoNull(param);
			if ("".equals(data)) {
				throw new RestApiException(ErrorCodeInfo.ERROR_110001);
			}
			try {
				order = JacksonUtils.fromJson(data, Order.class);
			} catch (Exception e) {
				throw new RestApiException(ErrorCodeInfo.ERROR_110001);
			}
			if (order == null) {
				throw new RestApiException(ErrorCodeInfo.ERROR_110015);
			}
			LogPrinter.logDebug(cpId, LogPrinter.reqInfo,
					new Object[] { "data", data });
			//order.setData(data);
			//cpId = order.getCpId();
		}
		if ("".equals(cpId)) {
			throw new RestApiException(ErrorCodeInfo.ERROR_110001);
		}

		LogPrinter.logDebug(cpId, LogPrinter.genInfo,new Object[]{"----------请求预处理结束----------"});	
		return order;
		
	}
	
	public abstract String getInterfaceName();
}
