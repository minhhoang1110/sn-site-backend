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
	private PostEntity postLike;

	public UserEntity getUserLike() {
		return userLike;
	}

	public void setUserLike(UserEntity userLike) {
		this.userLike = userLike;
	}

	public PostEntity getPostLike() {
		return postLike;
	}

	public void setPostLike(PostEntity postLike) {
		this.postLike = postLike;
	}

}
