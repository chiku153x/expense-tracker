package com.iit.asdcw2.expensetracker.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("session")
@Entity
@Table(name = "session", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	public Session() {
		super();
	}

	@ApiModelProperty(value = "Session id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "User")
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

	@ApiModelProperty(value = "Token")
	@Column(name = "token")
	private String token;

	@ApiModelProperty(value = "Date")
	@Column(name = "date")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Session(Long id, User user, String token, Date date) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.date = date;
	}
	
	public Session(User user, String token, Date date) {
		super();
		this.user = user;
		this.token = token;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", user=" + user + ", token=" + token + ", date=" + date + "]";
	}

}
