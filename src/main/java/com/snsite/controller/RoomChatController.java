package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.RoomChatDto;
import com.snsite.logger.ILoggerService;
import com.snsite.service.IRoomChatService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class RoomChatController {
	@Autowired
	private IRoomChatService roomChatService;
	@Autowired
	private ILoggerService loggerService;

	@GetMapping(value = "/api/roomchat")
	@ResponseBody
	public CommonRespone<List<RoomChatDto>> getListRoomChat(HttpServletRequest request) {
		loggerService.infoCallEndpoint(RoomChatController.class, request);
		List<RoomChatDto> data = roomChatService.getListRoomChat();
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Roomchat Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Fetch Roomchat Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(RoomChatController.class, request);
		return new CommonRespone<List<RoomChatDto>>(code, success, message, data);
	}

	@GetMapping(value = "/api/roomchat/{id}")
	@ResponseBody
	public CommonRespone<RoomChatDto> getRoomChatDetail(@PathVariable("id") Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(RoomChatController.class, request);
		RoomChatDto data = roomChatService.getRoomChatDetail(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Roomchat Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Fetch Roomchat Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(RoomChatController.class, request);
		return new CommonRespone<RoomChatDto>(code, success, message, data);
	}

	@PostMapping(value = "/api/roomchat")
	@ResponseBody
	public CommonRespone<RoomChatDto> creatRoomChat(@RequestBody RoomChatDto roomChatDto, HttpServletRequest request) {
		loggerService.infoCallEndpoint(RoomChatController.class, request);
		RoomChatDto data = roomChatService.saveRoomChat(roomChatDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Roomchat Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Create Roomchat Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(RoomChatController.class, request);
		return new CommonRespone<RoomChatDto>(code, success, message, data);
	}

	@PutMapping(value = "/api/roomchat/{id}")
	@ResponseBody
	public CommonRespone<RoomChatDto> updateRoomChat(@PathVariable("id") Long id, @RequestBody RoomChatDto roomChatDto,
			HttpServletRequest request) {
		loggerService.infoCallEndpoint(RoomChatController.class, request);
		roomChatDto.setId(id);
		RoomChatDto data = roomChatService.saveRoomChat(roomChatDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Roomchat Successfully";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_OK;
			message = "Update Roomchat Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(RoomChatController.class, request);
		return new CommonRespone<RoomChatDto>(code, success, message, data);
	}

	@DeleteMapping(value = "/api/roomchat/{id}")
	@ResponseBody
	public CommonRespone<RoomChatDto> deleteRoomChat(@PathVariable("id") Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(RoomChatController.class, request);
		boolean data = roomChatService.deleteRoomChat(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Delete Roomchat Successfully";
		boolean success = true;
		if (!data) {
			code = HttpServletResponse.SC_OK;
			message = "Delete Roomchat Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(RoomChatController.class, request);
		return new CommonRespone<RoomChatDto>(code, success, message, null);
	}
}
