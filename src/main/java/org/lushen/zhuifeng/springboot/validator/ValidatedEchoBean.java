package org.lushen.zhuifeng.springboot.validator;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 错误信息输出对象
 * 
 * @author hlm
 */
public class ValidatedEchoBean {
	
	private int errcode;
	
	private String msg;
	
	private List<ValidatedError> details;

	public ValidatedEchoBean(int buffer) {
		super();
		this.details = new ArrayList<>(buffer);
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void addValidatedError(ValidatedError error) {
		this.details.add(error);
	}
	
	public String toJsonString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errcode", this.errcode);
		jsonObject.put("msg", this.msg);
		jsonObject.put("details", this.details);
		return jsonObject.toString();
	}
	
}
