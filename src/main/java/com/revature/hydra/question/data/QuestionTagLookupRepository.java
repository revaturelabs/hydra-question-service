package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.QuestionTagLookup;

@Repository
public interface QuestionTagLookupRepository extends JpaRepository<QuestionTagLookup, Integer> {
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("select QUESTION_ID from QUESTION_TAG_LOOKUP q WHERE q.TAG_ID IN (?1)")
	List<Integer> getQuestionIdByTagId (List<Integer> tagIds);
}
