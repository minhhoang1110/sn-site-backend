package com.snsite.logger.implement;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.snsite.logger.ILoggerService;

@Service
public class LoggerService implements ILoggerService {
	private Logger logger;

	@Override
	public void infoCallEndpoint(Class<?> clazz, HttpServletRequest request) {
		logger = LoggerFactory.getLogger(clazz);
		logger.info("Call API: " + request.getMethod() + " " + request.getRequestURI() + ", IP: "
				+ request.getRemoteAddr() + " (" + request.getLocale().getCountry() + ")");
	}

	@Override
	public void infoCompleteEndpoint(Class<?> clazz, HttpServletRequest request) {
		logger = LoggerFactory.getLogger(clazz);
		logger.info("Complete API: " + request.getMethod() + " " + request.getRequestURI() + ", IP: "
				+ request.getRemoteAddr() + " (" + request.getLocale().getCountry() + ")");
	}

}
