package com.ravish.mypoll.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ravish.mypoll.model.Poll;

@Repository
public class PollEntityRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Poll> getPolls(){
		TypedQuery<Poll> query = entityManager.createQuery("select distinct p from Poll p join fetch p.choices",Poll.class);
		return query.getResultList();
	}
	
	public Poll getPoll(String pollId) {
		TypedQuery<Poll> query = entityManager.createQuery("select p from Poll p join fetch p.choices where p.id = :pollId", Poll.class);
		query.setParameter("pollId", Long.parseLong(pollId));
		
		try {
			return query.getSingleResult();
		}
		catch(NoResultException ex) {
			// Runtime exception
		}
		return null;
	}
	

}
