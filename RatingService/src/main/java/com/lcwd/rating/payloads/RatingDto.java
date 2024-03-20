package com.lcwd.rating.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

	private Integer id;
	private Integer userId;
	private Integer hotelId;
	private int rating;
	private String feedback;
	
}
