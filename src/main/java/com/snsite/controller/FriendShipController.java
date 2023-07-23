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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.FriendShipDto;
import com.snsite.logger.ILoggerService;
import com.snsite.service.IFriendShipService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class FriendShipController {
	@Autowired
	private IFriendShipService friendShipService;
	@Autowired
	private ILoggerService loggerService;

	@GetMapping(value = "/api/friendship/{id}")
	@ResponseBody
	public CommonRespone<FriendShipDto> getFriendShipDetail(@PathVariable("id") Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		FriendShipDto friendShipDto = friendShipService.getFriendShipDetail(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully FriendShips";
		boolean success = true;
		if (friendShipDto == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Fetch FriendShips Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<FriendShipDto>(code, success, message, friendShipDto);
	}

	@GetMapping(value = "/api/friendship")
	@ResponseBody
	public CommonRespone<List<FriendShipDto>> getListFriendShip(
			@RequestParam(value = "userId", required = false) Long userId, HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		List<FriendShipDto> friendShipDtos = friendShipService.getListFriendShip(userId);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully FriendShips";
		boolean success = true;
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<List<FriendShipDto>>(code, success, message, friendShipDtos);
	}

	@GetMapping(value = "/api/friendship/requested")
	@ResponseBody
	public CommonRespone<List<FriendShipDto>> getListRequestedFriendShip(HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		List<FriendShipDto> friendShipDtos = friendShipService.getListRequestedFriendShip();
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully FriendShips";
		boolean success = true;
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<List<FriendShipDto>>(code, success, message, friendShipDtos);
	}

	@PostMapping(value = "/api/friendship")
	@ResponseBody
	public CommonRespone<FriendShipDto> creatFriendShip(@RequestBody FriendShipDto friendShipDto,
			HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		FriendShipDto data = friendShipService.saveFriendShip(friendShipDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Create Successfully FriendShip";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Create FriendShip Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<FriendShipDto>(code, success, message, data);
	}

	@PutMapping(value = "/api/friendship/{id}")
	@ResponseBody
	public CommonRespone<FriendShipDto> updateFriendShip(@PathVariable("id") Long id,
			@RequestBody FriendShipDto friendShipDto, HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		friendShipDto.setId(id);
		FriendShipDto data = friendShipService.saveFriendShip(friendShipDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Successfully FriendShip";
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Update FriendShip Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<FriendShipDto>(code, success, message, data);
	}

	@DeleteMapping(value = "/api/friendship/{id}")
	@ResponseBody
	public CommonRespone<FriendShipDto> deleteFriendShip(@PathVariable("id") Long id, HttpServletRequest request) {
		loggerService.infoCallEndpoint(FriendShipController.class, request);
		boolean result = friendShipService.deleteFriendShip(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Delete Successfully FriendShip";
		boolean success = true;
		if (!result) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Update FriendShip Error";
			success = false;
		}
		loggerService.infoCompleteEndpoint(FriendShipController.class, request);
		return new CommonRespone<FriendShipDto>(code, success, message, null);
	}
}
