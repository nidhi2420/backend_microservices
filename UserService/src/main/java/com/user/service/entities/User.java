package com.user.service.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", length = 10)
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ABOUT")
	private String about;
	
	//to store the rating and since we dont want to save it in daabase so 
	@jakarta.persistence.Transient
	private List<Rating> ratings = new ArrayList<>();

}
