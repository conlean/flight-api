package com.conlean.challenge.repository;

import com.conlean.challenge.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    Flight findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc(String origin, String destination);

    Flight findFirstByOriginAirportAndDestinationAirportOrderByFlightTimeAsc(String origin, String destination);

}
