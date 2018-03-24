package com.revature.hydra.question.test.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.revature.hydra.question.data.SimpleQuestionRepository;
import com.revature.hydra.question.service.QuestionCompositionService;
import com.revature.wrappers.QuestionCreate;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@WebAppConfiguration
@ComponentScan("com.revature.hydra.question.*")
public class QuestionCompositionServiceTest {
	
	@Autowired
	private QuestionCompositionService questionCompositionService;
	
	@Autowired
	private SimpleQuestionRepository questionRepository;
	
	@Test
	public final void testCreateQuestion() {
		QuestionCreate qc = new QuestionCreate();
		qc.bucketId = 1;
		qc.text = "wat?";
		String[] answers = {"answer1", "answer2", "answer3", "answer4", "answer5"};
		Integer[] tagIds = {1, 2, 3, 4, 5};
		qc.answers = answers;
		qc.tagIds = tagIds;
		questionCompositionService.createQuestion(qc);
		Assert.assertEquals(questionRepository.findByBucketId(1).get(0).getQuestionText(), "wat?");
	}
	
}