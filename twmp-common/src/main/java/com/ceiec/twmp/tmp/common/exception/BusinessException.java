package com.ceiec.twmp.tmp.common.exception;

/**
 * @packge com.ceiec.base.common.exception.BusinessException
 * @date 2017年8月22日 上午10:31:16
 * @author wenliang
 * @comment 业务逻辑异常类
 * @update
 */
public class BusinessException extends CEIECException {

	private static final int CODE = 100; // 业务逻辑异常类编码

	public BusinessException(String msg) {
		super(CODE, msg);
	}

	public BusinessException(String msg, Throwable cause) {
		super(CODE, msg, cause);
	}

}
