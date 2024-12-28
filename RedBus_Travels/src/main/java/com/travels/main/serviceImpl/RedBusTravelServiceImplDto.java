package com.travels.main.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.travels.main.model.RedBusTravelDto;
import com.travels.main.service.RedBusTravelServiceDto;


@Service
public class RedBusTravelServiceImplDto implements RedBusTravelServiceDto {
	
	@Autowired private RestTemplate restTemplate;

	@Override
	public List<RedBusTravelDto> viewAllTravels() {
		
		String purpleTravelUrl = "http://zuul/purple/allPurpleTravels";
		String samarthTravelUrl = "http://zuul/sam/allSamarthTravels";
		
		List<RedBusTravelDto> listOfPurple = restTemplate.getForObject(purpleTravelUrl, List.class);
		
		List<RedBusTravelDto> listOfSamarth = restTemplate.getForObject(samarthTravelUrl, List.class);
		
		
		List<RedBusTravelDto> mergedDetails = RedBusTravelDtoMapper(listOfPurple,listOfSamarth);
		
		return mergedDetails;
	}

	private List<RedBusTravelDto> RedBusTravelDtoMapper(List<RedBusTravelDto> listOfPurple, List<RedBusTravelDto> listOfSamarth) {
		
		List<RedBusTravelDto> mergedDetails = new ArrayList<>();
		
		if(listOfPurple!=null)
		{
			mergedDetails.addAll(listOfPurple);
		}
		
		if(listOfSamarth!=null)
		{
			mergedDetails.addAll(listOfSamarth);
		}
		
		return mergedDetails;
		
	}

	@Override
	public List<RedBusTravelDto> viewAllTravelsLocation(String fromLocation, String toLocation) {
		
		String samarathUrl = "http://zuul/sam/samarth/" + fromLocation + "/" + toLocation;
		String purpleUrl   = "http://zuul/purple/travel/" + fromLocation+ "/" + toLocation;
		
		RedBusTravelDto[] redBusTravelSamarath = restTemplate.getForObject(samarathUrl, RedBusTravelDto[].class);
		RedBusTravelDto[] redBusTravelDtoPurple = restTemplate.getForObject(purpleUrl, RedBusTravelDto[].class);
		
		List<RedBusTravelDto> listOfTravels = new ArrayList<>();
		
		for(RedBusTravelDto busTravels1 : redBusTravelSamarath)
		{
			listOfTravels.add(busTravels1);
		}
		for(RedBusTravelDto busTravel2 : redBusTravelDtoPurple)
		{
			listOfTravels.add(busTravel2);
			
		}
		listOfTravels.sort(Comparator.comparingDouble(RedBusTravelDto::getBusTicketPrice));
		
		return listOfTravels;
	}
	

}
