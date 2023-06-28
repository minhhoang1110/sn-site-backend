package com.snsite.logger;

import javax.servlet.http.HttpServletRequest;

public interface ILoggerService {
	public void infoCallEndpoint(Class<?> clazz, HttpServletRequest request);

	public void infoCompleteEndpoint(Class<?> clazz, HttpServletRequest request);
}
