package com.lcwd.rating.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lcwd.rating.entity.Rating;

@Repository
public interface RatingRepo extends MongoRepository<Rating, Integer> {

	List<Rating> findByUserId(Integer uid);
	
	List<Rating> findByHotelId(Integer hid);
}
