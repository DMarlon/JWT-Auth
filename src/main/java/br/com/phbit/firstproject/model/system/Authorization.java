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
@Table(name = "sysauthorizations")
public class Authorization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aut_id", nullable = false)
	private long id;
    
	@Column(name = "aut_description", nullable = false)
    private String description;
    
	@Column(name = "aut_status", nullable = false)
	@Convert(converter = StatusConvert.class)
    private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}	
}
