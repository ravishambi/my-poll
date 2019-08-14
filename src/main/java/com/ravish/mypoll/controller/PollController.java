package com.ravish.mypoll.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ravish.mypoll.payload.PollRequest;
import com.ravish.mypoll.service.PollService;

@RestController
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@PostMapping("/poll")
	public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest poll){
		System.out.println(poll);
		return null;
		
	}

}
