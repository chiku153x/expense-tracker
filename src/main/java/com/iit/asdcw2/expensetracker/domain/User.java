package com.iit.asdcw2.expensetracker.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("user")
@Entity
@Table(name = "user", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "User id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "Name")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(value = "First Name")
	@Column(name = "firstName")
	private String firstName;

	@ApiModelProperty(value = "Last Name")
	@Column(name = "lastName")
	private String lastName;

	@ApiModelProperty(value = "Phone No")
	@Column(name = "phoneNo")
	private String phoneNo;

	@ApiModelProperty(value = "User Name")
	@Column(name = "username")
	private String username;

	@ApiModelProperty(value = "password")
	@Column(name = "password")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

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

	public User(Long id, String name, String firstName, String lastName, String phoneNo, String username,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.username = username;
		this.password = password;
	}
	
	public User(String name, String firstName, String lastName, String phoneNo, String username,
			String password) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
	}

	
}
