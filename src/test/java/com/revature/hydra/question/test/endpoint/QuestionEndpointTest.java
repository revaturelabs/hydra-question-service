package com.revature.hydra.question.test.endpoint;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@DataJpaTest
@WebAppConfiguration
@ComponentScan("com.revature.hydra.question.*")
@Ignore
public class QuestionEndpointTest {

	@BeforeClass
	public static final void init() {
		RestAssured.baseURI = "http://127.0.0.1:8080";
	}
	
	@Test
	public final void testCreateQuestion() {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE);
		JsonObject o = new JsonObject();
		o.addProperty("bucketID", 1);
		o.addProperty("text", "lel");
		String[] answers = {"answer1", "answer2", "answer3", "answer4", "answer5"};
		Integer[] tagIds = {1, 2, 3, 4, 5};
		JsonArray jAnswers = new JsonArray();
		for (String answer : answers) {
			jAnswers.add(answer);
		}
		JsonArray jTagIds = new JsonArray();
		for (Integer i : tagIds) {
			jTagIds.add(i);
		}
		o.add("answers", jAnswers);
		o.add("tagIds", jTagIds);
		request.body(o.toString());
		Response response = request.post("/question/createQuestion");
		int statusCode = response.getStatusCode();
		// 204 means no content
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	public final void testGetBucketQuestions() {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE);
		Response response = request.get("/question/bucketQuestions/1");
		// 302 means found
		Assert.assertEquals(response.getStatusCode(), 302);
		Assert.assertEquals(response.contentType(), MediaType.APPLICATION_JSON_VALUE);
	}
	
	@Test
	public final void testActivateDeactivate() {
		RequestSpecification request = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE);
		Response response = request.put("/question/activateQuestion/1");
		// 204 means no content
		Assert.assertEquals(response.getStatusCode(), 204);
		response = request.put("/question/deactivateQuestion/1");
		// 204 means no content
		Assert.assertEquals(response.getStatusCode(), 204);
	}
	
}
