package com.ravish.mypoll.audit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(
		value= {"createdBy","updatedBy"},
		allowGetters = true)
public abstract class UserDateAudit extends DateAudit{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 234354L;

	@CreatedBy
	@Column(nullable = false, updatable = false)
	private Long createdBy;
	
	@LastModifiedBy
	@Column(nullable = false)
	private Long updatedBy;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	

}
