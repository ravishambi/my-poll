package com.ravish.mypoll.utils;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ravish.mypoll.model.Poll;
import com.ravish.mypoll.model.User;
import com.ravish.mypoll.payload.ChoiceResponse;
import com.ravish.mypoll.payload.PollResponse;
import com.ravish.mypoll.payload.UserSummary;

public class ModelMapper {
	
	public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
		
		PollResponse pollResponse = new PollResponse();
		pollResponse.setQuestion(poll.getQuestion());
		pollResponse.setId(poll.getId());
		pollResponse.setExpirationTime(poll.getExpirationDateTime());
		pollResponse.setCreatedTime(poll.getCreatedAt());
		pollResponse.setExpired(poll.getExpirationDateTime().isBefore(Instant.now()));
		
		
		List<ChoiceResponse> choiceResponse = poll.getChoices().stream()
			.map(choice -> new ChoiceResponse(choice.getId(),choice.getChoice(),choiceVotesMap.get(choice.getId())))
			.collect(Collectors.toList());
		
		pollResponse.setChoices(choiceResponse);
		
		UserSummary userSummary = new UserSummary();
		userSummary.setId(creator.getId());
		userSummary.setName(creator.getName());
		userSummary.setUsername(creator.getUserName());
		pollResponse.setCreatedBy(userSummary);
		
		
		pollResponse.setSelectedChoice(userVote);
		pollResponse.setTotalVotes(pollResponse.getChoices().stream()
				.mapToLong(ChoiceResponse :: getVoteCount).sum());
		
		return pollResponse;
	}

}
