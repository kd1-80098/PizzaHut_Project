package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.ReviewsDao;
import com.app.entities.Review;


@Component
public class ReviewsServiceImpl implements ReviewsService {

	
	@Autowired                                                                                                                                                                                                                        
	ReviewsDao reviewsDao;
	
	@Override
	public Review addReview(Review review) {
		reviewsDao.save(review);
		return review;
	}


	public List<Review> findAllReview() {
		return reviewsDao.findAll();
	}


	@Override
	public Optional<Review> getReview(long id) {
		return reviewsDao.findById(id);
	}


	@Override
	public void deleteReviewById(long id) {
		reviewsDao.deleteById(id);
		
	}


	public List<Review> findReviewByProduct(long id) {
		return reviewsDao.findByPizzaId(id);
	}


	public List<Review> findReviewByUser(long id) {
		return reviewsDao.findByUserId(id);
	}
	
}
