package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag implements Serializable {

	private static final long serialVersionUID = -1015737769787058334L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQUENCE")
	@SequenceGenerator(name = "TAG_SEQUENCE", sequenceName = "TAG_SEQUENCE")
	@Column(name = "TAG_ID")
	private Integer tagId;

	@Column(name = "TAG_NAME")
	private String tagName;

	public Tag() {
		super();
	}

	public Tag(String tagName) {
		super();
		this.tagName = tagName;
	}

	public Tag(Integer tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

}
