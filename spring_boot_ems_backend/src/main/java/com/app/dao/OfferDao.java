package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Offer;



public interface OfferDao extends JpaRepository<Offer, Long> {

}
