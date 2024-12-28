package com.purpletravel.main.service;

import java.util.List;
import java.util.Optional;
import com.purpletravel.main.exception.BusNotAvailableException;
import com.purpletravel.main.exception.SeatNotFoundException;
import com.purpletravel.main.exception.SeatReadyBookedException;
import com.purpletravel.main.model.Passenger;
import com.purpletravel.main.model.PurpleTravelBusDetails;

public interface PurpleTravelService {

	public PurpleTravelBusDetails postDetails(PurpleTravelBusDetails purpleBusDetails);

	public PurpleTravelBusDetails updateDetails(String bNumber, int sId, Passenger passenger) throws BusNotAvailableException, SeatNotFoundException, SeatReadyBookedException;

	public List<PurpleTravelBusDetails> getAllDetails();

	public PurpleTravelBusDetails getPurpleTravelByName(String bNumber) throws BusNotAvailableException;

	public PurpleTravelBusDetails getPurpleTraveByLocationToLocation(String fromLocation, String toLocation) throws BusNotAvailableException;

	public void sendBookingConfirmationEmail(Passenger passenger);

	
}
