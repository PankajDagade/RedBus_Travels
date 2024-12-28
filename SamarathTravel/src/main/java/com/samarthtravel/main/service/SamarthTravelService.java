package com.samarthtravel.main.service;

import java.util.List;


import com.samarthtravel.main.exception.BusIsNotAvailableException;
import com.samarthtravel.main.model.Passenger;
import com.samarthtravel.main.model.SamarthTravel;

public interface SamarthTravelService {

	public SamarthTravel postSamarthTravelDetails(SamarthTravel samarthTravel);

	public List<SamarthTravel> getAllSamarthTravels();

	public SamarthTravel addPassenger(Passenger passenger, String busNumber, int seatId) throws Exception;

	public void sendBookingConfirmationEmail(Passenger passenger);

	public SamarthTravel getSamarthTravelByName(String busNumber) throws BusIsNotAvailableException;

	public SamarthTravel travelFromLocationToLocation(String fLocation, String tLocation) throws BusIsNotAvailableException;

}		
