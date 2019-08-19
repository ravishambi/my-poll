package com.ravish.mypoll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ravish.mypoll.model.Vote;
import com.ravish.mypoll.payload.ChoiceVoteCount;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	@Query("Select new com.ravish.mypoll.payload.ChoiceVoteCount(v.choice.id, count(v.id) from Vote v where v.Poll.id = :pollId group by v.choice.id")
	List<ChoiceVoteCount> countByChoiceInPollId(@Param("pollId") long pollId);
	
	@Query("Select new com.ravish.mypoll.payload.ChoiceVoteCount(v.choice.id,count(v.id) from Vote v where v.poll.id in :pollIds group by v.choice.id")
	List<ChoiceVoteCount> countByChoiceInPollIds(@Param("pollIds") List<Long> pollIds);
	
	@Query("Select count(v.id) from Vote v where v.user.id = :userId")
	Long countVoteByUser(@Param("userId") long userId);
	
	@Query("Select v from Vote v where v.user.id = :userId and v.poll.id in :pollIds")
	List<Vote> findbyUserIdInPollIds(@Param("userId") long userId, @Param("pollIds") List<Long> pollIds);
	
	@Query("Select v from Vote v where v.user.id = :userId and v.poll.id = :pollId")
	Vote findbyUserIdInPollId(@Param("userId") long userId, @Param("pollId") List<Long> pollId);
	
	
	

}
