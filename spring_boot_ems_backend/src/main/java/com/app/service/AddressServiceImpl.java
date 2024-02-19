package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Address;
import com.app.entities.Pizza;
import com.app.dao.AddressDao;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressDao addressDao;
	
	@Override
	public Address addAddress(Address address) {
		addressDao.save(address);
		return address;
	}
	
	public List<Address> findAllAddress(){
		return addressDao.findAll();
	}
	

	public void deleteAddressById(long id) {
		addressDao.deleteById(id);
	}

	
	public List<Address> getAddressByUser(long id) {
		return addressDao.findByUserId(id);
	}
	
	@Override
	public Optional<Address> addressByID(long id) {
		return addressDao.findById(id);
	}
	

}
