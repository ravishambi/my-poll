package com.ravish.mypoll.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PollLength {
	
	@NotNull
	@Max(value = 7)
	private Integer days;
	
	@NotNull
	@Max(value = 23)
	private Integer hours;
	
	public PollLength() {
		
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	

}
