package com.purpletravel.main.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String seatNumber;
	private String seatStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Passenger passenger;

}
