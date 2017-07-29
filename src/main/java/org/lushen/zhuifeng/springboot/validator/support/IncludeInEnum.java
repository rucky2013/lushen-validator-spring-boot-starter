package org.lushen.zhuifeng.springboot.validator.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

/**
 * 验证字符串是否包含在枚举
 * 
 * @author hlm
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface IncludeInEnum {

	/**
	 * 枚举类
	 * @return
	 */
	Class<? extends Enum<?>> enumClass();

	String message() default "";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
