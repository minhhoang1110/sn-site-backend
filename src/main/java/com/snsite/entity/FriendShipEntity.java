package com.snsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "friend_ships")
public class FriendShipEntity extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "first_user_id")
	private UserEntity userFriendShip;
	@Column
	@NotNull(message = "SecondUserId must not be empty")
	private Long secondUserId;
	@Column
	@NotNull(message = "State must not be empty")
	private Integer state;

	public UserEntity getUserFriendShip() {
		return userFriendShip;
	}

	public void setUserFriendShip(UserEntity userFriendShip) {
		this.userFriendShip = userFriendShip;
	}

	public Long getSecondUserId() {
		return secondUserId;
	}

	public void setSecondUserId(Long secondUserId) {
		this.secondUserId = secondUserId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
