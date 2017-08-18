package org.lushen.zhuifeng.springboot.validator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.BindException;
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
	
	private BindExceptionHandler bindExceptionHandler;
	
	public ValidatorBindExceptionResolver(BindExceptionHandler bindExceptionHandler) {
		super();
		this.bindExceptionHandler = bindExceptionHandler;
	}
	
	@ExceptionHandler(BindException.class)
    public void validExceptionHandler(BindException bindException, WebRequest request, HttpServletResponse response) {
		
		response.setContentType("application/json;charset=utf-8");
		
		try (PrintWriter out = response.getWriter()){
			
			String echoMsg = bindExceptionHandler.handleErrorAndHttpEcho(bindException);
			out.write(echoMsg);
			out.flush();
			
		} catch (IOException ex) {
			
			log.error(ex);
		}
	
    }

}
