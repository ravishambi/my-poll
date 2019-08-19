package com.ravish.mypoll.payload;

public class VoteChoiceRequest {
	
	private String choice;
	private Long id;
	
	public VoteChoiceRequest() {
		
	}
	
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
