package br.com.phbit.firstproject.model.system;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.phbit.firstproject.enumerators.Status;
import br.com.phbit.firstproject.model.StatusConvert;

@Entity
@Table(name = "sysusers")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "use_id")
	private long id;
	
	@Column(name = "use_name")
	private String name;
	
	@Column(name = "use_password")
	private String password;
	
	@Column(name = "use_status")
	@Convert(converter = StatusConvert.class)
	private Status status;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}	
}
