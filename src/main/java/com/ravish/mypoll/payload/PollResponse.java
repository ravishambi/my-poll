package com.ravish.mypoll.payload;

import java.time.Instant;
import java.util.List;

public class PollResponse {
	
	private Long id;
	private UserSummary createdBy;
	private Instant createdTime;
	private Instant expirationTime;
	private String question;
	private List<ChoiceResponse> choices;
	private boolean isExpired;
	
	private Long selectedChoice;
	private Long totalVotes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserSummary getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserSummary createdBy) {
		this.createdBy = createdBy;
	}
	public Instant getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Instant createdTime) {
		this.createdTime = createdTime;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<ChoiceResponse> getChoices() {
		return choices;
	}
	public void setChoices(List<ChoiceResponse> choices) {
		this.choices = choices;
	}
	public Long getSelectedChoice() {
		return selectedChoice;
	}
	public void setSelectedChoice(Long selectedChoice) {
		this.selectedChoice = selectedChoice;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Instant getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}
	public boolean isExpired() {
		return isExpired;
	}
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	
	
	

}
