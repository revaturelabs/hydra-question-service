package com.revature.hydra.question.controller;

import java.util.List;

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
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagRepository tagRepository;
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tag>> getAllTags() {
		return new ResponseEntity<>(tagRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createNewTag", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewTag(@RequestParam(value="tagName") String tagName) {
		Tag t = new Tag(tagName);
		tagRepository.save(t);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
