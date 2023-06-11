package com.snsite.dto;

import java.util.HashMap;
import java.util.List;

public class PostDto extends BaseDto<PostDto> {
	private Long userId;
	private UserDto user;
	private String sharedType;
	private String content;
	private String imageUrls;
	private int countOfLikes;
	private int countOfComments;
	private List<LikeDto> likes;
	private List<CommentDto> comments;

	public static Integer SharedTypePublic = 0;
	public static Integer SharedTypeFriend = 1;
	public static Integer SharedTypePrivate = 2;
	public static HashMap<String, Integer> SharedTypeToId = new HashMap<String, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("SharedTypePublic", 0);
			put("SharedTypeFriend", 1);
			put("SharedTypePrivate", 2);
		}
	};

	public static HashMap<Integer, String> SharedTypeToString = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(0, "SharedTypePublic");
			put(1, "SharedTypeFriend");
			put(2, "SharedTypePrivate");
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

	public String getSharedType() {
		return sharedType;
	}

	public void setSharedType(String sharedType) {
		this.sharedType = sharedType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getCountOfLikes() {
		return countOfLikes;
	}

	public void setCountOfLikes(int countOfLikes) {
		this.countOfLikes = countOfLikes;
	}

	public int getCountOfComments() {
		return countOfComments;
	}

	public void setCountOfComments(int countOfComments) {
		this.countOfComments = countOfComments;
	}

	public List<LikeDto> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDto> likes) {
		this.likes = likes;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

}
