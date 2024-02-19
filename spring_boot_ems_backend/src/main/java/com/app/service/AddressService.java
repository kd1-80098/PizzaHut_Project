package com.app.service;

import java.util.Optional;

import com.app.entities.Address;
import com.app.entities.Pizza;

public interface AddressService {
	public Address addAddress(Address address);

//	public Optional<Address> getAddress(long id);

	void deleteAddressById(long id);

	Optional<Address> addressByID(long id);
}
