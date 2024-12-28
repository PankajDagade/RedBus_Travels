package com.samarthtravel.main.model;

import java.time.LocalTime;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SamarthTravel {
	
	  @Id
	    @NotEmpty(message = "Bus number cannot be empty")
	    @Size(min = 3, max = 20, message = "Bus number must be between 1 and 4 digits")
	    private String busNumber;

	    @NotEmpty(message = "Bus agency name cannot be empty")
	    private String busAgencyName;

	    @NotEmpty(message = "Bus owner name cannot be empty")
	    private String busOwnerName;

	    @NotEmpty(message = "Bus driver name cannot be empty")
	    private String busDriverName;

	    @NotEmpty(message = "Bus co-driver name cannot be empty")
	    private String busCoDriverName;

	    @Min(value = 1, message = "Seating capacity must be at least 1")
	    private int busSeatingCapacity;

	    @Min(value = 0, message = "Ticket price cannot be negative")
	    private float busTicketPrice;

	    @NotEmpty(message = "From location cannot be empty")
	    private String fromLocation;

	    @NotEmpty(message = "To location cannot be empty")
	    private String toLocation;

	    private LocalTime onBoardingTime;

	    private LocalTime dropTime;

	    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<Seat> seats;

}
