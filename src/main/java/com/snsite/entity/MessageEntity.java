package com.snsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "room_id")
	private RoomChatEntity roomMessage;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userMessage;
	@Column(columnDefinition = "boolean default false")
	private boolean isRead = false;
	@Column
	private String message;
	@Column
	private String imageUrl;

	public RoomChatEntity getRoomMessage() {
		return roomMessage;
	}

	public void setRoomMessage(RoomChatEntity roomMessage) {
		this.roomMessage = roomMessage;
	}

	public UserEntity getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(UserEntity userMessage) {
		this.userMessage = userMessage;
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
