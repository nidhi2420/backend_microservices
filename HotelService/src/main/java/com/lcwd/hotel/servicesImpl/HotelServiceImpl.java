package com.lcwd.hotel.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.payloads.ApiResponse;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		return this.hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> hotels = this.hotelRepository.findAll();
		return hotels;
	}

	@Override
	public Hotel getHotelById(Integer hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel","id",hotelId));
		return hotel;
	}

	@Override
	public ApiResponse delete(Integer hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel","id",hotelId));
		this.hotelRepository.deleteById(hotelId);
		ApiResponse apiResponse = ApiResponse.builder().message("deleted successfully").status(true).build();
		return apiResponse;
	}
	
	

}
