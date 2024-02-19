package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entities.Review;


public interface ReviewsService {
	public Review addReview(Review review);
	
	public Optional<Review> getReview(long id);
	
	public void deleteReviewById(long id);
	
	
}
