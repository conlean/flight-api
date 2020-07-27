package com.conlean.challenge.service;

import com.conlean.challenge.model.Flight;
import com.conlean.challenge.repository.FlightBookedRepository;
import com.conlean.challenge.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    public static final BigDecimal DISCONT = new BigDecimal(5);
    public static final BigDecimal KG_TO_GET_DISCONT = new BigDecimal(10000);


    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    private final FlightRepository flightRepository;
    private final FlightBookedRepository flightBookedRepository;


    public FlightService(FlightRepository flightRepository, FlightBookedRepository flightBookedRepository) {
        this.flightRepository = flightRepository;
        this.flightBookedRepository = flightBookedRepository;
    }

    /**
     *
     * Get all flights
     *
     * @return
     */
    public Iterable<Flight> findAll() {
        return flightRepository.findAll();
    }

    /**
     *
     * Save Flight
     *
     * @param flight
     * @return
     */
    public Flight save (Flight flight) {
        return flightRepository.save(flight);
    }

    /**
     * Get cheaper and faster flight
     *
     * @param origin
     * @param destination
     * @param weight
     * @return
     */
    public List<Flight> getFastAndCheapFlight(String origin, String destination, BigDecimal weight) {

        List<Flight> flights = new ArrayList<>();

        Optional<Flight> cheapFlight = getCheapest(origin, destination, weight);
        Optional<Flight> fastFlight = getFastest(origin, destination, weight);

        if (cheapFlight.isPresent())
            flights.add(cheapFlight.get());
        if (fastFlight.isPresent())
            flights.add(fastFlight.get());

        return flights;
    }


    /**
     * Get the Cheapest flight in DB and
     * apply a discount if is required
     *
     * @param origin
     * @param destination
     * @param weight
     * @return
     */
    public Optional<Flight> getCheapest(String origin, String destination, BigDecimal weight) {

        logger.info(String.format("getting the cheapest flight from %s to  %s ", origin, destination));
        Optional<Flight> bestFlight = Optional.ofNullable(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc(origin, destination));
        if (bestFlight.isPresent())
            findDiscont(bestFlight.get(), weight);
        return bestFlight;
    }

    /**
     * Get the fastest flight in DB and
     * apply a discount if is required
     *
     * @param origin
     * @param destination
     * @param weight
     * @return
     */
    public Optional<Flight> getFastest(String origin, String destination, BigDecimal weight) {

        logger.info(String.format("getting the fastest flight from %s to  %s ", origin, destination));
        Optional<Flight> bestFlight = Optional.ofNullable(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByFlightTimeAsc(origin, destination));
        if (bestFlight.isPresent())
            findDiscont(bestFlight.get(), weight);
        return bestFlight;
    }

    /**
     * Function to find and apply discont in a flight from all flights booked
     *
     * @param bestFlight
     * @param weight
     */
    private void findDiscont(Flight bestFlight, BigDecimal weight) {

        if (isAirlineDiscont(bestFlight.getAirlineCode(), weight)) {
            applyDiscont(bestFlight);
        }

    }


    /**
     * Function to kwon if the sum of total weight of all the flight booked and
     * the new weight`s flight reaches the discont
     *
     * @param airlineCode
     * @return
     */
    private boolean isAirlineDiscont(String airlineCode, BigDecimal weight) {

        logger.info(String.format("Find if there is a discoint airline code %s ", airlineCode));
        Optional<BigDecimal> totalWeightFlights = Optional.ofNullable(flightBookedRepository.getTotalWeightByAirlineCode(airlineCode));

        if (totalWeightFlights.isPresent()) {
            BigDecimal flightFound = totalWeightFlights.get();
            BigDecimal sum = flightFound.add(weight);
            return KG_TO_GET_DISCONT.compareTo(sum) < 0;

        } else
            return false;
    }


    /**
     * function to apply 5% discount in the fares has
     * booked more than 10000Kg in the current year gets .
     *
     * @param bestFlight
     * @return
     */
    private BigDecimal applyDiscont(Flight bestFlight) {

        logger.info(String.format("Apply %s porcent off  ", DISCONT));
        return bestFlight.setFinalPriceWithDiscount(bestFlight.getPrice().subtract(DISCONT));
    }

}
