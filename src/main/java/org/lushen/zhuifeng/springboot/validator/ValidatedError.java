package org.lushen.zhuifeng.springboot.validator;


/**
 * 错误详细信息
 * 
 * @author hlm
 */
public class ValidatedError {
	
	private String field;
	
	private String msg;

	public ValidatedError(String field, String msg) {
		super();
		this.field = field;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
