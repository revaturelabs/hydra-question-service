# hydra-question-service 
The Question service handles CRUD operations and messaging for the SimpleQuestion, Tag and QuestionTagLookup beans.

Location of Question Related Beans:

	com.revature.beans

Question Specific Beans:

	SimpleQuestion

	QuestionTagLookup

	Tag
	

Methods contained within this service:

**QuestionController - Found in com/revature/hydra/question/controller/**
The QuestionController is where the Question service end points are defined.

End Points
* /question/activateQuestion/{id}
	Using an Integer Id value from the mapping, activate the corresponding Question
* /question/deactivateQuestion/{id}
	Using an Integer Id value from the mapping, deactivate the corresponding Question
* /question/bucketQuestions/{bucketId}
	Using an Integer bucketId value from the mapping, return the Questions corresponding to that bucket
* /question/createQuestion
	Taking in the Integer bucketID, Question text, an array of Question Answers and an Integer array of TagIDs within the 		RequestBody, create and store a new Question and the corresponding QuestionTagLookups
* /tag/getTags
	Returns all tags in the Tag table
* /tag/createNewTag
	Taking in the tagName value within the RequestBody, create and store the new Tag

QuestionCompositionService 

	createQuestion
	
