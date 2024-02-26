package com.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.LookupRepository;
import com.Ecommerce.Entity.Lookup;

@Service
public class LookupServiceimpl {
	
	@Autowired
	LookupRepository lookupRepository;
	
	public List<Lookup> getLookups()
	{
		List<Lookup> lookups = lookupRepository.findAll();
		return lookups;
	}
}
