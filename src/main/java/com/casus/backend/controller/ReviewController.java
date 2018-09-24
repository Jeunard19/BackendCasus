package com.casus.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casus.backend.model.Review;
import com.casus.backend.service.IReviewService;




@RestController
public class ReviewController {
	
	@Autowired
	private IReviewService iReviewService; 
	
	@GetMapping("/api/review/product/{id}")
	public List<Review> findByProductId(@PathVariable("id") Long productId) {
		return this.iReviewService.findByProductId(productId);
	}
	
	@PostMapping("/api/review")
	public Review create(@RequestBody Review review) {
		return this.iReviewService.create(review);
	}

}


