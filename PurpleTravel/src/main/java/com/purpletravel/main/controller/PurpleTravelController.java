package com.purpletravel.main.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.purpletravel.main.exception.BusNotAvailableException;
import com.purpletravel.main.exception.SeatNotFoundException;
import com.purpletravel.main.exception.SeatReadyBookedException;
import com.purpletravel.main.model.Passenger;
import com.purpletravel.main.model.PurpleTravelBusDetails;
import com.purpletravel.main.service.PurpleTravelService;

import jakarta.validation.Valid;


@RestController
public class PurpleTravelController {
	
	@Autowired PurpleTravelService purpleTravelService;
	
	@Autowired PurpleTravelService mailService;
	
	@PostMapping("/purpleTravel")
	public ResponseEntity<PurpleTravelBusDetails> postDetails(@Valid @RequestBody PurpleTravelBusDetails purpleBusDetails)
	{
		PurpleTravelBusDetails postedBusDetails = purpleTravelService.postDetails(purpleBusDetails);
		
		return new ResponseEntity<PurpleTravelBusDetails>(postedBusDetails,HttpStatus.CREATED);
	}
	@PutMapping("/purpleTravel/{busNumber}/{seatId}")
	public ResponseEntity<PurpleTravelBusDetails> updateDetails(@PathVariable("busNumber") String bNumber,
			                                                    @PathVariable("seatId") int sId,
			                                                    @RequestBody Passenger passenger) throws BusNotAvailableException, SeatReadyBookedException, SeatNotFoundException
	{
		PurpleTravelBusDetails purpleUpdatedDetails = purpleTravelService.updateDetails(bNumber,sId,passenger);
		
		mailService.sendBookingConfirmationEmail(passenger);
		
		return new ResponseEntity<PurpleTravelBusDetails>(purpleUpdatedDetails,HttpStatus.OK);
	}
	
	@GetMapping("/allPurpleTravels")
	public ResponseEntity<List<PurpleTravelBusDetails>> getAllDetails()
	{
		List<PurpleTravelBusDetails> listOfPurpleTravel = purpleTravelService.getAllDetails();
		return new ResponseEntity<>(listOfPurpleTravel,HttpStatus.OK);
	}
	
	@GetMapping("/purpleTravel/{busNumber}")
	public ResponseEntity<PurpleTravelBusDetails> getPurpleTravelByNumber(@PathVariable("busNumber") String bNumber) throws BusNotAvailableException
	{
		PurpleTravelBusDetails pTravelByNumber = purpleTravelService.getPurpleTravelByName(bNumber);
		return new ResponseEntity<>(pTravelByNumber,HttpStatus.OK);
	}
	
	@GetMapping("/travel/{fromLocation}/{toLocation}")
	public ResponseEntity<PurpleTravelBusDetails> getPurpleTraveByLocationToLocation(@PathVariable("fromLocation") String fromLocation,
			                                                                         @PathVariable("toLocation") String toLocation) throws BusNotAvailableException
	{
		PurpleTravelBusDetails purpleLocation = purpleTravelService.getPurpleTraveByLocationToLocation(fromLocation,toLocation);
		return new ResponseEntity<PurpleTravelBusDetails>(purpleLocation,HttpStatus.OK);
	}

}
