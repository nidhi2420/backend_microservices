package com.lcwd.hotel.controller;

import java.util.List;

import javax.transaction.xa.XAException;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.payloads.ApiResponse;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PutMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		return new ResponseEntity<Hotel>(this.hotelService.createHotel(hotel), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = this.hotelService.getAllHotels();
		System.out.println("inside hotel service "+hotels);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable Integer id) {
		Hotel hotel = this.hotelService.getHotelById(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable Integer id){
		ApiResponse response = this.hotelService.delete(id);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	

}
