package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.QuestionTagLookup;
import com.revature.beans.Tag;

@Repository
public interface QuestionTagLookupRepository extends JpaRepository<QuestionTagLookup, Integer> {
	
	/**
	 * Returns the question ids associated with the given tag ids
	 * 
	 * @param tags List of tag ids
	 * @return List of question ids
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT q.question.questionId FROM QuestionTagLookup q WHERE q.tag IN (?1)")
	List<Integer> getQuestionIdByTagId (List<Tag> tags);
	
	/**
	 * Returns list of tags by given question id
	 * 
	 * @param questionId Question id
	 * @return List of tags associated with given id
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT q.tag FROM QuestionTagLookup q WHERE q.question.questionId = (?1)")
	List<Tag> getTagByQuestionId (Integer questionId);
}
