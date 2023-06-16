package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.MessageDto;
import com.snsite.service.IMessageService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class MessageController {
	@Autowired
	private IMessageService messageService;

	@GetMapping(value = "/api/message")
	@ResponseBody
	public CommonRespone<List<MessageDto>> getListMessage(@RequestParam("roomId") Long roomId) {
		List<MessageDto> data = messageService.getListMessage(roomId);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Message Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Fetch Message Error";
			success = false;
		}
		return new CommonRespone<List<MessageDto>>(code, success, message, data);
	}

	@PostMapping(value = "/api/message")
	@ResponseBody
	public CommonRespone<MessageDto> creatMessage(@RequestBody MessageDto messageDto) {
		MessageDto data = messageService.saveMessage(messageDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Message Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Create Message Error";
			success = false;
		}
		return new CommonRespone<MessageDto>(code, success, message, data);
	}

	@PutMapping(value = "/api/message/{id}")
	@ResponseBody
	public CommonRespone<MessageDto> updateMessage(@PathVariable("id") Long id, @RequestBody MessageDto messageDto) {
		messageDto.setId(id);
		MessageDto data = messageService.saveMessage(messageDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Message Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Update Message Error";
			success = false;
		}
		return new CommonRespone<MessageDto>(code, success, message, data);
	}

	@DeleteMapping(value = "/api/message/{id}")
	@ResponseBody
	public CommonRespone<MessageDto> deleteMessage(@PathVariable("id") Long id) {
		boolean data = messageService.deleteMessage(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Delete Message Successfully";
		boolean success = true;
		if (!data) {
			code = HttpServletResponse.SC_OK;
			message = "Delete Message Error";
			success = false;
		}
		return new CommonRespone<MessageDto>(code, success, message, null);
	}
}
