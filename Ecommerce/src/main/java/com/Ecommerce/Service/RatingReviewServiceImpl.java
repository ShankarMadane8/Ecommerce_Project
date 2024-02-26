package com.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.RatingReviewRepository;
import com.Ecommerce.Entity.RatingReview;
import com.Ecommerce.ServiceInterfaces.RatingreviewService;

@Service
public class RatingReviewServiceImpl implements RatingreviewService {
	
	@Autowired
	RatingReviewRepository ratingReviewRepository;
	
	public List<RatingReview> getAllRatReview()
	{
		List<RatingReview> ratingReviews = ratingReviewRepository.findAll();
		
		return ratingReviews;
		
	}
}
