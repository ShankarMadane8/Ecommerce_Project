package com.Ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.AddressRepository;
import com.Ecommerce.Entity.Address;
import com.Ecommerce.ServiceInterfaces.AddressService;

@Service
public class AddressServiceImple implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public String saveAddress(Address address) {
	
		addressRepository.save(address);
		return "Address Save Succefully";
	}

	@Override
	public String deleteAddress(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddress() {
		
		List<Address> addresses = addressRepository.findAll();
		return addresses;
	}

	@Override
	public List<Address> getAddressesByUserId(int user_id) {
		// TODO Auto-generated method stub
		List<Address> addresses = addressRepository.findByUserId(user_id);
		return addresses;
	}

	@Override
	public Address getAddressById(int id) {
		
		Optional<Address> optional = addressRepository.findById(id);
		return optional.get();
	}

}
