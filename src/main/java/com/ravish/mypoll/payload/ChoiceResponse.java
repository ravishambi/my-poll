package com.ravish.mypoll.payload;

public class ChoiceResponse {
	
	private long id;
	private String choice;
	private long voteCount;
	
	public ChoiceResponse(long id, String choice, long voteCount) {
		this.id = id;
		this.choice = choice;
		this.voteCount = voteCount;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public long getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}
	
	

}
