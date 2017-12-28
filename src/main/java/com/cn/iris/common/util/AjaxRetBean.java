package com.cn.iris.common.util;

/**
 * Author: IrisNew
 * Description:用户ajax请求返回信息的包装
 * Date: 2017/12/28 10:59
 */
public class AjaxRetBean<T> {
	private Boolean success;
	private String status;
	private String message;
	private T data;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
