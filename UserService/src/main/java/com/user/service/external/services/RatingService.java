package com.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.user.service.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@GetMapping(value = "/ratings/user/{uid}")
	List<Rating> getRatingsOfUser(@PathVariable int uid);

	


}