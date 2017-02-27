package com.chao.helper.manage;

import java.util.HashMap;
import java.util.Map;


/**
 * 错误码描述
 * @ClassName: ErrorDesc
 * @Description: 错误码描述
 */
public class ErrorDesc {
	private ErrorDesc(){
		
	}
	
	/**
	 * 错误码描述
	 */
	public static final Map<Integer, String> ERROR_DESC = new HashMap<Integer, String>();
	
	static{
		ERROR_DESC.put(ErrorCode.OP_SUCCESS, "操作成功！");
		ERROR_DESC.put(ErrorCode.OP_FAILURE, "操作失败！");
		ERROR_DESC.put(ErrorCode.ERR_FILE_FORMAT, "文件格式出错！");
		ERROR_DESC.put(ErrorCode.ERR_FILE_SIZE, "文件大小超过10M限制！");
		
		
		
//		ERROR_DESC.put(ErrorCode.SHELL_OK, "ok");
//		ERROR_DESC.put(ErrorCode.AJAX_LOGIN, "登录超时");
//		ERROR_DESC.put(ErrorCode.NOT_AUTHC, "权限不足");
//		/***********************系统级异常***********************/
		ERROR_DESC.put(ErrorCode.SYSTEM_PARARM, "参数异常，请检查");
//		/***********************文件上传异常***********************/
//		ERROR_DESC.put(ErrorCode.FILE_UPLOAD_ERROR, "文件上传异常，请检查");
//		
//		ERROR_DESC.put(ErrorCode.USER_UPDATE, "修改用户信息失败");
//		ERROR_DESC.put(ErrorCode.USER_UPDATE_STATE, "修改用户状态失败");
//		ERROR_DESC.put(ErrorCode.USER_ADD, "添加用户信息失败");
//		
//		/***********************CP审核异常***********************/
//		ERROR_DESC.put(ErrorCode.CP_AUDIT, "CP审核异常");
//		ERROR_DESC.put(ErrorCode.CP_AUDIT_STATE, "请选择CP审核状态");
//		ERROR_DESC.put(ErrorCode.CP_AUDIT_FILE, "文件上传异常");
//		ERROR_DESC.put(ErrorCode.CP_AUDIT_CONTRACTMANAGER, "没有指定客户经理");
//		
//		/***********************应用审核异常***********************/
//		ERROR_DESC.put(ErrorCode.APP_AUDIT, "应用审核异常");
//		ERROR_DESC.put(ErrorCode.APP_AUDIT_APP, "请选择应用的审核状态");
//		ERROR_DESC.put(ErrorCode.APP_AUDIT_GOODS, "计费点审核系统错误");
//		ERROR_DESC.put(ErrorCode.APP_AUDIT_EMAIL, "应用审核邮件发送异常");
//		ERROR_DESC.put(ErrorCode.APP_AUDIT_FILE, "文件上传异常");
//		ERROR_DESC.put(ErrorCode.APP_AUDIT_REASON, "请选择或者输入驳回原因");
//		
//		/***********************商品审核异常***********************/
//		ERROR_DESC.put(ErrorCode.GOODS_AUDIT, "商品审核异常");
//		ERROR_DESC.put(ErrorCode.GOODS_AUDIT_REASON, "请选择或者输入驳回原因");
//		ERROR_DESC.put(ErrorCode.GOODS_RULE_NOEXIST, "被关联的商品规则不存在");
//
//		/***********************风控***********************/
//		ERROR_DESC.put(ErrorCode.RISK_MOBILE, "手机号码错误");
//		ERROR_DESC.put(ErrorCode.RISK_MOBILE_REPEAT, "手机号码重复");
//		ERROR_DESC.put(ErrorCode.RISK_PARAM_TYPE, "参数类型错误");
//		ERROR_DESC.put(ErrorCode.RISK_APPID_ERROR, "应用id格式错误");
//		ERROR_DESC.put(ErrorCode.RISK_APP_ERROR, "应用不存在");
//		ERROR_DESC.put(ErrorCode.RISK_PROVINCE, "省份不能为空");
//		ERROR_DESC.put(ErrorCode.RISK_DAY_LIME_ERROR, "日限额只能是数字");
//		ERROR_DESC.put(ErrorCode.RISK_DAY_LIME_MAX_ERROR, "日限额超出最大值");
//		ERROR_DESC.put(ErrorCode.RISK_MONTH_LIME_MAX_ERROR, "月限额超出最大值");
//		ERROR_DESC.put(ErrorCode.RISK_DAY_GE_MONTH_ERROR, "月限额必须大于日限额");
//		ERROR_DESC.put(ErrorCode.RISK_DAY_MONTH_STRATEGY_ERROR, "日月限风控策略不存在");
//		ERROR_DESC.put(ErrorCode.RISK_PAY_FREQUENCY, "支付频率只能是数字");
//		ERROR_DESC.put(ErrorCode.RISK_PAY_FREQUENCY_MAX_ERROR, "支付频率超出最大值");
//		ERROR_DESC.put(ErrorCode.RISK_FREQUENCY_STRATEGY_ERROR, "支付频率风控策略不存在");
//		ERROR_DESC.put(ErrorCode.RISK_MAX_LIMIT, "最大限额只能是数字");
//		ERROR_DESC.put(ErrorCode.RISK_MAX_LIMIT_MAX_ERROR, "最大限额超出最大值");
//		ERROR_DESC.put(ErrorCode.RISK_MAX_LIMIT_STRATEGY_ERROR, "最大限额风控策略不存在");
//		ERROR_DESC.put(ErrorCode.RISK_USER_BEHAVIOR_EXIST, "重复添加用户行为风控");
//		ERROR_DESC.put(ErrorCode.RISK_APP_PROVINCE_EXIST, "重复添加应用省份风控");
//		ERROR_DESC.put(ErrorCode.RISK_SUPPLIERID_ERROR, "通道id格式错误");
//		ERROR_DESC.put(ErrorCode.RISK_OPERATORS_NOT_EXIST, "运营商不存在");
//		ERROR_DESC.put(ErrorCode.RISK_CONTENT_PROVIDER_ERROR, "cpid格式错误");
//		ERROR_DESC.put(ErrorCode.RISK_CONTENT_PROVIDER_PROVINCE_EXIST, "重复添加CP省份风控");
//		ERROR_DESC.put(ErrorCode.RISK_CONTENT_PROVIDER_RULE_ID_ERROR, "CP风控id格式错误");
//		ERROR_DESC.put(ErrorCode.RISK_SUPPLIERID_PROVINCE_EXIST, "重复添加通道省份风控");
//		/***********************风控end***********************/
//		
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_AUDIT_EMAIL, "应用上线审核邮件发送异常");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_AUDIT_APP, "请选择应用审核上线审核状态");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_REASON, "请选择或者输入驳回原因");
//
//		/***********************通道维护***********************/
//		ERROR_DESC.put(ErrorCode.SUPPLIER_ADD_PARAMS, "通道添加缺少必要参数");
//		ERROR_DESC.put(ErrorCode.SUPPLIER_DEL, "通道删除异常");
//
//		/***********************通道参数维护***********************/
//		ERROR_DESC.put(ErrorCode.SUPPLIER_PARAMS_UPDATE, "通道参数修改异常");
//		ERROR_DESC.put(ErrorCode.SUPPLIER_PARAMS_ADD, "通道参数添加异常");
//		ERROR_DESC.put(ErrorCode.SUPPLIER_PARAMS_SUPID, "当前通道参数已存在");
//		
//		/***********************CP***********************/
//		ERROR_DESC.put(ErrorCode.CP_BUL, "营业执照上传异常");
//		ERROR_DESC.put(ErrorCode.CP_NOT_BUL, "缺少营业执照");
//		ERROR_DESC.put(ErrorCode.CP_BUL_ERROR, "营业执照格式错误(jpg|png)");
//		ERROR_DESC.put(ErrorCode.CP_OCC, "组织机构代码证上传异常");
//		ERROR_DESC.put(ErrorCode.CP_NOT_OCC, "缺少组织机构代码证");
//		ERROR_DESC.put(ErrorCode.CP_OCC_ERROR, "组织机构代码证格式错误(jpg|png)");
//		ERROR_DESC.put(ErrorCode.CP_TRC, "税务登记证上传异常");
//		ERROR_DESC.put(ErrorCode.CP_NOT_TRC, "缺少税务登记证");
//		ERROR_DESC.put(ErrorCode.CP_TRC_ERROR, "税务登记证格式错误(jpg|png)");
//		ERROR_DESC.put(ErrorCode.CP_BOC, "银行开户许可证上传异常");
//		ERROR_DESC.put(ErrorCode.CP_NOT_BOC, "缺少银行开户许可证");
//		ERROR_DESC.put(ErrorCode.CP_BOC_ERROR, "银行开户许可证格式错误(jpg|png)");
//		ERROR_DESC.put(ErrorCode.CP_OTHER, "其他证件上传异常");
//		ERROR_DESC.put(ErrorCode.CP_EMAIL, "账号为空");
//		ERROR_DESC.put(ErrorCode.CP_EMAIL_EMPTY, "邮箱为空");
//		ERROR_DESC.put(ErrorCode.CP_EMAIL_ERROR, "邮箱格式错误");
//		ERROR_DESC.put(ErrorCode.CP_CPNAME, "公司名称为空");
//		ERROR_DESC.put(ErrorCode.CP_ABBRE_NAME, "公司简称为空");
//		ERROR_DESC.put(ErrorCode.CP_CONTACT_PERSON, "联系人为空");
//		ERROR_DESC.put(ErrorCode.CP_CONTACT_TEL, "联系电话为空");
//		ERROR_DESC.put(ErrorCode.CP_CONTACT_TEL_ERROR, "手机号码格式错误");
//		ERROR_DESC.put(ErrorCode.CP_VALIDATOR_EMAIL, "账号被占用");
//		ERROR_DESC.put(ErrorCode.CP_VALIDATOR_NAME, "公司名称被占用");
//		ERROR_DESC.put(ErrorCode.CP_ACCOUNT_MANAGER, "客户经理为空");
//		ERROR_DESC.put(ErrorCode.CP_ERROR, "数据异常");
//		
//		/***********************开发者平台用户注册***********************/
//		ERROR_DESC.put(ErrorCode.CP_USER_EMAIL_ERROR, "邮箱已经被使用");
//		ERROR_DESC.put(ErrorCode.CP_USER_PASSWROD, "密码为空");
//		ERROR_DESC.put(ErrorCode.CP_USER_EMAIL, "邮箱为空");
//		
//		
//		/***********************应用***********************/
//		ERROR_DESC.put(ErrorCode.APP_GOODS_ADD, "缺少商品数据");
//		ERROR_DESC.put(ErrorCode.APP_APPAPT, "应用资质上传失败");
//		ERROR_DESC.put(ErrorCode.APP_TYPE, "缺少计费形态");
//		ERROR_DESC.put(ErrorCode.APP_TYPE, "缺少apk");
//		ERROR_DESC.put(ErrorCode.APP_NAME, "应用名称为空");
//		ERROR_DESC.put(ErrorCode.APP_VALIDATOR_NAME, "应用名称被占用");
//		ERROR_DESC.put(ErrorCode.APP_APK_ERROR, "APK文件有误");
//		
//		/***********************商品***********************/
//		ERROR_DESC.put(ErrorCode.GOODS_ADD, "缺少商品数据");
//		ERROR_DESC.put(ErrorCode.GOODS_NAME, "商品名称为空");
//		ERROR_DESC.put(ErrorCode.GOODSNAME_VALIDATOR, "商品名称被占用");
//		ERROR_DESC.put(ErrorCode.GOODS_NOT_EQ_PRICE, "两个商品价格不统一");
//		
//		/***********************应用发布***********************/
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_ERROR, "应用发布数据异常");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_APKPACKAGE, "不是同一个apk文件");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_CHANNLE_APK, "一个应用只能在一个渠道发布一次");
//		
//		/***********************应用发布***********************/
//		ERROR_DESC.put(ErrorCode.CHANNEL_NAME, "渠道名称已被使用");
//		ERROR_DESC.put(ErrorCode.CHANNEL_CODE, "渠道码已被使用");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_PAY_FILE, "缺少支付流程文档");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_APK, "apk文件不被支持或者不是apk文件");
//		ERROR_DESC.put(ErrorCode.APP_ONLINE_FILE, "支付流程文档");
//		
//		/***********************密码修改***********************/
//		ERROR_DESC.put(ErrorCode.RESET_PASSWORD, "密码重置失败");
//		ERROR_DESC.put(ErrorCode.PASSWORD_UPDATE, "密码修改失败");
//		
//		/***********************运维维护***********************/
//		ERROR_DESC.put(ErrorCode.VESSEL_NAME_EMPTY, "容器名称不能为空");
//		ERROR_DESC.put(ErrorCode.LOG_PATH_EMPTY, "日志路径不能为空");
//		
//		/***********************VAC管理***********************/
//		ERROR_DESC.put(ErrorCode.VAC_CODE_EMPTY, "企业VAC或产品VAC为空");
//		
	}
}
