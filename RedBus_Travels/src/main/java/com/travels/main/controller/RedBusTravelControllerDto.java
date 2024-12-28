package com.travels.main.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.travels.main.model.RedBusTravelDto;
import com.travels.main.service.RedBusTravelServiceDto;

@RestController
public class RedBusTravelControllerDto {
	
	@Autowired RedBusTravelServiceDto redBusTravelService;
	
	@GetMapping("/viewAllTravels")
	public ResponseEntity<List<RedBusTravelDto>> viewAllTravels()
	{
		List<RedBusTravelDto> viewAllTravels1 = redBusTravelService.viewAllTravels();
		return new ResponseEntity<>(viewAllTravels1,HttpStatus.OK);
	}
	@GetMapping("/viewAllTravel/{fromLocation}/{toLocation}")
	public ResponseEntity<List<RedBusTravelDto>> viewAllTravelsLocation(@PathVariable String fromLocation,
			                                                            @PathVariable String toLocation)
	{
		List<RedBusTravelDto> listOfTravels = redBusTravelService.viewAllTravelsLocation(fromLocation,toLocation);
		return new ResponseEntity<>(listOfTravels,HttpStatus.OK);
	}

}
