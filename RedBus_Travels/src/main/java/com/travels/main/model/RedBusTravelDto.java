package com.travels.main.model;


import java.time.LocalTime;
import java.util.Set;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RedBusTravelDto {

    
	private String busNumber;
    private String busAgencyName;
    private String busOwnerName;
    private String busDriverName;
    private String busCoDriverName;
    private int busSeatingCapacity;
    private float busTicketPrice;
    private String fromLocation;
    private String toLocation;
    private LocalTime onBoardingTime;
    private LocalTime dropTime;
}
