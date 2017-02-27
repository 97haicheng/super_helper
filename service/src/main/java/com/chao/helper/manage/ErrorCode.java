package com.chao.helper.manage;

/**
 * 错误码
 * 
 * @ClassName: ErrCode
 * @Description: 错误码
 */

public class ErrorCode {
	private ErrorCode() {

	}

	// 操作成功
	public static int OP_SUCCESS = 1;
	// 操作失败
	public static int OP_FAILURE = 2;
	
	// 文件格式出错
	public static int ERR_FILE_FORMAT = 11;
	// 文件大小超限10M
	public static int ERR_FILE_SIZE = 12;
		
//	
//	/**
//	 * AJAX请求时，自己登录超时返回
//	 */
	public static final int AJAX_LOGIN = 2;
//
//	/**
//	 * 没有权限时返回提示
//	 */
//	public static final int NOT_AUTHC = 3;
//	
//	/**
//	 * CP审核邮件发送异常
//	 */
//	public static final int CP_AUDIT_EMAIL =  1; 
//	
//	/**
//	 * APP上线审核邮件发送异常
//	 */
//	public static final int APP_ONLINE_AUDIT_EMAIL =  1; 
//	
//	/**
//	 * 应用审核邮件发送异常
//	 */
//	public static final int APP_AUDIT_EMAIL =  1; 
//	
//	/**
//	 * 商品审核邮件发送异常
//	 */
//	public static final int GOODS_AUDIT_EMAIL =  1; 
//
//	/**
//	 * 系统错误码起始值
//	 */
	public static int START = 500;
//	
//	/**
//	 * 参数异常，请检查
//	 */
	public static int SYSTEM_PARARM = START++;
//
//	/**
//	 * 文件上传错误码起始值
//	 */
//	public static int FILE_START = 900;
//	
//	/**
//	 * 文件上传异常
//	 */
//	public static int FILE_UPLOAD_ERROR = FILE_START++;
//
//	/**
//	 * 应用错误码起始值
//	 */
//	private static int APP_START = 1200;
//
//	/**
//	 * cp管理错误码起始值
//	 */
//	private static int CP_START = 1500;
//	
//	/**
//	 * user管理错误码起始值
//	 */
//	private static int USER_START = 2000;
//	
//	/**
//	 * shellMeun管理错误码起始值
//	 */
//	private static int MEUN_START = 2500;
//	
//	/**
//	 * ROLE 错误码起始值
//	 */
//	private static int ROLE_START = 3000;
//	
//	
//	/**
//	 * ACCOUNT 账户错误码起始值
//	 */
//	private static int ACCOUNT_START = 3500;
//
//	/**
//	 * cp审核错误码起始值
//	 */
//	private static int AUDIT_CP_START = 4000;
//
//	/**
//	 * APP审核错误码起始值
//	 */
//	private static int AUDIT_APP_START = 4500;
//	
//	/**
//	 * GOODs审核错误码起始值
//	 */
//	private static int AUDIT_GOODS_START = 5000;
//	
//	/**
//	 * 风控错误码起始值
//	 */
//	private static int RISK_START = 5500;
//
//	/**
//	 * 通道维护错误码起始值
//	 */
//	private static int SUPPLIER_START = 6000;
//	
//	/**
//	 * 通道参数维护错误码起始值
//	 */
//	private static int SUPPLIER_PARAMS_START = 6500;
//
//	/**
//	 * 商品错误码起始值
//	 */
//	private static int GOODS_START = 7000;
//	
//	/**
//	 * 应用上线
//	 */
//	private static int APP_ONLINE_START = 7500;
//
//	
//	/**
//	 * 渠道
//	 */
//	private static int CHANNEL_START = 8000;
//	
//	/**
//	 * 运维管理
//	 */
//	private static int MAINTENANCE_START = 8500;
//	
//	/**
//	 * vac
//	 */
//	private static int VAC_START = 9000;
//	
//	/**
//	 * 通道参数修改异常
//	 */
//	public static final int SUPPLIER_PARAMS_UPDATE = SUPPLIER_PARAMS_START ++;
//
//	/**
//	 * 通道参数添加异常
//	 */
//	public static final int SUPPLIER_PARAMS_ADD = SUPPLIER_PARAMS_START ++;
//
//	/**
//	 * 当前通道参数已存在
//	 */
//	public static final int SUPPLIER_PARAMS_SUPID = SUPPLIER_PARAMS_START ++;
//	
//	/**
//	 * 通道添加缺少必要参数
//	 */
//	public static final int SUPPLIER_ADD_PARAMS =  SUPPLIER_START++; 
//
//	/**
//	 * 通道删除异常
//	 */
//	public static final int SUPPLIER_DEL =  SUPPLIER_START++; 
//
//	/**
//	 * user修改失败
//	 */
//	public static final int USER_UPDATE =  USER_START++; 
//
//	/**
//	 * user修改状态失败
//	 */
//	public static final int USER_UPDATE_STATE =  USER_START++; 
//
//	/**
//	 * user用户添加失败
//	 */
//	public static final int USER_ADD =  USER_START++; 
//
//	/**
//	 * 角色的权限赋值失败
//	 */
//	public static final int ROLE_PERMISSIONS =  ROLE_START++; 
//
//	/**
//	 * 角色的信息更新失败
//	 */
//	public static final int ROLE_UPDATE =  ROLE_START++; 
//	
//	/**
//	 * 角色的信息添加失败
//	 */
//	public static final int ROLE_ADD =  ROLE_START++; 
//
//	/**
//	 * cp审核异常
//	 */
//	public static final int CP_AUDIT =  AUDIT_CP_START++; 
//
//	/**
//	 * 没有指定客户经理
//	 */
//	public static final int CP_AUDIT_CONTRACTMANAGER =  AUDIT_CP_START++; 
//
//	/**
//	 * 文件上传失败
//	 */
//	public static final int CP_AUDIT_FILE =  AUDIT_CP_START++; 
//	
//	
//	
//
//	/**
//	 * 请选择CP审核状态
//	 */
//	public static final int CP_AUDIT_STATE =  AUDIT_CP_START++; 
//
//	/**
//	 * APP审核异常
//	 */
//	public static final int APP_AUDIT =  AUDIT_APP_START++; 
//
//	/**
//	 * 请选择或者输入驳回原因
//	 */
//	public static final int APP_AUDIT_REASON =  AUDIT_APP_START++; 
//
//	/**
//	 * 请选择应用审核状态
//	 */
//	public static final int APP_AUDIT_APP =  AUDIT_APP_START++; 
//	
//	/**
//	 * 计费点审核系统错误
//	 */
//	public static final int APP_AUDIT_GOODS =  AUDIT_APP_START++; 
//	
//	
//	/**
//	 * APP上线审核审核状态异常
//	 */
//	public static final int APP_ONLINE_AUDIT =  AUDIT_APP_START++; 
//
//	/**
//	 * 请选择应用审核上线审核状态
//	 */
//	public static final int APP_ONLINE_AUDIT_APP =  AUDIT_APP_START++; 
//
//	/**
//	 * 文件上传异常
//	 */
//	public static final int APP_ONLINE_FILE =  AUDIT_APP_START++; 
//
//	/**
//	 * 缺少支付流程文档
//	 */
//	public static final int APP_ONLINE_PAY_FILE =  AUDIT_APP_START++; 
//	
//
//	/**
//	 * 请选择审核驳回原因或者输入审核驳回原因
//	 */
//	public static final int APP_ONLINE_REASON =  AUDIT_APP_START++; 
//
//	/**
//	 * 文件上传异常
//	 */
//	public static final int APP_AUDIT_FILE =  AUDIT_APP_START++; 
//
//	
//
//
//	/**
//	 * goods审核异常
//	 */
//	public static final int GOODS_AUDIT =  AUDIT_GOODS_START++; 
//
//	/**
//	 * 请选择或者输入驳回原因
//	 */
//	public static final int GOODS_AUDIT_REASON =  AUDIT_GOODS_START++; 
//
//	/**
//	 * 文件上传异常
//	 */
//	public static final int GOODS_AUDIT_FILE =  AUDIT_GOODS_START++; 
//	
//	/**
//	 * 手机号码错误
//	 */
//	public static final int RISK_MOBILE = RISK_START++;
//	
//	/**
//	 * 手机号重复添加
//	 */
//	public static final int RISK_MOBILE_REPEAT = RISK_START++;
//	
//	/**
//	 * 参数类型错误
//	 */
//	public static final int RISK_PARAM_TYPE = RISK_START++;
//	
//	/**
//	 * 应用id格式错误
//	 */
//	public static final int RISK_APPID_ERROR =  RISK_START++;
//	
//	/**
//	 * 应用不存在
//	 */
//	public static final int RISK_APP_ERROR =  RISK_START++;
//	
//	/**
//	 * 省份不能为空
//	 */
//	public static final int RISK_PROVINCE = RISK_START++;
//	
//	/**
//	 * 日限额只能是数字
//	 */
//	public static final int RISK_DAY_LIME_ERROR = RISK_START++;
//	
//	/**
//	 * 日限额超出最大值
//	 */
//	public static final int RISK_DAY_LIME_MAX_ERROR = RISK_START++;
//	
//	/**
//	 * 月限额只能是数字
//	 */
//	public static final int RISK_MONTH_LIME_ERROR = RISK_START++;
//	
//	/**
//	 * 月限额超出最大值
//	 */
//	public static final int RISK_MONTH_LIME_MAX_ERROR = RISK_START++;
//	
//	/**
//	 * 月限额必须大于日限额
//	 */
//	public static final int RISK_DAY_GE_MONTH_ERROR = RISK_START++;
//	
//	/**
//	 * 日月限风控策略不存在
//	 */
//	public static final int RISK_DAY_MONTH_STRATEGY_ERROR = RISK_START++;
//	
//	/**
//	 * 支付频率只能是数字
//	 */
//	public static final int RISK_PAY_FREQUENCY = RISK_START++;
//	
//	/**
//	 * 支付频率超出最大值
//	 */
//	public static final int RISK_PAY_FREQUENCY_MAX_ERROR = RISK_START++;
//	
//	/**
//	 * 支付频率风控策略不存在
//	 */
//	public static final int RISK_FREQUENCY_STRATEGY_ERROR = RISK_START++;
//	
//	/**
//	 * 最大限额只能是数字
//	 */
//	public static final int RISK_MAX_LIMIT = RISK_START++;
//	
//	/**
//	 * 最大限额超出最大值
//	 */
//	public static final int RISK_MAX_LIMIT_MAX_ERROR = RISK_START++;
//	
//	/**
//	 * 最大限额风控策略不存在
//	 */
//	public static final int RISK_MAX_LIMIT_STRATEGY_ERROR = RISK_START++;
//	
//	/**
//	 * 重复添加用户行为风控
//	 */
//	public static final int RISK_USER_BEHAVIOR_EXIST = RISK_START++;
//	
//	/**
//	 * 重复添加应用省份风控
//	 */
//	public static final int RISK_APP_PROVINCE_EXIST = RISK_START++;
//	
//	/**
//	 * 通道id格式错误
//	 */
//	public static final int RISK_SUPPLIERID_ERROR =  RISK_START++;
//	
//	/**
//	 * 运营商不存在
//	 */
//	public static final int RISK_OPERATORS_NOT_EXIST = RISK_START++;
//	
//	/**
//	 * cpid格式错误
//	 */
//	public static final int RISK_CONTENT_PROVIDER_ERROR = RISK_START++;
//	
//	/**
//	 * 重复添加CP省份风控
//	 */
//	public static final int RISK_CONTENT_PROVIDER_PROVINCE_EXIST = RISK_START++;
//	
//	/**
//	 * CP风控id格式错误
//	 */
//	public static final int RISK_CONTENT_PROVIDER_RULE_ID_ERROR =  RISK_START++;
//	
//	/**
//	 * 重复添加通道省份风控
//	 */
//	public static final int RISK_SUPPLIERID_PROVINCE_EXIST = RISK_START++;
//	
//	/**
//	 * 金额号码错误
//	 */
//	public static final int RISK_PRICE = RISK_START++;
//
//	/*****************CP************************/
//	
//	/**
//	 * CP营业执照上传异常
//	 */
//	public static final int CP_BUL = CP_START++;
//
//	/**
//	 * 缺少营业执照
//	 */
//	public static final int CP_NOT_BUL = CP_START++;
//
//	/**
//	 * CP数据异常
//	 */
//	public static final int CP_ERROR = CP_START++;
//	
//	/**
//	 * 文件格式错误
//	 */
//	public static final int CP_BUL_ERROR = CP_START++;
//	
//	/**
//	 * CP组织机构代码证上传异常
//	 */
//	public static final int CP_OCC = CP_START++;
//
//	/**
//	 * 缺少组织机构代码证
//	 */
//	public static final int CP_NOT_OCC = CP_START++;
//	
//	/**
//	 * 组织机构代码证格式错误
//	 */
//	public static final int CP_OCC_ERROR = CP_START++;
//	
//	/**
//	 * CP税务登记证上传异常
//	 */
//	public static final int CP_TRC = CP_START++;
//
//	/**
//	 * 缺少税务登记证
//	 */
//	public static final int CP_NOT_TRC = CP_START++;
//	
//	/**
//	 * 税务登记证格式错误
//	 */
//	public static final int CP_TRC_ERROR = CP_START++;
//
//	/**
//	 * CP银行开户许可证上传异常
//	 */
//	public static final int CP_BOC = CP_START++;
//
//	/**
//	 * 缺少银行开户许可证
//	 */
//	public static final int CP_NOT_BOC = CP_START++;
//	
//	/**
//	 * 银行开户许可证格式错误
//	 */
//	public static final int CP_BOC_ERROR = CP_START++;
//	
//	/**
//	 * CP其他证件上传异常
//	 */
//	public static final int CP_OTHER = CP_START++;
//
//	/**
//	 * CP邮箱为空
//	 */
//	public static final int CP_EMAIL = CP_START++;
//	
//	/**
//	 * CP邮箱为空
//	 */
//	public static final int CP_EMAIL_EMPTY = CP_START++;
//	
//	/**
//	 * CP邮箱为格式错误
//	 */
//	public static final int CP_EMAIL_ERROR = CP_START++;
//
//	/**
//	 * CP名称为空
//	 */
//	public static final int CP_CPNAME = CP_START++;
//	
//	/**
//	 * 公司简称为空
//	 */
//	public static final int CP_ABBRE_NAME = CP_START++;
//	
//	/**
//	 * 联系人为空
//	 */
//	public static final int CP_CONTACT_PERSON = CP_START++;
//	
//	/**
//	 * 联系电话为空
//	 */
//	public static final int CP_CONTACT_TEL = CP_START++;
//	
//	/**
//	 * 手机号码格式错误
//	 */
//	public static final int CP_CONTACT_TEL_ERROR = CP_START++;
//	
//	/**
//	 * 邮箱不唯一
//	 */
//	public static final int CP_VALIDATOR_EMAIL = CP_START++;
//
//	/**
//	 * 公司名称已存在
//	 */
//	public static final int CP_VALIDATOR_NAME = CP_START++;
//	
//	/**
//	 * 客户经理为空
//	 */
//	public static final int CP_ACCOUNT_MANAGER = CP_START++;
//	
//	/**
//	 * 缺少商品数据
//	 */
//	public static final int APP_GOODS_ADD = APP_START++;
//
//	/**
//	 * 应用资质上传失败
//	 */
//	public static final int APP_APPAPT = APP_START++;
//
//	/**
//	 * 缺失计费形态
//	 */
//	public static final int APP_TYPE = APP_START++;
//
//	/**
//	 * 缺少apk
//	 */
//	public static final int APP_APK = APP_START++;
//
//	/**
//	 * APK文件有误
//	 */
//	public static final int APP_APK_ERROR = APP_START++;
//
//	/**
//	 * 应用名称为空
//	 */
//	public static final int APP_NAME = APP_START++;
//
//	/**
//	 * 应用名称已经存在
//	 */
//	public static final int APP_VALIDATOR_NAME = APP_START++;
//
//	/**
//	 * 缺少商品参数
//	 */
//	public static final int GOODS_ADD = GOODS_START++;
//	
//	/**
//	 * 商品名称为空
//	 */
//	public static final int GOODS_NAME = GOODS_START++;
//
//	/**
//	 * 商品名称已使用
//	 */
//	public static final int GOODSNAME_VALIDATOR = GOODS_START++;
//	
//	/**
//	 * 商品规则不存在
//	 */
//	public static final int GOODS_RULE_NOEXIST = GOODS_START++;
//	/**
//	 * 应用发布数据异常
//	 */
//	public static final int APP_ONLINE_ERROR = APP_ONLINE_START++;
//	
//	/**
//	 * 文件上传异常
//	 */
//	public static final int CP_FILE = CP_START++;
//
//	/**
//	 * 不是同一个apk文件
//	 */
//	public static final int APP_ONLINE_APKPACKAGE = APP_ONLINE_START++;
//
//	/**
//	 * 不是apk文件
//	 */
//	public static final int APP_ONLINE_APK = APP_ONLINE_START++;
//
//	/**
//	 * 一个应用只能在一个渠道发布一次
//	 */
//	public static final int APP_ONLINE_CHANNLE_APK = APP_ONLINE_START++;
//
//	/**
//	 * 渠道名称被使用了
//	 */
//	public static final int CHANNEL_NAME = CHANNEL_START++;
//
//	/**
//	 * 渠道码被使用了
//	 */
//	public static final int CHANNEL_CODE = CHANNEL_START++;
//
//	/**
//	 * 重置密码失败
//	 */
//	public static final int RESET_PASSWORD = CHANNEL_START++;
//
//	/**
//	 * 密码修改失败
//	 */
//	public static final int PASSWORD_UPDATE = CHANNEL_START++;
//	
//	/**
//	 * 邮箱已经被使用
//	 */
//	public static final int CP_USER_EMAIL_ERROR = CP_START++;
//
//	/**
//	 * 密码为空
//	 */
//	public static final int CP_USER_PASSWROD = CP_START++;
//
//	/**
//	 * 邮箱为空
//	 */
//	public static final int CP_USER_EMAIL = CP_START++;
//
//	/**
//	 * 商品价格不统一
//	 */
//	public static final int GOODS_NOT_EQ_PRICE = GOODS_START++;
//	
//	/**
//	 * 容器名称为空
//	 */
//	public static final int VESSEL_NAME_EMPTY = MAINTENANCE_START++;
//	
//	/**
//	 * 日志路径为空
//	 */
//	public static final int LOG_PATH_EMPTY = MAINTENANCE_START++;
//	
//	/**
//	 * 
//	 */
//	public static final int VAC_CODE_EMPTY = VAC_START++;
}
