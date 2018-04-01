package com.revature.hydra.question.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class QuestionCompositionMessagingService {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	private static String SKILLTYPE_ROUTING_KEY = "ugxlIFj1zg93JkKd";
	private static String RABBIT_REPO_EXCHANGE = "revature.hydra.repos";
	
	/**
	 * Sends skill type id and returns list of bucket ids
	 * 
	 * @param SkillTypeId Skilltype id
	 * @return List of associated bucket ids
	 */
	public List<Integer> sendBucketIdsRequest(Integer SkillTypeId) {
		JsonObject bucketIdsRequest = new JsonObject();
		
		bucketIdsRequest.addProperty("methodName", "findBucketIdsBySkillType");
		bucketIdsRequest.addProperty("skillTypeId", SkillTypeId);
		
		List<Integer> list =  (List<Integer>)rabbitTemplate.convertSendAndReceive(RABBIT_REPO_EXCHANGE,
				SKILLTYPE_ROUTING_KEY, bucketIdsRequest.toString());
		return list;
	}
}
