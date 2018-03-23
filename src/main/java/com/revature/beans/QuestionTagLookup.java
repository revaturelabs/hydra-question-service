package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="QUESTION_TAG_LOOKUP")
public class QuestionTagLookup implements Serializable {
	
	private static final long serialVersionUID = -7429128509208659401L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="QUESTION_TAG_LOOKUP_SEQUENCE")
	@SequenceGenerator(name="QUESTION_TAG_LOOKUP_SEQUENCE",sequenceName="QUESTION_TAG_LOOKUP_SEQUENCE")
	@Column(name="QUESTION_TAG_LOOKUP_ID")
	private Integer questionTagLookupId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TAG_ID")
	private Tag tag;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="QUESTION_ID")
	private SimpleQuestion question;

	public QuestionTagLookup() {
		super();
	}

	public QuestionTagLookup(Tag tag, SimpleQuestion question) {
		super();
		this.tag = tag;
		this.question = question;
	}

	public int getQuestionTagLookupId() {
		return questionTagLookupId;
	}

	public void setQuestionTagLookupId(int questionTagLookupId) {
		this.questionTagLookupId = questionTagLookupId;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public SimpleQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SimpleQuestion question) {
		this.question = question;
	}
	
}
