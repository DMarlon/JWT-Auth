package br.com.phbit.firstproject.model.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "syssessions")
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ses_id", nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ses_user_id", nullable = false)
	private User user;
	
	@Column(name = "ses_token", nullable = false)
	private String token;
	
	@Column(name = "ses_devicename")
	private String devicename;
	
	@Column(name = "ses_remoteaddress")
	private String remoteaddress;
	
	@Column(name = "ses_lastaccess", nullable = false)
	private Date lastaccess;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	public String getDevicename() {
		return devicename;
	}
	
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	
	public String getRemoteaddress() {
		return remoteaddress;
	}
	
	public void setRemoteaddress(String remoteaddress) {
		this.remoteaddress = remoteaddress;
	}
	
	public Date getLastaccess() {
		return lastaccess;
	}
	
	public void setLastaccess(Date lastaccess) {
		this.lastaccess = lastaccess;
	}
}
