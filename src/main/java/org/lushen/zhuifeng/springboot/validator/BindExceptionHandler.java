package org.lushen.zhuifeng.springboot.validator;

import org.springframework.validation.BindException;

import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * 异常处理器
 * 
 * @author hlm
 */
public interface BindExceptionHandler {

	public String handleErrorAndHttpEcho(BindException exception) throws JsonProcessingException ;
	
}
