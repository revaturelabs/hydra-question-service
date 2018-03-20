package com.revature.hydra.question.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Question;
import com.revature.hydra.question.data.QuestionRepository;
import com.revature.hydra.question.service.QuestionCompositionService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
	
	private static final Logger log = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuestionCompositionService questionCompositionService;
	
	/**
	 * Sets question to active status
	 * 
	 * @param questionId Id of question to be activated
	 * @return No content
	 */
	@RequestMapping(value = "/activateQuestion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> activateQuestion(@PathVariable(value="id") Integer questionId) {
		log.info("Activating question: " + questionId);
		questionRepository.toggleQuestionStatusById(true, questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Sets question to inactive status
	 * 
	 * @param questionId Id of question to be deactivated
	 * @return No content
	 */
	@RequestMapping(value = "/deactivateQuestion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> deactivateQuestion(@PathVariable(value="id") Integer questionId) {
		log.info("Deactivating question: " + questionId);
		questionRepository.toggleQuestionStatusById(false, questionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Returns questions associated with bucket of given id
	 * 
	 * @param bucketId Id of bucket
	 * @return List of questions associated with bucket of given id
	 */
	@RequestMapping(value = "/bucketQuestions/{bucketId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Question>> getBucketQuestions(@PathVariable(value="bucketId") Integer bucketId) {
		log.info("Getting questions for bucket: " + bucketId);
		return new ResponseEntity<>(questionRepository.findByBucketId(bucketId), HttpStatus.FOUND);
	}
	
	/**
	 * Creates a question based on given information
	 * 
	 * @param bucketId Id of bucket
	 * @param questionText Text of question
	 * @param answers Sample answers to question
	 * @param tagIds Ids of tags to be associated with question
	 * @return No content
	 */
	@RequestMapping(value = "/createQuestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createQuestion(@RequestParam(value="bucketId") Integer bucketId, @RequestParam(value="text") String questionText, @RequestParam(value="answers") String[] answers, @RequestParam(value="tagIds") Integer[] tagIds) {
		log.info("Creating message");
		questionCompositionService.createQuestion(bucketId, questionText, answers, tagIds);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
