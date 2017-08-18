package org.lushen.zhuifeng.springboot.validator;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 默认的异常处理器
 * 
 * @author hlm
 */
public class DefaultBindExceptionHandler implements BindExceptionHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String handleErrorAndHttpEcho(BindException exception) throws JsonProcessingException {

		List<FieldError> fieldErrors = exception.getFieldErrors();
		
		ValidatedEchoBean echoBean = new ValidatedEchoBean(fieldErrors.size());
		echoBean.setErrcode(400);
		echoBean.setMsg("请求参数错误");
		
		for(FieldError fieldError : fieldErrors) {
			
			String field = fieldError.getField();
			String msg = fieldError.getDefaultMessage();
			
			echoBean.addValidatedError(new ValidatedError(field, msg));
		}
		
		return objectMapper.writeValueAsString(echoBean);
	}

}
