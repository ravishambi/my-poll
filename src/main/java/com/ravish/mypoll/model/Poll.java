package com.ravish.mypoll.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ravish.mypoll.audit.UserDateAudit;

@Entity
@Table(name = "polls")
public class Poll extends UserDateAudit{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 40)
	private String question;
	
	@NotNull
	@Size(min = 2, max = 6)
	@OneToMany(fetch=FetchType.LAZY, cascade =CascadeType.ALL, mappedBy="poll")
	private List<Choice> choices = new ArrayList<>();
	
	@NotNull
	private Instant expirationDateTime;
	
	
	public Poll(){
		
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Instant getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(Instant expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	
	
	

}
