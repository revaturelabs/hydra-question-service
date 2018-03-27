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
	 * @param questionId 
	 * 		Id of question
	 * @return SimpleQuestion 
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	SimpleQuestion findByQuestionId(Integer questionId);
	
	/**
	 * Toggles question status to (in)active based on given information
	 * 
	 * @param isActive 
	 * 		Sets question to active if true, inactive otherwise
	 * @param questionId 
	 * 		Id of question to be toggled
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
	
	/**
	 * Gets those questions with the specified questionIds belonging to the specified buckets
	 * 
	 * @param questionIds
	 * 		list of valid questionIds, preferably containing one of several tags
	 * @param bucketIds
	 * 		list of valid bucketIds, preferably within the same skillType
	 * @return List<SimpleQuestion>
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("SELECT q FROM SimpleQuestion q WHERE q.questionId IN (?1) AND q.bucketId IN (?2)")
	List<SimpleQuestion> getSpecificQuestionsByBucketId(List<Integer> questionIds, List<Integer> bucketIds);
	
	/**
	 * Updates the Question of specified questionId with the bucketId, question and answers specified
	 * 
	 * @param bucketId
	 * @param questionText
	 * @param answer1
	 * @param answer2
	 * @param answer3
	 * @param answer4
	 * @param answer5
	 * @param questionId
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Query("UPDATE SimpleQuestion q SET q.bucketId = ?1, q.questionText = ?2, q.sampleAnswer1 = ?3, q.sampleAnswer2 = ?4, " + 
			"q.sampleAnswer3 = ?5, q.sampleAnswer4 = ?6, q.sampleAnswer5 = ?7 WHERE q.questionId = ?8")
	void updateQuestion (Integer bucketId, String questionText, String answer1, String answer2, String answer3, String answer4, String answer5, Integer questionId);
}
