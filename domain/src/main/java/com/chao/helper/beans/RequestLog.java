package com.chao.helper.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称   ：domain
 * 类名称       ：RequestLog.java
 * 类描述       ：
 * 版本号       ：v1.0
 * 作者&时间 ：changjian  2016年3月30日
 */
public class RequestLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Number id;
    private String cpOrderId;
    private Number orderPaymentId;
    private Number workOrderId;
    private String remoteAddr;
    private Integer type;
    private String content;
    private Timestamp createDate;
    private Integer state;
    private String description;
    private String admin;
    private Long refenceId;
    
    public enum State{
		STATE(1,"正常"),
		NOT_STATE(0,"失效");
		
		private Integer code;
		private String name;
		
		private State(Integer code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public String getName() {
			return name;
		}
		
	}
    
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum RequestType {

        LOG_CP_REQUEST(1, "cp请求支付平台"),
        LOG_SP_REQUEST(2, "支付平台请求sp"),
        LOG_SP_RESPONSE(3, "sp响应支付平台"),
        LOG_SP_CALLBACK(4, "sp回调支付平台"),
        LOG_APP_MANAGER(5, "应用支付信息管理"),
        LOG_APP_NATURE_MANAGER(6, "应用附属信息管理"),
        LOG_APK_MANAGER(7, "APK信息管理"),
        LOG_CP_MANAGER(8, "CP支付信息管理"),
        LOG_CP_NATURE_MANAGER(9, "CP附属信息管理"),
        LOG_CP_USER_MANAGER(10, "CPUSER信息管理"),
        LOG_GOODRULE_MANAGER(11, "商品规则信息管理"),
        LOG_PRODUCT_MANAGER(12, "产品信息管理"),
        LOG_APP_RULE_MANAGER(13, "应用风控信息管理"),
        LOG_BLACK_RULE_MANAGER(14, "黑名单风控信息管理"),
        LOG_CP_RULE_MANAGER(15, "cp风控信息管理"),
        LOG_SUPPLIER_RULE_MANAGER(16, "通道规则信息管理"),
        LOG_WHITE_RULE_MANAGER(17, "白名单信息管理"),
        LOG_SUPPLIER_PARAM_MANAGER(18, "通道参数信息管理"),
        LOG_SUPPLIER_MANAGER(19, "通道信息管理"),
        LOG_USER_MANAGER(20, "后台用户信息管理"),
        LOG_CHANNEL_MANAGER(21, " 渠道信息管理"),
        LOG_GOODS_RELATE(22, " 商品管理"),
        LOG_VAC_MANAGER(23, "VAC信息管理"),
        LOG_PAY_MONTH_MANAGER(24, "VAC信息管理"),
        ;
        
        private int code;
        private String name;

        RequestType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static String getName(int code) {
            for (RequestType c : RequestType.values()) {
                if (c.getCode() == code) {
                    return c.getName();
                }
            }
            return null;
        }
    }

	/**
	 * @return 获取［refenceId］的值 
	 */
	public Long getRefenceId() {
		return refenceId;
	}
	/**	
	 *	设置 属性［refenceId］的值
	 * @param ［refenceId］ : 属性［refenceId］的值
	 */
	public void setRefenceId(Long refenceId) {
		this.refenceId = refenceId;
	}
	/**
	 * @return 获取［_description］的值 
	 */
	public String getDescription() {
		return description;
	}
	/**	
	 *	设置 属性［_description］的值
	 * @param ［_description］ : 属性［_description］的值
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return 获取［admin］的值 
	 */
	public String getAdmin() {
		return admin;
	}
	/**	
	 *	设置 属性［admin］的值
	 * @param ［admin］ : 属性［admin］的值
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public String getCpOrderId() {
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId) {
		this.cpOrderId = cpOrderId;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public Number getOrderPaymentId() {
		return orderPaymentId;
	}
	public void setOrderPaymentId(Number orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}
	public Number getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Number workOrderId) {
		this.workOrderId = workOrderId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
    
    
}
