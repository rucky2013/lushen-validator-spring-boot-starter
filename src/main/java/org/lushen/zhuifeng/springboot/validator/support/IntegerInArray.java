package org.lushen.zhuifeng.springboot.validator.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

/**
 * 验证int 数字是否在数组中
 * 
 * @author hlm
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface IntegerInArray {

	/**
	 * 值数组
	 */
	int[] values() default {};

	String message() default "";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
