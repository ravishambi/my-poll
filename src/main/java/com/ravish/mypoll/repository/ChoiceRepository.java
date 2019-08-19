package com.ravish.mypoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravish.mypoll.model.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

}
