package com.snsite.dto;

import java.util.HashMap;

public class FriendShipDto extends BaseDto<FriendShipDto> {
	private Long firstUserId;
	private UserDto firstUser;
	private Long secondUserId;
	private UserDto secondUser;
	private String state;

	public static Integer StateRequested = 0;
	public static Integer StateFriend = 1;
	public static HashMap<String, Integer> StateToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("StateRequested", 0);
			put("StateFriend", 1);
		}
	};

	public static HashMap<Integer, String> StateToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "StateRequested");
			put(1, "StateFriend");
		}
	};

	public Long getFirstUserId() {
		return firstUserId;
	}

	public void setFirstUserId(Long firstUserId) {
		this.firstUserId = firstUserId;
	}

	public UserDto getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(UserDto firstUser) {
		this.firstUser = firstUser;
	}

	public Long getSecondUserId() {
		return secondUserId;
	}

	public void setSecondUserId(Long secondUserId) {
		this.secondUserId = secondUserId;
	}

	public UserDto getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(UserDto secondUser) {
		this.secondUser = secondUser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
