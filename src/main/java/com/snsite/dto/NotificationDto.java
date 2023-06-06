package com.snsite.dto;

import java.util.HashMap;

public class NotificationDto extends BaseDto<NotificationDto> {

	private Long userId;
	private UserDto user;
	private Long fromUserId;
	private UserDto fromUser;
	private boolean isRead;

	private Integer type;

	private Long objectId;

	private String action;

	public static Integer TypeLike = 0;
	public static Integer TypeComment = 1;
	public static Integer TypeRequestFriendShip = 2;
	public static Integer TypeAcceptFriendShip = 3;
	public static HashMap<String, Integer> TypeToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("TypeLike", 0);
			put("TypeComment", 1);
			put("TypeRequestFriendShip", 2);
			put("TypeAcceptFriendShip", 3);
		}
	};

	public static HashMap<Integer, String> TypeToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "TypeLike");
			put(1, "TypeComment");
			put(2, "TypeRequestFriendShip");
			put(3, "TypeAcceptFriendShip");
		}
	};

	public static HashMap<String, String> TypeToAction = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("TypeLike", "đã thích bài viết của bạn");
			put("TypeComment", "đã bình luận vào bài viết của bạn");
			put("TypeRequestFriendShip", "đã gửi cho bạn một lời mời kết bạn");
			put("TypeAcceptFriendShip", "đã đồng ý kết bạn với bạn");
		}
	};

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public UserDto getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserDto fromUser) {
		this.fromUser = fromUser;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
