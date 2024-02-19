package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.OfferDao;
import com.app.entities.Offer;


@Component
public class OfferServiceImpl {
	@Autowired
	OfferDao offerDao;
	
	public Offer addOffer(Offer offer) {
		offerDao.save(offer);
		return offer;
	}
	
	public List<Offer> findAllOffer() {
		return offerDao.findAll();
	}
	
	//code by ashraf
	
public Optional<Offer> OfferByID(Long id) {
		
		System.out.println("Finding the offer with the Id: "+ id);
		return offerDao.findById(id);
	}
	
	public void deleteofferByID(Long id)
	{
		System.out.println("Finding the offer with the Id: "+ id);
		offerDao.deleteById(id);
	}
	
	public Offer updateOffer(Offer offer) {
		Offer updOff = this.offerDao.findById(offer.getId()).orElse(null);
		updOff.setName(offer.getName());
//		updOff.setDiscount(offer.getDiscount());
		updOff.setValid_upto(offer.getValid_upto());
		updOff.setValid_from(offer.getValid_from());
		updOff.setCode(offer.getCode());
		updOff.setTerms_conditions(offer.getTerms_conditions());
		
		return offerDao.save(updOff);
	}
	

}
