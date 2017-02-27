package com.chao.helper.application.exception;


import com.chao.helper.application.PlatformCodingHandler;
import com.chao.helper.exception.PlatformException;

/**
 *
 * 20151013 修改 改为使用错误状态枚举
 *
 */
public class RestApiException extends PlatformException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4487940740367472093L;
	private PlatformCodingHandler codingHandler;
	private Object[] parameters;

	public RestApiException(PlatformCodingHandler codingHandler, Object[] parameters){
		super(codingHandler.getMessage());
		this.codingHandler = codingHandler;

		if(parameters != null && parameters.length > 0) {
			this.parameters = parameters;
		}
	}

	public RestApiException(PlatformCodingHandler codingHandler){
		super(codingHandler.getMessage());
		this.codingHandler = codingHandler;
	}

	public RestApiException(PlatformCodingHandler codingHandler, Throwable t){
		super(codingHandler.getMessage(), t);
		this.codingHandler = codingHandler;
	}

	public RestApiException(Throwable t){
		super(t);
	}
	
	public RestApiException(String msg){
		super(msg);
	}

	public int getCode() {
		return codingHandler.getCode();
	}

	public Object[] getParameters() {
		return parameters;
	}
}
