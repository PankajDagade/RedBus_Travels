package com.samarthtravel.main.controller;

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

import com.samarthtravel.main.exception.BusIsNotAvailableException;
import com.samarthtravel.main.model.Passenger;
import com.samarthtravel.main.model.SamarthTravel;

import com.samarthtravel.main.service.SamarthTravelService;

@RestController
//@RequestMapping("/samarth")
public class SamarthController {
	
	@Autowired SamarthTravelService samarthService;
	
	@Autowired SamarthTravelService mailService;
	
	@PostMapping("/samarthtravel")
	public ResponseEntity<SamarthTravel> postSamarthTravelDetails(@RequestBody SamarthTravel samarthTravel)
	{
		SamarthTravel regSamarthDetails = samarthService.postSamarthTravelDetails(samarthTravel);
		return new ResponseEntity<>(regSamarthDetails,HttpStatus.CREATED);
	}
	
	@PutMapping("/travel/{busNumber}/{seatId}")
    public ResponseEntity<SamarthTravel> addPassenger(@RequestBody Passenger passenger, @PathVariable String busNumber, @PathVariable int seatId) throws Exception
    {
		SamarthTravel s = samarthService.addPassenger(passenger,busNumber,seatId);
    	
		mailService.sendBookingConfirmationEmail(passenger);
		
         return new ResponseEntity<>(s, HttpStatus.OK);
    }
	
	@GetMapping("/allSamarthTravels")
	public ResponseEntity<List<SamarthTravel>> getAllSamarthTravels()
	{
		List<SamarthTravel> listOfTravels = samarthService.getAllSamarthTravels();
		return new ResponseEntity<List<SamarthTravel>>(listOfTravels,HttpStatus.OK);
	}
	
	@GetMapping("/samarthTravel/{busNumber}")
	public ResponseEntity<SamarthTravel> getSamarathTravelByName(@PathVariable("busNumber") String busNumber) throws BusIsNotAvailableException
	{
		SamarthTravel getBusNumber = samarthService.getSamarthTravelByName(busNumber);
		return new ResponseEntity<SamarthTravel>(getBusNumber,HttpStatus.OK);
	}
	
	@GetMapping("/samarth/{fromLocation}/{toLocation}")
	public ResponseEntity<SamarthTravel> travelFromLocationToLocation(@PathVariable("fromLocation") String fLocation, @PathVariable("toLocation") String tLocation) throws BusIsNotAvailableException
	{
		SamarthTravel location = samarthService.travelFromLocationToLocation(fLocation,tLocation);
		return new ResponseEntity<>(location,HttpStatus.OK);	
	}
	
	

}
