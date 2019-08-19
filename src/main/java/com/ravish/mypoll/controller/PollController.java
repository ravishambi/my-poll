package com.ravish.mypoll.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ravish.mypoll.CurrentUser;
import com.ravish.mypoll.exception.ResourceNotFoundException;
import com.ravish.mypoll.model.Choice;
import com.ravish.mypoll.model.Poll;
import com.ravish.mypoll.payload.ApiResponse;
import com.ravish.mypoll.payload.ChoiceRequest;
import com.ravish.mypoll.payload.PollLength;
import com.ravish.mypoll.payload.PollRequest;
import com.ravish.mypoll.payload.PollResponse;
import com.ravish.mypoll.payload.VoteChoiceRequest;
import com.ravish.mypoll.security.CustomUserDetails;
import com.ravish.mypoll.service.PollService;

@RestController
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@PostMapping("/poll")
	public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest){
		
		Poll poll = pollService.createPoll(pollRequest);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{pollId}")
				.buildAndExpand(poll.getId()).toUri();
		
		return ResponseEntity.created(location)
			.body(new ApiResponse(true,"Poll created successfully"));
		
	}
	
	@GetMapping("/poll")
	public ResponseEntity<?> getAllPolls(){
		
		List<Poll> polls = pollService.getPolls();
		/*polls.stream().forEach(poll -> {
			List<Choice> choices = poll.getChoices();
			System.out.println(poll.getQuestion());
			choices.stream().forEach(choice -> {
				System.out.println(choice.getChoice());
			});
		});*/
		
		return ResponseEntity.status(HttpStatus.OK).body(polls);
	}
	
	@GetMapping("/poll/{pollId}")
	public ResponseEntity<?> getPoll(@PathVariable String pollId){
		
		Optional<Poll> poll = pollService.getPoll(pollId);
		if(poll.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(poll.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Poll","Id", pollId));
		}
		
	}
	
	@PostMapping("/poll/{pollId}/castVote")
	public ResponseEntity<?> castVote(@CurrentUser CustomUserDetails currentUser, 
			@PathVariable String pollId, 
			@Valid VoteChoiceRequest voteChoiceRequest){
		
		PollResponse pollResponse = pollService.castVoteAndGetUpdatedResponse(pollId,voteChoiceRequest, currentUser);
		
		return ResponseEntity.status(HttpStatus.OK).body(pollResponse);
	}

}
