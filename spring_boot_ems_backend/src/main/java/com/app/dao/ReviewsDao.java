package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Review;


public interface ReviewsDao extends JpaRepository<Review, Long> {

	List<Review> findByPizzaId(long id);

	List<Review> findByUserId(long id);

}
