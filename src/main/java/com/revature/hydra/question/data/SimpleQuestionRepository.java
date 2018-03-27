package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.SimpleQuestion;

@Repository
public interface SimpleQuestionRepository extends JpaRepository<SimpleQuestion, Integer> {
	
	/**
	 * Returns question from given Id
	 * 
	 * @param questionId Id of question
	 * @return Question object
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	SimpleQuestion findByQuestionId(Integer questionId);
	
	/**
	 * Toggles question status to (in)active based on given information
	 * 
	 * @param isActive Sets question to active if true, inactive otherwise
	 * @param questionId Id of question to be toggled
	 */
	@Modifying(clearAutomatically = true)
	@Query("update SimpleQuestion q set q.isActive = ?1 where q.questionId = ?2")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void toggleSimpleQuestionStatusById(Boolean isActive, Integer questionId);
	
	/**
	 * Returns bucket of given Id
	 * 
	 * @param bucketId Id of bucet
	 * @return Bucket of given Id
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<SimpleQuestion> findByBucketId(Integer bucketId);
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT * FROM QUESTION q WHERE QUESTION_ID IN q.QUESTION_ID IN (?1) AND q.BUCKET_ID IN (?2)")
	List<SimpleQuestion> getSpecificQuestionsByBucketId(List<Integer> questionIds, List<Integer> bucketIds);
}
