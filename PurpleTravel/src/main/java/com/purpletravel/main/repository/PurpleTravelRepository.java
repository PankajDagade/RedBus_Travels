package com.purpletravel.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purpletravel.main.model.PurpleTravelBusDetails;

@Repository
public interface PurpleTravelRepository extends JpaRepository<PurpleTravelBusDetails, String>{

	Optional<PurpleTravelBusDetails> findByBusNumber(String bNumber);

	Optional<PurpleTravelBusDetails> findByFromLocationAndToLocation(String fromLocation, String toLocation);

	
}
