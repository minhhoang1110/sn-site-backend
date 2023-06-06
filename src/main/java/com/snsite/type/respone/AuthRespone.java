package com.snsite.type.respone;

public class AuthRespone {
	private int httpCode;
	private boolean success;
	private String message;
	private UserWithToken data;

	public AuthRespone() {
		super();
	}

	public AuthRespone(int httpCode, boolean success, String message, UserWithToken data) {
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserWithToken getData() {
		return data;
	}

	public void setData(UserWithToken data) {
		this.data = data;
	}

}
