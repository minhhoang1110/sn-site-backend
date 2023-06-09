package com.snsite.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userPost;
	@Column
	@NotNull(message = "Sharedtype must not be empty")
	private Integer sharedType;
	@Column
	private String content;
	@Column
	private String imageUrls;
	@OneToMany(mappedBy = "likeOfPost")
	private List<LikeEntity> likes = new ArrayList<>();
	@OneToMany(mappedBy = "postComment")
	private List<CommentEntity> comments = new ArrayList<>();

	public UserEntity getUserPost() {
		return userPost;
	}

	public void setUserPost(UserEntity userPost) {
		this.userPost = userPost;
	}

	public int getSharedType() {
		return sharedType;
	}

	public void setSharedType(int sharedType) {
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

	public List<LikeEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeEntity> likes) {
		this.likes = likes;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}
