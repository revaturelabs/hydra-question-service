package com.revature.hydra.question.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Tag;

@Repository	
public interface TagRepository extends JpaRepository<Tag, Integer> {
	
	/**
	 * Returns tag of given id
	 * 
	 * @param tagId Id of tag
	 * @return Tag of given id
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Tag findByTagId(Integer tagId);
	
	/**
	 * Find the tags with the given ids
	 * @param tagIds List of tag ids to search for
	 * @return List of tags with given ids
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<Tag> findByTagIdIn(List<Integer> tagIds);
	
	/**
	 * Find tag by given name
	 * 
	 * @param tagName Name of tag
	 * @return Tag with given name
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	List<Tag> findByTagName(String tagName);
}
