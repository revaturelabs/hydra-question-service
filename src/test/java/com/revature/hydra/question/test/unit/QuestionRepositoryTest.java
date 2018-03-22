package com.revature.hydra.question.test.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.revature.beans.Question;
import com.revature.hydra.question.data.QuestionRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@WebAppConfiguration
public class QuestionRepositoryTest {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	private Question q;
	
	@Before
	public final void init() {
		q = new Question(1, true, "Test question?", "Test answer 1", "Test answer 2",
				"Test answer 3", "Test answer 4", "Test answer 5");
		q = questionRepository.save(q);
	}
	
	@After
	public final void end() {
		questionRepository.delete(q);
	}
	
	@Test
	public final void testFindByBucketId() {
		Question p = questionRepository.findByBucketId(1).get(0);
		Assert.assertEquals(p.getQuestionId(), q.getQuestionId());
	}
	
	@Test
	public final void testToggleQuestionStatusById() {
		int id = q.getQuestionId();
		q = questionRepository.findByQuestionId(id);
		Assert.assertTrue(q.getIsActive());
		questionRepository.toggleQuestionStatusById(false, id);
		q = questionRepository.findByQuestionId(id);
		Assert.assertFalse(q.getIsActive());
		questionRepository.toggleQuestionStatusById(true, id);
		q = questionRepository.findByQuestionId(id);
		Assert.assertTrue(q.getIsActive());
	}
	
}
