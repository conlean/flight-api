package com.conlean.challenge.repository;


import com.conlean.challenge.model.Flight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;


    @Before
    public void setup() {

        Flight flightCheaper = new Flight("AA", 1l, "AR", "BR", 60, new BigDecimal(1000));
        Flight flightExpensive = new Flight("TURKISH", 2l, "AR", "BR", 50, new BigDecimal(1500));
        Flight flightNotInTheSearch = new Flight("TURKISH", 3l, "AR", "CL", 52, new BigDecimal(1550));
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flightCheaper);
        flightList.add(flightExpensive);
        flightList.add(flightNotInTheSearch);
        flightRepository.saveAll(flightList);
    }

    @Test
    public void whenFindingFlightById_thenCorrect() {
        assertThat(flightRepository.findById(1L)).isInstanceOf(Optional.class);

    }

    @Test
    public void findfromFlightsTheCheapest_thenGetCheaper() {
        assertThat(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc("AR", "BR").getFlightNumber()).isEqualTo(1l);

    }

    @Test
    public void findfromFlightsTheFastest_thenGetFastest() {
        assertThat(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByFlightTimeAsc("AR", "BR").getFlightNumber()).isEqualTo(2l);

    }
    @Test
    public void findfromFlightsTheFastest_thenGetNull() {
        assertThat(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByFlightTimeAsc("AR", "MX")).isNull();

    }




}
