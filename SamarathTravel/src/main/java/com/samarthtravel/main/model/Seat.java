package com.samarthtravel.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String seatNumber;
	private String seatStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Passenger passenger;

}
