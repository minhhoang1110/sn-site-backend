package com.snsite.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class LikeEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userLike;
	@ManyToOne
	@JoinColumn(name = "post_id")
	private PostEntity likeOfPost;

	public UserEntity getUserLike() {
		return userLike;
	}

	public void setUserLike(UserEntity userLike) {
		this.userLike = userLike;
	}

	public PostEntity getLikeOfPost() {
		return likeOfPost;
	}

	public void setLikeOfPost(PostEntity likeOfPost) {
		this.likeOfPost = likeOfPost;
	}

}
