package com.samarthtravel.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samarthtravel.main.model.Passenger;
import com.samarthtravel.main.model.SamarthTravel;

@Repository
public interface SamarthTravelRepository extends JpaRepository<SamarthTravel, String>{

	public Optional<SamarthTravel> findByBusNumber(String busNumber);


	public Optional<SamarthTravel> findByFromLocationAndToLocation(String fLocation, String tLocation);

}
