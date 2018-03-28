package com.revature.hydra.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.QuestionTagLookup;
import com.revature.beans.SimpleQuestion;
import com.revature.beans.Tag;
import com.revature.hydra.question.data.QuestionTagLookupRepository;
import com.revature.hydra.question.data.SimpleQuestionRepository;
import com.revature.hydra.question.data.TagRepository;
import com.revature.wrappers.Filter;
import com.revature.wrappers.QuestionCreate;
import com.revature.wrappers.QuestionUpdate;

@Service
public class QuestionCompositionService {

	@Autowired
	public SimpleQuestionRepository questionRepository;

	@Autowired
	public TagRepository tagRepository;

	@Autowired
	public QuestionTagLookupRepository questionTagLookupRepository;
	
	@Autowired
	public QuestionCompositionMessagingService questionCompositionMessagingService;

	/**
	 * Creates a question based on given information
	 * 
	 * @param bucketId
	 *            Id of bucket
	 * @param questionText
	 *            Text of question
	 * @param answers
	 *            Sample answers to question
	 * @param tagIds
	 *            Ids of tags to be associated with question
	 */
	public void createQuestion(QuestionCreate creator) {
		List<Tag> tags = new ArrayList<>();
		for (Integer i : creator.tagIds) {
			tags.add(tagRepository.findByTagId(i));
		}
		SimpleQuestion q = new SimpleQuestion(creator.bucketId, true, creator.text, creator.answers[0],
				creator.answers[1], creator.answers[2], creator.answers[3], creator.answers[4]);
		q = questionRepository.save(q);
		for (Tag t : tags) {
			QuestionTagLookup qtl = new QuestionTagLookup(t, q);
			questionTagLookupRepository.save(qtl);
		}
	}
	
	public void updateQuestion(QuestionUpdate q) {
		questionRepository.updateQuestion(q.question.getBucketId(), q.question.getQuestionText(), q.question.getSampleAnswer1(), 
				q.question.getSampleAnswer2(), q.question.getSampleAnswer3(), q.question.getSampleAnswer4(),
				q.question.getSampleAnswer5(), q.question.getQuestionId());
		questionTagLookupRepository.deleteByQuestionId(q.question.getQuestionId());
		List<Tag> tags = new ArrayList<>();
		for (Integer i : q.tagIds) {
			tags.add(tagRepository.findByTagId(i));
		}
		for (Tag t : tags) {
			QuestionTagLookup qtl = new QuestionTagLookup(t, q.question);
			questionTagLookupRepository.save(qtl);
		}
	}

	/**
	 * Filters questions based on inputed list of tagIds and skillTypeId
	 * 
	 * @param filter
	 * 		Filter object which contains tagList and skillTypeId
	 * @return
	 * 		List of SimpleQuestion objects
	 */
	public List<SimpleQuestion> filterQuestion(Filter filter) {
		List<Integer> bucketIds = questionCompositionMessagingService.sendBucketIdsRequest(filter.skillTypeId);
		List<Tag> tags = tagRepository.findByTagIdIn(filter.tagList);
		List<Integer> questionIds = questionTagLookupRepository.getQuestionIdByTagId(tags);
		return questionRepository.getSpecificQuestionsByBucketId(questionIds, bucketIds);
	}
}
