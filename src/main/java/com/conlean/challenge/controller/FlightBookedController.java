package com.conlean.challenge.controller;

import com.conlean.challenge.model.FlightBooked;
import com.conlean.challenge.repository.FlightBookedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FlightBookedController {

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    private final FlightBookedRepository flightBookedRepository;


    public FlightBookedController(FlightBookedRepository flightBookedRepository) {
        this.flightBookedRepository = flightBookedRepository;
    }


    @GetMapping("/flight/booked")
    public Iterable<FlightBooked> getAllFlight() {
        logger.info("getting all flight booked");
        return flightBookedRepository.findAll();
    }


    @PostMapping("/flight/booked")
    public FlightBooked createflightBooked(@RequestBody FlightBooked flightBooked) {
        logger.info(String.format("Saving flight booked AirlineCode: %s ", flightBooked.getAirlineCode()));
        return flightBookedRepository.save(flightBooked);
    }

}
