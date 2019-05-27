package com.ceiec.twmp.tmp.utils;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.common.exception.CEIECException;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: GlobalExceptionHandler.java
 * @Package com.ceiec.gis.springboot.utils
 * @Description: 全局处理 Controller 层异常工具类
 * @author wujiong
 * @date 2017年11月14日 下午11:51:32
 * @version V1.0
 */
@ControllerAdvice
public class GlobalExceptionAdvisor {
	Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvisor.class);

	/**
	 * 提取Validator产生的异常错误
	 * @param bindingResult
	 * @return
	 */
	private CEIECException parseBindingResult(BindingResult bindingResult){
		Map<String,String> errorMsgs = new HashMap<String,String>();
		for (FieldError error:bindingResult.getFieldErrors()){
			errorMsgs.put(error.getField(),error.getDefaultMessage());
		}
		if(errorMsgs.isEmpty()) {
			return new ParameterException(ResponseType.ILLEGAL_PARAMETER.getDesc());
		} else {
			return new ParameterException(JsonUtils.toJSONString(errorMsgs));
		}
	}

	/**
	 * 捕获@Validate校验抛出的异常
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseContent validExceptionHandler(BindException e, HttpServletRequest request, HttpServletResponse response) {
		CEIECException ex = parseBindingResult(e.getBindingResult());
		logger.error(ex.getMessage());
        return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,ex.getMessage());
    }

	/**
	 * 捕获@Validate校验抛出的异常,处理所有接口数据验证异常
	 * @param e
	 * @param request
	 * @param response
	 * @return
	 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseContent validException2Handler(MethodArgumentNotValidException e, HttpServletRequest request, HttpServletResponse response) {
		CEIECException ex = parseBindingResult(e.getBindingResult());
		logger.error(ex.getMessage());
		return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,ex.getMessage());
    }

	/**
	 * CEIECException
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = CEIECException.class)
	@ResponseBody
	public ResponseContent gisExceptionHandler(HttpServletRequest req, CEIECException e) {
		logger.error(e.getMessage());
		return new ResponseContent(ResponseType.ILLEGAL_PARAMETER,e.getMessage());
	}

	/**
	 * 全局Exception
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseContent exceptionHandler(HttpServletRequest req, Exception e) {
		logger.error(e.toString());
    	return new ResponseContent(ResponseType.UNKNOWN,e.getMessage());
    }


}

