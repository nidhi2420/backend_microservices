package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.payloads.ApiResponse;

public interface HotelService {

	Hotel createHotel(Hotel hotel);

	List<Hotel> getAllHotels();

	Hotel getHotelById(Integer hotelId);

	ApiResponse delete(Integer hotelId);

}
