package com.appsdeveloperblog.photoapp.api.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")//database table
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -2731425678149216053L;

	@Id//to specify that this field is going to be a database id
	@GeneratedValue //this field will also be an auto generated value,It will start with one.
	private long id;

	@Column(nullable=false, length=50)
	private String firstName;

	@Column(nullable=false, length=50)
	private String lastName;

	@Column(nullable=false, length=250, unique=true)
	private String email;

	// is a public user ID, which we are going to use in our HTTP requests and it's going to be an alphanumeric value.
	@Column(nullable=false, unique=true)
	private String userId;

	@Column(nullable=false, unique=true)
	private String encryptedPassword;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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



}
