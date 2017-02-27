package com.chao.helper.manage;

/**
 * 消息实体
 * @ClassName: Message
 * @Description: 消息实体
 * @author zhangge
 */
public class Message {

	/**
	 * 错误码
	 */
	private int code;
	
	/**
	 * 消息描述
	 */
	private String msg;

	public Message() {
		this.code = ErrorCode.OP_SUCCESS;
		this.msg = ErrorDesc.ERROR_DESC.get(code).trim();;
	}

	public Message(int code) {
		super();
		this.code = code;
		this.msg = ErrorDesc.ERROR_DESC.get(code).trim();
	}

	public Message(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		this.msg = ErrorDesc.ERROR_DESC.get(code).trim();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return "{\"code\":" + code + ", \"msg\":\"" + msg + "\"}";
	}
}
