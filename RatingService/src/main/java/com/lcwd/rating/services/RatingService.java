package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.payloads.RatingDto;

public interface RatingService  {

	RatingDto createRating(RatingDto ratingDto);
	
	List<RatingDto> ratings();
	
	List<RatingDto> getRatingsByUser(Integer uid);
	
	List<RatingDto> getRatingsOfHotel(Integer hid);
	
}
