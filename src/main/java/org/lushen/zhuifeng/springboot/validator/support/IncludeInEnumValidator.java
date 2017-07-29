package org.lushen.zhuifeng.springboot.validator.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * 
 * @author hlm
 */
public class IncludeInEnumValidator implements ConstraintValidator<IncludeInEnum,String> {

	private Enum<?>[] enums;
	
	@Override
	public void initialize(IncludeInEnum constraintAnnotation) {
		this.enums = constraintAnnotation.enumClass().getEnumConstants();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return false;
		}
		
		for(Enum<?> e : enums) {
			if(e.name().equals(value)) {
				return true;
			}
		}
		
		return false;
	}

}
