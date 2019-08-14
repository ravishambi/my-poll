package com.ravish.mypoll.payload;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PollRequest {
	
	@NotBlank
	@Size(min=2, max=40)
	private String question;
	
	@NotNull
	private List<ChoiceRequest> choices;
	
	@NotNull
	private PollLength pollLength;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<ChoiceRequest> getChoices() {
		return choices;
	}

	public void setChoices(List<ChoiceRequest> choices) {
		this.choices = choices;
	}

	public PollLength getPollLength() {
		return pollLength;
	}

	public void setPollLength(PollLength pollLength) {
		this.pollLength = pollLength;
	}
	
	

}
