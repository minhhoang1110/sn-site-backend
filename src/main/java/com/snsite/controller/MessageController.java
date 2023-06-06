package com.snsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.MessageDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class MessageController {
	@GetMapping(value = "/api/message")
	@ResponseBody
	public CommonRespone<List<MessageDto>> getListMessage(@RequestParam("roomId") Long roomId) {
		return null;
	}

	@PostMapping(value = "/api/message")
	@ResponseBody
	public CommonRespone<MessageDto> creatMessage(@RequestBody MessageDto messageDto) {
		return null;
	}

	@PutMapping(value = "/api/message/{id}")
	@ResponseBody
	public CommonRespone<MessageDto> updateMessage(@PathVariable("id") Long id, @RequestBody MessageDto messageDto) {
		return null;
	}
}
