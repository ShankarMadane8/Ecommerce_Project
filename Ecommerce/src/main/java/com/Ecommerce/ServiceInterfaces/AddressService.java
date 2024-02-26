package com.Ecommerce.ServiceInterfaces;

import java.util.List;

import com.Ecommerce.Entity.Address;

public interface AddressService {

	String saveAddress(Address address); 		//to save Address
	String deleteAddress(int id);				// to delete address using id
	List<Address> getAllAddress();				// to get all address
	List<Address> getAddressesByUserId(int user_id);			// to det single address
	Address getAddressById(int id);
}
