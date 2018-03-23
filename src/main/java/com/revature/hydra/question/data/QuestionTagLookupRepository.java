package com.revature.hydra.question.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.QuestionTagLookup;

@Repository
public interface QuestionTagLookupRepository extends JpaRepository<QuestionTagLookup, Integer> {
	
}
