package com.snsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userNotification;
	@Column
	@NotNull(message = "FromUserId must not be empty")
	private Long fromUserId;
	@Column(columnDefinition = "boolean default false")
	private boolean isRead = false;
	@Column
	@NotNull(message = "Type must not be empty")
	private Integer type;
	@Column
	@NotNull(message = "ObjectId must not be empty")
	private Long objectId;
	@Column
	@NotBlank(message = "Action must not be empty")
	private String action;

	public UserEntity getUserNotification() {
		return userNotification;
	}

	public void setUserNotification(UserEntity userNotification) {
		this.userNotification = userNotification;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
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
