package com.revature.hydra.question.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Tag;
import com.revature.hydra.question.data.TagRepository;

@RestController
@CrossOrigin
public class TagController {
	
	private static final Logger log = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private TagRepository tagRepository;
	
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
	 * @return No content
	 */
	@RequestMapping(value = "/tag/createNewTag", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewTag(@RequestParam(value="tagName") String tagName) {
		log.info("Creating tag: " + tagName);
		Tag t = new Tag(tagName);
		tagRepository.save(t);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
