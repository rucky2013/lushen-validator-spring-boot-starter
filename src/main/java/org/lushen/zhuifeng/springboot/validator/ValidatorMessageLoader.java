package org.lushen.zhuifeng.springboot.validator;

import java.util.Properties;

/**
 * 内置用于代码加载验证信息接口，实现接口并配置为spring bean
 * 
 * @author hlm
 */
public interface ValidatorMessageLoader {

	/**
	 * 按Properties格式返回配置的验证信息：<br><br>
	 * 
	 * 	例如：key=user.username.null，value=用户名不能为空<br><br>
	 * 
	 * 	不用再封装为JSON串，验证失败信息会封装为json串返回{@link ValidatedEchoBean}
	 * 
	 * @return
	 */
	Properties loadMessages();
	
}
