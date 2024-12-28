package com.purpletravel.main.serviceImpl;

import java.util.LinkedHashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.purpletravel.main.exception.BusNotAvailableException;

import com.purpletravel.main.exception.SeatNotFoundException;
import com.purpletravel.main.exception.SeatReadyBookedException;
import com.purpletravel.main.model.Passenger;
import com.purpletravel.main.model.PurpleTravelBusDetails;
import com.purpletravel.main.model.Seat;
import com.purpletravel.main.repository.PurpleTravelRepository;
import com.purpletravel.main.service.PurpleTravelService;

@Service
public class PurpleTravelServiceImpl implements PurpleTravelService {
	
	@Autowired PurpleTravelRepository purpleTravelRepository;
	
    @Autowired JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	

	@Override
	public PurpleTravelBusDetails postDetails(PurpleTravelBusDetails purpleBusDetails) {
		
		Set<Seat> seats = new LinkedHashSet<>();
		
		for(int i=1 ; i<=purpleBusDetails.getBusSeatingCapacity() ; i++)
		{
			Seat seat = new Seat();
			if(i>=20)
			{
				seat.setSeatNumber("U"+i);
				seat.setSeatStatus("Available");
			}
			else
			{
				seat.setSeatNumber("L"+i);
				seat.setSeatStatus("Available");
			}
			seats.add(seat);
		}
		purpleBusDetails.setSeats(seats);
		
		return purpleTravelRepository.save(purpleBusDetails);
		
	}

	@Override
	public PurpleTravelBusDetails updateDetails(String bNumber, int sId,
			Passenger passenger) throws BusNotAvailableException, SeatReadyBookedException, SeatNotFoundException {
		
		Optional<PurpleTravelBusDetails> opc = purpleTravelRepository.findByBusNumber(bNumber);
		
		if(opc.isPresent())
		{
			PurpleTravelBusDetails orginalTravel = opc.get();
			Set<Seat> seats = orginalTravel.getSeats();
			Boolean seatFound=false;
			for(Seat seat : seats)
			{
				seatFound=true;
				if(seat.getSeatId()==sId)
				{
					if(! seat.getSeatStatus().equals("Booked"))
					{
						seat.setSeatStatus("Booked");
						seat.setPassenger(passenger);
					}
					else
					{
						throw new SeatReadyBookedException("Seat is Already Booked.");
					}
					
				}
				if(seatFound==false)
				{
				     throw new SeatNotFoundException("Seat is not found.");
				}
			}
			return purpleTravelRepository.save(orginalTravel);
		}
		else
		{
			throw new BusNotAvailableException("Bus is not Available");
		}
		
	}
	
	@Override
	public List<PurpleTravelBusDetails> getAllDetails() {
		
        List<PurpleTravelBusDetails> allPurpleTravels = (List) purpleTravelRepository.findAll();
		
		return allPurpleTravels;
	}

	@Override
	public PurpleTravelBusDetails getPurpleTravelByName(String bNumber) throws BusNotAvailableException {
		Optional<PurpleTravelBusDetails> opc = purpleTravelRepository.findByBusNumber(bNumber);
		
		if(opc.isPresent())
		{
			PurpleTravelBusDetails orginalPurpleTravelByName =  opc.get();
			
			return purpleTravelRepository.save(orginalPurpleTravelByName);
		}
		else
		{
			throw new BusNotAvailableException("Bus is not found");
		}
	}

	@Override
	public PurpleTravelBusDetails getPurpleTraveByLocationToLocation(String fromLocation, String toLocation) throws BusNotAvailableException {
		Optional<PurpleTravelBusDetails> opc = purpleTravelRepository.findByFromLocationAndToLocation(fromLocation,toLocation);
		
		if(opc.isPresent())
		{
			PurpleTravelBusDetails pTravelBusLocation = opc.get();
			
			return purpleTravelRepository.save(pTravelBusLocation);	
		}
		else
		{
			throw new BusNotAvailableException("Bus is not Available");
		}
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
				         " PurpleTarvel Details\n\n"+
		                 "Best Regard\n"+
				         "\n\n"+passenger.getPassengerName());
		
		javaMailSender.send(mail);;
		
		
		
	}

	
	

	
	
}
