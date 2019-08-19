package com.ravish.mypoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravish.mypoll.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long>{

}
