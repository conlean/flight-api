package com.conlean.challenge.controller;


import com.conlean.challenge.model.Flight;
import com.conlean.challenge.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {


    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flight")
    public Iterable<Flight> getAllFlight() {
        logger.info("getting all flight");
        return flightService.findAll();
    }

    @PostMapping("/flight")
    public Flight createflight(@RequestBody Flight flight) {

        logger.info(String.format("Saving flight AirlineCode: %s ", flight.getAirlineCode()));
        return flightService.save(flight);
    }

    @GetMapping("/flight/search")
    public List<Flight> getFastAndCheapFlight(@RequestParam("origin") String origin,
                                                        @RequestParam("destination") String destination,
                                                        @RequestParam("weight") BigDecimal weight) {

        logger.info(String.format("Finding Flight by origin: %s , destination %s and weight %s", origin, destination, weight));
        return flightService.getFastAndCheapFlight(origin, destination, weight);

    }



}
