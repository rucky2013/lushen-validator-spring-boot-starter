package org.lushen.zhuifeng.springboot.validator.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * 
 * @author hlm
 */

public class IntegerInArrayValidator implements ConstraintValidator<IntegerInArray, Integer> {

	private int[] values;
	
	@Override
	public void initialize(IntegerInArray constraintAnnotation) {
		this.values = constraintAnnotation.values();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		
		if (value == null ) {
			return false;
		}
		
		for(int val : values) {
			if(val == value) {
				return true;
			}
		}
		
		return false;
	}

}
