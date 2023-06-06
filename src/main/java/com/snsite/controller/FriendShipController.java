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

import com.snsite.dto.FriendShipDto;
import com.snsite.type.respone.CommonRespone;

@RestController
public class FriendShipController {
	@GetMapping(value = "/api/friendship")
	@ResponseBody
	public CommonRespone<List<FriendShipDto>> getListFriendShip(@RequestParam("userId") Long userId) {
		return null;
	}

	@PostMapping(value = "/api/friendship")
	@ResponseBody
	public CommonRespone<FriendShipDto> creatFriendShip(@RequestBody FriendShipDto friendShipDto) {
		return null;
	}

	@PutMapping(value = "/api/friendship/{id}")
	@ResponseBody
	public CommonRespone<FriendShipDto> updateFriendShip(@PathVariable("id") Long id,
			@RequestBody FriendShipDto friendShipDto) {
		return null;
	}
}
