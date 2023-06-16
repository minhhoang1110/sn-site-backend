package com.snsite.dto;

public class MessageDto extends BaseDto<MessageDto> {
	private Long roomId;
	private RoomChatDto room;
	private Long userId;
	private UserDto user;
	private boolean isRead;
	private String message;
	private String imageUrl;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public RoomChatDto getRoom() {
		return room;
	}

	public void setRoom(RoomChatDto room) {
		this.room = room;
	}

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

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
