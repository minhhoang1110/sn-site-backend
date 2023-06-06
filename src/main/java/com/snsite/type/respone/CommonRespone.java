package com.snsite.type.respone;

public class CommonRespone<T> {
	private int httpCode;
	private boolean success;
	private String message;
	private T data;

	public CommonRespone() {
		super();
	}

	public CommonRespone(int httpCode, boolean success, String message, T data) {
		super();
		this.httpCode = httpCode;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
