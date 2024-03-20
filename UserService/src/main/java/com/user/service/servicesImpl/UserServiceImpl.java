package com.user.service.servicesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.external.services.RatingService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User saveUser(User user) {
		
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = this.userRepository.findAll();
		
		
		 users.forEach(user -> {
		        try {
		            List<Rating> ratingOfUser = this.ratingService.getRatingsOfUser(user.getId());
		            System.out.println(ratingOfUser);
		            user.setRatings(ratingOfUser);
		            
		            ratingOfUser.forEach(rating -> {
		                try {
		                    Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
		                    rating.setHotel(hotel);
		                } catch (Exception e) {
		                    // Log error or handle exception
		                    System.err.println("Error fetching hotel: " + e.getMessage());
		                }
		            });
		        } catch (Exception e) {
		            // Log error or handle exception
		            System.err.println("Error fetching ratings: " + e.getMessage());
		        }
		    });
		 
		return users;
	}

	@Override
	public User getUser(Integer userId) {
	User user =		this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
	// fetch rating of the above user from RATING-SERVICE
	//rating service has api to get ratings of user by user id,but we know these api can be called by client so we need a client RestTemplate , FeignClient
	//http://localhost:8084/ratings/user/1
//	Rating[] ratingList = restTemplate.getForObject("http://localhost:8084/ratings/user/"+user.getId(), Rating[].class);
	List<Rating> ratingOfUser = ratingService.getRatingsOfUser(user.getId());
	//problem with using the above harcoded url is , its not dynamic and url might change ,but service name will remain unchanged so we will use service name
//	List<Rating> ratingOfUser=Arrays.stream(ratingList).toList();
	user.setRatings(ratingOfUser);
	logger.info("{} ",ratingOfUser);
	
	ratingOfUser.stream().map(rating->{
		//api call to hotel service to get the hotel
		//private String hotelName;
		//http://localhost:8083/hotel/1
//		ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://localhost:8083/hotel/"+rating.getHotelId(), Hotel.class);
		Hotel hotel = hotelService.getHotel(rating.getHotelId());
		//set the hotel to rating
		rating.setHotel(hotel);
		
		//return the rating
		return rating;
	}).collect(Collectors.toList());
	
	//fetch hotels which are rated by the user
	//http://localhost:8084/ratings/user/1
	
	return user;
}

	@Override
	public String deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		this.userRepository.delete(user);
		return "deleted successfully";
	}

	@Override
	public User updateUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
