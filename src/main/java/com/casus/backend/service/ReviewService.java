package com.casus.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.casus.backend.dao.IReviewDao;
import com.casus.backend.model.Review;


@Service
public class ReviewService implements IReviewService {
	
	@Autowired
	private IReviewDao iReviewDao; //

	@Override
	public Review create(Review review) {
		Assert.notNull(review, "Review may not be null");

		return this.iReviewDao.save(review);
	}

	@Override
	public List<Review> findByProductId(Long productId) {
		return this.iReviewDao.findByProductId(productId);
	}

}
