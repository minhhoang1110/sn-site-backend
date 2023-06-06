package com.snsite.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snsite.dto.UserDto;
import com.snsite.service.IUserService;
import com.snsite.type.respone.CommonRespone;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping(value = "/api/current_profile")
	@ResponseBody
	public CommonRespone<UserDto> getCurrentProfile() {
		UserDto userDto = userService.getCurrentProfile();
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully User " + userDto.getId();
		boolean success = true;
		return new CommonRespone<UserDto>(code, success, message, userDto);
	}

	@GetMapping(value = "/api/user")
	@ResponseBody
	public CommonRespone<List<UserDto>> getListUser() {
		List<UserDto> userDtos = userService.getListUser();
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully Users";
		boolean success = true;
		return new CommonRespone<List<UserDto>>(code, success, message, userDtos);
	}

	@GetMapping(value = "/api/user/{id}")
	@ResponseBody
	public CommonRespone<UserDto> getUserDetail(@PathVariable("id") Long id) {
		UserDto userDto = userService.getUserDetail(id);
		int code = HttpServletResponse.SC_OK;
		String message = "Fetch Successfully User " + id;
		boolean success = true;
		if (userDto == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Can not find User " + id;
			success = false;
		}
		return new CommonRespone<UserDto>(code, success, message, userDto);
	}

	@PutMapping(value = "/api/user/{id}")
	@ResponseBody
	public CommonRespone<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
		userDto.setId(id);
		UserDto data = userService.saveUser(userDto);
		int code = HttpServletResponse.SC_OK;
		String message = "Update Successfully User " + id;
		boolean success = true;
		if (data == null) {
			code = HttpServletResponse.SC_BAD_REQUEST;
			message = "Update Error User " + id;
			success = false;
		}
		return new CommonRespone<UserDto>(code, success, message, data);
	}
}
