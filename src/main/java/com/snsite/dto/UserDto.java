package com.snsite.dto;

import java.util.Date;

public class UserDto extends BaseDto<UserDto> {
	private String username;

	private String password;

	private String email;

	private String phone;

	private String address;

	private String bio;

	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private String avatarUrl;

	private String coverUrl;

	private Date sendVerifyEmailAt;

	private Date verifyEmailAt;

	private int countOfFriends;

	private boolean isChatWithSessionUser = false;

	private Long roomChatId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Date getSendVerifyEmailAt() {
		return sendVerifyEmailAt;
	}

	public void setSendVerifyEmailAt(Date sendVerifyEmailAt) {
		this.sendVerifyEmailAt = sendVerifyEmailAt;
	}

	public Date getVerifyEmailAt() {
		return verifyEmailAt;
	}

	public void setVerifyEmailAt(Date verifyEmailAt) {
		this.verifyEmailAt = verifyEmailAt;
	}

	public int getCountOfFriends() {
		return countOfFriends;
	}

	public void setCountOfFriends(int countOfFriends) {
		this.countOfFriends = countOfFriends;
	}

	public boolean isChatWithSessionUser() {
		return isChatWithSessionUser;
	}

	public void setChatWithSessionUser(boolean isChatWithSessionUser) {
		this.isChatWithSessionUser = isChatWithSessionUser;
	}

	public Long getRoomChatId() {
		return roomChatId;
	}

	public void setRoomChatId(Long roomChatId) {
		this.roomChatId = roomChatId;
	}

}
