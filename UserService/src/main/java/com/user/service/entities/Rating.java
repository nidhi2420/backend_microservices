package com.user.service.entities;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmOneToManyCollectionElementType;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Rating {

	private Integer ratingId;
	private Integer userId;
	private String hotelId;
	private int rating;
	private String feedback;
	
	private Hotel hotel;
}
