package com.snsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.NotificationDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class NotificationController {
	@GetMapping(value = "/api/notification")
	@ResponseBody
	public CommonRespone<List<NotificationDto>> getListNotification() {
		return null;
	}

	@GetMapping(value = "/api/notification/{id}")
	@ResponseBody
	public CommonRespone<NotificationDto> readNotification(@PathVariable("id") Long id) {
		return null;
	}

	@GetMapping(value = "/api/notification/read_all")
	@ResponseBody
	public CommonRespone<List<NotificationDto>> readAllListNotification() {
		return null;
	}
}
