package com.ravish.mypoll.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravish.mypoll.exception.BadRequestException;
import com.ravish.mypoll.exception.ResourceNotFoundException;
import com.ravish.mypoll.model.Choice;
import com.ravish.mypoll.model.Poll;
import com.ravish.mypoll.model.User;
import com.ravish.mypoll.model.Vote;
import com.ravish.mypoll.payload.ChoiceRequest;
import com.ravish.mypoll.payload.ChoiceVoteCount;
import com.ravish.mypoll.payload.PollLength;
import com.ravish.mypoll.payload.PollRequest;
import com.ravish.mypoll.payload.PollResponse;
import com.ravish.mypoll.payload.VoteChoiceRequest;
import com.ravish.mypoll.repository.ChoiceRepository;
import com.ravish.mypoll.repository.PollEntityRepository;
import com.ravish.mypoll.repository.PollRepository;
import com.ravish.mypoll.repository.UserRepository;
import com.ravish.mypoll.repository.VoteRepository;
import com.ravish.mypoll.security.CustomUserDetails;
import com.ravish.mypoll.utils.ModelMapper;

@Service
public class PollService {
	
	private static final Logger logger = LoggerFactory.getLogger(PollService.class);
	
	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private ChoiceRepository choiceRepository;
	
	@Autowired
	private PollEntityRepository pollEntityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Poll createPoll(PollRequest pollRequest) {
		Poll poll = new Poll();
		poll.setQuestion(pollRequest.getQuestion());
		List<ChoiceRequest> choiceRequests = pollRequest.getChoices();
		
		choiceRequests.stream().forEach(choiceRequest -> {
			poll.addChoice(new Choice(choiceRequest.getChoice()));
		});
		
		PollLength pollLength = pollRequest.getPollLength();
		
		Instant instant = Instant.now();
		instant = instant.plus(pollLength.getDays(),ChronoUnit.DAYS).plus(pollLength.getHours(), ChronoUnit.HOURS);
		
		poll.setExpirationDateTime(instant);
		
		logger.info("Poll has been created successfully");
		return pollRepository.save(poll);
	}
	
	@Transactional
	public List<Poll> getPolls(){
		//return pollRepository.findAll();
		return pollEntityRepository.getPolls();
	}
	
	public Optional<Poll> getPoll(String pollId) {
		//return pollRepository.findById(Long.parseLong(pollId));
		Poll poll = pollEntityRepository.getPoll(pollId);
		return Optional.ofNullable(poll);
	}
	
	public Optional<Choice> getChoice(String choiceId) {
		return choiceRepository.findById(Long.parseLong(choiceId));
	}

	public PollResponse castVoteAndGetUpdatedResponse(String pollId, VoteChoiceRequest voteChoiceRequest,
			CustomUserDetails currentUser) {
		
		Optional<Poll> optionalPoll = getPoll(pollId);
		Poll poll = optionalPoll.orElseThrow(() -> new ResourceNotFoundException("poll","id", pollId));
		
		Choice choice = poll.getChoices().stream()
			.filter(c -> c.getId() == voteChoiceRequest.getId())
			.findFirst()
			.orElseThrow(() -> new BadRequestException("Sorry! This is not a valid choice"));
		
		if(!poll.getExpirationDateTime().isBefore(Instant.now())) {
			throw new BadRequestException("Sorry! This poll is already expired");
		}
		
		Vote vote = new Vote();
		vote.setPoll(poll);
		vote.setChoice(choice);
		vote.setUser(currentUser);
		
		voteRepository.save(vote);
		
		List<ChoiceVoteCount> choiceVoteCounts = voteRepository.countByChoiceInPollId(Long.parseLong(pollId));
		Map<Long,Long> choiceVoteCountMap = choiceVoteCounts.stream().collect(Collectors.toMap(ChoiceVoteCount::getChoice, ChoiceVoteCount::getVotes));
		
		User creator = userRepository.findById(poll.getCreatedBy())
				.orElseThrow(() -> new ResourceNotFoundException("User","Id", poll.getCreatedBy()));
		
		return ModelMapper.mapPollToPollResponse(poll, choiceVoteCountMap, creator, voteChoiceRequest.getId());
		
	}

}
