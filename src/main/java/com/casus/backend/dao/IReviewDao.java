package com.casus.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.casus.backend.model.Review;

public interface IReviewDao extends CrudRepository<Review, Long> {
	
	@Override
	public List<Review> findAll();

	public List<Review> findByProductId(Long productId);

}
