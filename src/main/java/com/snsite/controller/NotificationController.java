package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.NotificationDto;
import com.snsite.service.INotificationService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class NotificationController {
	@Autowired
	private INotificationService notificationService;

	@GetMapping(value = "/api/notification")
	@ResponseBody
	public CommonRespone<List<NotificationDto>> getListNotification() {
		List<NotificationDto> notificationDtos = notificationService.getListNotification();
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Notification Successfully";
		boolean success = true;
		if (notificationDtos == null) {
			code = HttpServletResponse.SC_OK;
			message = "Fetch Notification Error";
			success = false;
		}
		return new CommonRespone<List<NotificationDto>>(code, success, message, notificationDtos);
	}

	@GetMapping(value = "/api/notification/{id}")
	@ResponseBody
	public CommonRespone<NotificationDto> readNotification(@PathVariable("id") Long id) {
		NotificationDto notificationDto = notificationService.readNotification(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Read Notification Successfully";
		boolean success = true;
		if (notificationDto == null) {
			code = HttpServletResponse.SC_OK;
			message = "Read Notification Error";
			success = false;
		}
		return new CommonRespone<NotificationDto>(code, success, message, notificationDto);
	}

	@GetMapping(value = "/api/notification/read_all")
	@ResponseBody
	public CommonRespone<List<NotificationDto>> readAllListNotification() {
		List<NotificationDto> notificationDtos = notificationService.readAllListNotification();
		int code = HttpServletResponse.SC_OK;
		String message = "Read Notification Successfully";
		boolean success = true;
		if (notificationDtos == null) {
			code = HttpServletResponse.SC_OK;
			message = "Read Notification Error";
			success = false;
		}
		return new CommonRespone<List<NotificationDto>>(code, success, message, notificationDtos);
	}
}
