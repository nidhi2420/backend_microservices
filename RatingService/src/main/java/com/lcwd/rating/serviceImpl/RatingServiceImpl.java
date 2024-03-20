package com.lcwd.rating.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.exception.ResourceNotFoundException;
import com.lcwd.rating.payloads.RatingDto;
import com.lcwd.rating.repository.RatingRepo;
import com.lcwd.rating.services.RatingService;

import jakarta.annotation.PostConstruct;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	@Autowired
	private ModelMapper modelMapper;

	private Rating ratingDtoTORating(RatingDto ratingDto) {
		return this.modelMapper.map(ratingDto, Rating.class);
	}

	private RatingDto ratingTORatingDto(Rating rating) {
		return this.modelMapper.map(rating, RatingDto.class);
	}

	@PostConstruct
	public void configureMapper() {
		// Specify explicit mappings to resolve conflicts
		modelMapper.createTypeMap(RatingDto.class, Rating.class)
				.addMappings(mapping -> mapping.map(RatingDto::getUserId, Rating::setUserId))
				.addMappings(mapping -> mapping.map(RatingDto::getHotelId, Rating::setHotelId));
	}

	@Override
	public RatingDto createRating(RatingDto ratingDto) {
		Rating rating = ratingDtoTORating(ratingDto);
		rating.setAddedTime(new Date());
		this.ratingRepo.save(rating);
		return ratingTORatingDto(rating);
	}

	@Override
	public List<RatingDto> ratings() { 
		List<Rating> ratings = this.ratingRepo.findAll();
		List<RatingDto> ratingsDto = ratings.stream().map(this::ratingTORatingDto).collect(Collectors.toList());
		return ratingsDto;
	}

	@Override
	public List<RatingDto> getRatingsByUser(Integer uid) {
		List<Rating> ratings = this.ratingRepo.findByUserId(uid);
		if (ratings.size() == 0)
			throw new ResourceNotFoundException("user", "id", uid);
		List<RatingDto> ratingDtos = ratings.stream().map(this::ratingTORatingDto).collect(Collectors.toList());
		return ratingDtos;
	}

	@Override
	public List<RatingDto> getRatingsOfHotel(Integer hid) {

		List<Rating> ratings = this.ratingRepo.findByHotelId(hid);
		if (ratings.size() == 0)
			throw new ResourceNotFoundException("Hotel", "id", hid);
		List<RatingDto> ratingDtos = ratings.stream().map(this::ratingTORatingDto).collect(Collectors.toList());
		return ratingDtos;
	}

}
