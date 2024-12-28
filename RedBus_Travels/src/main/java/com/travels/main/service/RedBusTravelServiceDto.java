package com.travels.main.service;

import java.util.List;

import com.travels.main.model.RedBusTravelDto;

public interface RedBusTravelServiceDto {

	public List<RedBusTravelDto> viewAllTravels();

	public List<RedBusTravelDto> viewAllTravelsLocation(String fromLocation, String toLocation);

}
