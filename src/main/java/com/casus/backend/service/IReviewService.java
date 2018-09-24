package com.casus.backend.service;

import java.util.List;

import com.casus.backend.model.Review;


public interface IReviewService {
	
	public Review create(Review review);

	public List<Review> findByProductId(Long productId);

}
