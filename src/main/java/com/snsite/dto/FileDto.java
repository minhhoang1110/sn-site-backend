package com.snsite.dto;

import java.util.HashMap;

public class FileDto extends BaseDto<FileDto> {
	private Long userId;

	private String fileType;

	private String objectType;

	private String url;

	public static Integer FileTypeImage = 0;
	public static HashMap<String, Integer> FileTypeToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("FileTypeImage", 0);
		}
	};
	public static HashMap<Integer, String> FileTypeToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "FileTypeImage");
		}
	};

	public static Integer ObjectTypeUserAvatar = 0;
	public static Integer ObjectTypeUserCover = 1;
	public static Integer ObjectTypeRoomChat = 2;
	public static Integer ObjectTypeMessage = 3;
	public static Integer ObjectTypePost = 4;
	public static HashMap<String, Integer> ObjectTypeToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("ObjectTypeUserAvatar", 0);
			put("ObjectTypeUserCover", 1);
			put("ObjectTypeRoomChat", 2);
			put("ObjectTypeMessage", 3);
			put("ObjectTypePost", 4);
		}
	};
	public static HashMap<Integer, String> ObjectTypeToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "ObjectTypeUserAvatar");
			put(1, "ObjectTypeUserCover");
			put(2, "ObjectTypeRoomChat");
			put(3, "ObjectTypeMessage");
			put(4, "ObjectTypePost");
		}
	};

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
