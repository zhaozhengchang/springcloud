package com.ceiec.twmp.tmp.common.exception;


/**
 * @packge CEIECException
 * @date 2017年8月22日 上午10:32:01
 * @author wenliang
 * @comment 系统所有异常的父类
 * @update
 */
public class CEIECException extends RuntimeException  {
	private int code = -1;//系统所有异常的父类编码

	public CEIECException() {

	}

	public CEIECException(int code, String message) {
		super(message);
		this.code = code;
	}

	public CEIECException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
