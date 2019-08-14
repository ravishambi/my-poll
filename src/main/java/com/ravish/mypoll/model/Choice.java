package com.ravish.mypoll.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="choices")
public class Choice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=2, max=10)
	private String choice;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name = "poll_id", foreignKey = @ForeignKey(name = "fk_poll_id"))
	private Poll poll;
	
	public Choice() {
		
	}

	public Long getId() {
		return id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}
	
	
	
	

}
