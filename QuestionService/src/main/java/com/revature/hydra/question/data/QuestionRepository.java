package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	/**
	 * Toggles question status to (in)active based on given information
	 * 
	 * @param isActive Sets question to active if true, inactive otherwise
	 * @param questionId Id of question to be toggled
	 */
	@Modifying
	@Query("update Question q set q.isActive = ?1 where q.questionId = ?2")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void toggleQuestionStatusById(Boolean isActive, Integer questionId);
	
	/**
	 * Returns bucket of given Id
	 * 
	 * @param bucketId Id of bucet
	 * @return Bucket of given Id
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<Question> findByBucketId(Integer bucketId);
	
}
