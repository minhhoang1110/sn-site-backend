package com.snsite.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room_chats")
public class RoomChatEntity extends BaseEntity {
	@Column
	@NotBlank(message = "UserId must not be empty")
	private String userIds;
	@Column
	@NotNull(message = "Roomtype must not be empty")
	private Integer roomType;
	@Column
	private String thumbnailUrl;
	@OneToMany(mappedBy = "roomMessage")
	private List<MessageEntity> messages = new ArrayList<>();

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}

}
