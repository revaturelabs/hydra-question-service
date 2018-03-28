package com.revature.hydra.question.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Tag;
import com.revature.hydra.question.data.QuestionTagLookupRepository;
import com.revature.hydra.question.data.TagRepository;

@RestController
@CrossOrigin
public class TagController {
	
	private static final Logger log = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private QuestionTagLookupRepository questionTagLookupRepository;
	
	/**
	 * Returns all tags
	 * 
	 * @return List of all tags
	 */
	@RequestMapping(value = "/tag/getAllTags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tag>> getAllTags() {
		log.info("Getting all tags");
		return new ResponseEntity<>(tagRepository.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Creates tag by given name
	 * 
	 * @param tagName Name of tag to be created
	 * @return Tag tag that was created
	 */
	@RequestMapping(value = "/tag/createNewTag", method = RequestMethod.POST)
	public ResponseEntity<Tag> createNewTag(@RequestBody Tag tag) {
		log.info("Creating tag: " + tag.getTagName());
		Tag t = new Tag(tag.getTagName());
		tagRepository.save(t);
		return new ResponseEntity<>(tagRepository.findByTagName(tag.getTagName()).get(0), HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Returns the list of tags associated with the given question id
	 * 
	 * @param questionId Id of question
	 * @return List of tags associated with given id
	 */
	@RequestMapping(value = "/tag/getTagByQuestionId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tag>> getTagsByQuestionId(@PathVariable(value="id") Integer questionId) {
		return new ResponseEntity<>(questionTagLookupRepository.getTagByQuestionId(questionId), HttpStatus.OK);
	}
}
