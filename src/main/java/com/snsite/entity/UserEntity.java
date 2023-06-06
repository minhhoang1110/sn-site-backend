package com.snsite.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
	@Column
	@NotBlank(message = "Username must not be empty")
	private String username;
	@Column
	@NotBlank(message = "Password must not be empty")
	private String password;
	@Column
	@NotBlank(message = "Email must not be empty")
	@Email(message = "Invalid email")
	private String email;
	@Column
	@NotBlank(message = "Username must not be empty")
	@Pattern(regexp = "[0-9]{10}", message = "Invalid phone")
	private String phone;
	@Column
	private String address;
	@Column
	private String bio;
	@Column
	@NotBlank(message = "Firstname must not be empty")
	private String firstName;
	@Column
	@NotBlank(message = "Lastname must not be empty")
	private String lastName;
	@Column
	private Date dateOfBirth;
	@Column
	private String avatarUrl;
	@Column
	private String coverUrl;
	@Column
	private Date sendVerifyEmailAt;
	@Column
	private Date verifyEmailAt;
	@OneToMany(mappedBy = "userNotification")
	private List<NotificationEntity> notifications = new ArrayList<>();
	@OneToMany(mappedBy = "userFriendShip")
	private List<FriendShipEntity> friendShips = new ArrayList<>();
	@OneToMany(mappedBy = "userPost")
	private List<PostEntity> posts = new ArrayList<>();
	@OneToMany(mappedBy = "userLike")
	private List<LikeEntity> likes = new ArrayList<>();
	@OneToMany(mappedBy = "userComment")
	private List<CommentEntity> comments = new ArrayList<>();
	@OneToMany(mappedBy = "userMessage")
	private List<MessageEntity> messages = new ArrayList<>();

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

	public List<NotificationEntity> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationEntity> notifications) {
		this.notifications = notifications;
	}

	public List<FriendShipEntity> getFriendShips() {
		return friendShips;
	}

	public void setFriendShips(List<FriendShipEntity> friendShips) {
		this.friendShips = friendShips;
	}

	public List<PostEntity> getPosts() {
		return posts;
	}

	public void setPosts(List<PostEntity> posts) {
		this.posts = posts;
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

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}

}
