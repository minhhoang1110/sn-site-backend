package com.snsite.dto;

import java.util.HashMap;

public class RoomChatDto extends BaseDto<RoomChatDto> {
	private String userIds;
	private String roomType;

	private String thumbnailUrl;

	private boolean hasUnreadMessage;

	public static Integer RoomTypeDefault = 0;
	public static Integer RoomTypeGroup = 1;
	public static HashMap<String, Integer> RoomTypeToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("RoomTypeDefault", 0);
			put("RoomTypeGroup", 1);
		}
	};

	public static HashMap<Integer, String> RoomTypeToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "RoomTypeDefault");
			put(1, "RoomTypeGroup");
		}
	};

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public boolean isHasUnreadMessage() {
		return hasUnreadMessage;
	}

	public void setHasUnreadMessage(boolean hasUnreadMessage) {
		this.hasUnreadMessage = hasUnreadMessage;
	}

}
