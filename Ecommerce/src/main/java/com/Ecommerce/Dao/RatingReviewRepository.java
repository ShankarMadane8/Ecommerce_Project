package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.RatingReview;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, Integer>{

}
