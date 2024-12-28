package com.samarthtravel.main.serviceImpl;

import java.util.*;



import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.samarthtravel.main.exception.BusIsNotAvailableException;
import com.samarthtravel.main.exception.SeatAllReadyBookedException;
import com.samarthtravel.main.exception.SeatNotAvailableException;
import com.samarthtravel.main.model.Passenger;
import com.samarthtravel.main.model.SamarthTravel;
import com.samarthtravel.main.model.Seat;
import com.samarthtravel.main.repository.SamarthTravelRepository;
import com.samarthtravel.main.service.SamarthTravelService;

@Service
public class SamarthTravelServiceImpl implements SamarthTravelService {
	
	@Autowired SamarthTravelRepository samarthTravelRepository;
	
	@Autowired JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public SamarthTravel postSamarthTravelDetails(SamarthTravel samarthTravel) {
		Set<Seat> listOfSeat = new LinkedHashSet<Seat>();
		for(int i=1 ; i<=samarthTravel.getBusSeatingCapacity(); i++)
		{
			Seat seats = new Seat();
			if(i>=20)
			{
				seats.setSeatNumber("U"+i);
				seats.setSeatStatus("Available");
			}else {
				seats.setSeatNumber("L"+i);
				seats.setSeatStatus("Available");
			}
			listOfSeat.add(seats);
		}
		
		samarthTravel.setSeats(listOfSeat);
		
		return samarthTravelRepository.save(samarthTravel);
	}

	
	@Override
	public SamarthTravel addPassenger(Passenger passenger,String busNumber, int seatId) throws Exception {

		Optional<SamarthTravel> optional = samarthTravelRepository.findByBusNumber(busNumber);
		
		System.out.println(seatId);
		if(optional.isPresent())
		{
			SamarthTravel travel = optional.get();
			Set<Seat> seats = travel.getSeats();
			System.out.println(travel.getSeats());
			boolean seatFound = false;
			
			for(Seat seat : seats)
			{
				System.out.println("Available Seat ID: " + seat.getSeatId());
				if(seat.getSeatId()==seatId)
				{
					System.out.println();
					 seatFound=true;
					 if(!seat.getSeatStatus().equals("Booked"))
					{
						 seat.setSeatStatus("Booked");
		                 seat.setPassenger(passenger);
					}
					else
					{
						throw new SeatAllReadyBookedException("Seat is already booked!!!!");
					}				
				}
			}
			if(seatFound==false)
			{
				throw new SeatNotAvailableException("Seat is not Available!!!!");
			}
			return samarthTravelRepository.save(travel);
		}
		else
		{
			throw new BusIsNotAvailableException("Bus is not Available !!!");
		}
		
		
	}
	
	@Override
	public List<SamarthTravel> getAllSamarthTravels() {
		
		List<SamarthTravel> listOfTravels = samarthTravelRepository.findAll();
		return listOfTravels;
	}


	@Override
	public void sendBookingConfirmationEmail(Passenger passenger) {
		
		System.out.println(passenger.getEmail());
		
		if(passenger==null || passenger.getEmail()==null)
		{ 
			throw new IllegalIdentifierException("Passenger email is null or empty");
		}
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(fromEmail);
		mail.setTo(passenger.getEmail());
		mail.setSubject("Seat Booking Confirmation");
		mail.setText("Dear "+passenger.getPassengerName()+"\n\n"+
		                "Your seat Booking has been confirmed\n\n"+
				         "SamarthTarvel Details\n\n"+
		                 "Best Regard\n"+
				         "\n\n"+passenger.getPassengerName());
		
		javaMailSender.send(mail);;
		
		
		
	}


	@Override
	public SamarthTravel getSamarthTravelByName(String busNumber) throws BusIsNotAvailableException {
		
		Optional<SamarthTravel> opc = samarthTravelRepository.findByBusNumber(busNumber);
		
		if(opc.isPresent())
		{
			SamarthTravel orginalBusNumber = opc.get();
			
			return samarthTravelRepository.save(orginalBusNumber);
		}
		else
		{
			throw new BusIsNotAvailableException("Bus is not Available.");
		}
		
		
	}


	@Override
	public SamarthTravel travelFromLocationToLocation(String fLocation, String tLocation) throws BusIsNotAvailableException {
		
		Optional<SamarthTravel> opc = samarthTravelRepository.findByFromLocationAndToLocation(fLocation,tLocation);
		
		if(opc.isPresent())
		{
			SamarthTravel samarthTravel = opc.get();
			
			return samarthTravelRepository.save(samarthTravel);

		}
		else
		{
			throw new BusIsNotAvailableException("Bus is not Available.");
		}
	}
}
