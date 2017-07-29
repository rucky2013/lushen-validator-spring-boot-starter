package org.lushen.zhuifeng.springboot.validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


/**
 * BindException 异常处理器，统一处理为json串返回
 * 
 * @author hlm
 */
@ControllerAdvice
@ConditionalOnProperty(prefix=ValidatorProperties.VALIDATOR_PREFIX, name="enabled", matchIfMissing=true)
public class ValidatorBindExceptionResolver {
	
	private Log log = LogFactory.getLog(getClass());
	
	private ValidatorProperties properties;
	
	public ValidatorBindExceptionResolver(ValidatorProperties properties) {
		super();
		this.properties = properties;
	}
	
	@ExceptionHandler(BindException.class)
    public void validExceptionHandler(BindException bindException, WebRequest request, HttpServletResponse response) {
		
		response.setContentType("application/json;charset=utf-8");
		
		try (PrintWriter out = response.getWriter()){
			
			List<FieldError> fieldErrors = bindException.getFieldErrors();
			
			ValidatedEchoBean echoBean = new ValidatedEchoBean(fieldErrors.size());
			echoBean.setErrcode(this.properties.getErrcode());
			echoBean.setMsg(this.properties.getMsg());
			
			for(FieldError fieldError : fieldErrors) {
				
				String field = fieldError.getField();
				String msg = fieldError.getDefaultMessage();
				
				echoBean.addValidatedError(new ValidatedError(field, msg));
			}
			
			out.write(echoBean.toJsonString());
			out.flush();
			
		} catch (IOException ex) {
			
			log.error(ex);
		}
	
    }

}
