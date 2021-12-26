package com.appsdeveloperblog.photoapp.api.users.shared;

import java.io.Serializable;
import java.util.List;

import com.appsdeveloperblog.photoapp.api.users.ui.model.AlbumResponseModel;

public class UserDto implements Serializable {
	/**
	 * Before we attempt to store user details in the database, there is one value that I want to set on user
	 * details object and this value is user ID. Every user details that we store in the database should have
	 * a unique user ID And once user details are stored in the database, that record will have two user IDs
	 * One user ID is a database generated value, which is user ID one and then user ID two
	 * will be auto incremented with each new record and we are not going to use that database user ID
	 * it much because it is not very secure to use database user ID in HTTP requests.
	 * Let's say we've implemented a delete user HTTP request and if we send HTTP request to delete user with ID 1,
	 * then most likely there is a user with ID 2 and user with ID 3, malicious user will notice this and might be
	 * tempted to break into our system to delete user ID 1,user ID 2, user ID 3 and so on.
	 *
	 * So we're not going to use database user ID in our HTTP request.
	 * We're going to use a public user ID, which is alphanumeric string of characters randomly generated
	 * and is unique for every single user.
	 *
	 * And it is this user ID that we are going to use in HTTP requests when we send the request to update
	 * user details or delete user details with an ID, because that public alphanumeric randomly generated
	 * string of characters doesn't tell malicious user what would be the ID of another user.
	 *
	 * So there are different ways how to generate random string of characters which we can use as user ID
	 *
	 * and I will use most likely the easiest one.
	 */
	private static final long serialVersionUID = -953297098295050686L;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
	private List<AlbumResponseModel> albums;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<AlbumResponseModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumResponseModel> albums) {
		this.albums = albums;
	}

}
