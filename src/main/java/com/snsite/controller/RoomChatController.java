package com.snsite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.RoomChatDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class RoomChatController {
	@GetMapping(value = "/api/roomchat")
	@ResponseBody
	public CommonRespone<List<RoomChatDto>> getListRoomChat() {
		return null;
	}

	@GetMapping(value = "/api/roomchat/{id}")
	@ResponseBody
	public CommonRespone<RoomChatDto> getRoomChatDetail(@PathVariable("id") Long id) {
		return null;
	}

	@PostMapping(value = "/api/roomchat")
	@ResponseBody
	public CommonRespone<RoomChatDto> creatRoomChat(@RequestBody RoomChatDto roomChatDto) {
		return null;
	}

	@PutMapping(value = "/api/roomchat/{id}")
	@ResponseBody
	public CommonRespone<RoomChatDto> updateRoomChat(@PathVariable("id") Long id,
			@RequestBody RoomChatDto roomChatDto) {
		return null;
	}
}
