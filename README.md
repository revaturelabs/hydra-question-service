# hydra-question-service 
### The Hydra MSA element for persisting, accessing, and messaging questions, tags, and tag lookup.

This service contains the following methods for interacting with data:
* activateQuestion
  * Description
    * Using an Integer Id value from the mapping, activate the corresponding Question
  * Endpoint
    * /question/activateQuestion/{id}
* deactivateQuestion
  * Description
    * Using an Integer Id value from the mapping, deactivate the corresponding Question
  * Endpoint
    * /question/deactivateQuestion/{id}
* getBucketQuestions
  * Description
    * Using an Integer bucketId value from the mapping, return the Questions corresponding to that bucket
  * Endpoint
    * /question/bucketQuestions/{bucketId}
* createQuestion
  * Description
    * Taking in the Integer bucketID, Question text, an array of Question Answers and an Integer array of TagIDs within the RequestBody, create and store a new Question and the corresponding QuestionTagLookups
  * Endpoint
    * /question/createQuestion
* filterQuestions
  * Description
    * Returns a List of Questions responding to the given skillTypeId and tags in a RequestBody
  * Endpoint
    * /question/filtered
* updateQuestion
  * Description
    * Consumes a RequestBody as a SimpleQuestion and updates the corresponding entry in the database with the new values.
  * Endpoint
    * /question/updateQuestion
* getAllTags
  * Description
    * Returns all tags in the Tag table.
  * Endpoint
    * /tag/getAllTags
* createNewTag
  * Description
    * Taking in the tagName value within the RequestBody, create and store the new Tag
  * Endpoint	
    * /tag/createNewTag
* getTagsByQuestionId
  * Description
    * Returns all tags that are related to the given questionId
  * Endpoint
    * /tag/getTagByQuestionId/{id}

The Question service communicates with the following services:
* Bucket
* QuestionScore
	
