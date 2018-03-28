package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.QuestionTagLookup;
import com.revature.beans.Tag;

@Repository
public interface QuestionTagLookupRepository extends JpaRepository<QuestionTagLookup, Integer> {
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT q.question.questionId FROM QuestionTagLookup q WHERE q.tag IN (?1)")
	List<Integer> getQuestionIdByTagId (List<Tag> tags);
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT q.tag FROM QuestionTagLookup q WHERE q.question.questionId = (?1)")
	List<Tag> getTagByQuestionId (Integer questionId);
	
	@Modifying(clearAutomatically = true)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("DELETE FROM QuestionTagLookup q WHERE q.question.questionId = (?1)")
	void deleteByQuestionId (Integer questionId);
}
