package com.conlean.challenge.repository;

import com.conlean.challenge.model.FlightBooked;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FlightBookedRepository extends CrudRepository<FlightBooked, Long> {

    @Query("SELECT SUM(f.weight) from FlightBooked f WHERE f.airlineCode = ?1")
    BigDecimal getTotalWeightByAirlineCode(String airlineCode);

}
