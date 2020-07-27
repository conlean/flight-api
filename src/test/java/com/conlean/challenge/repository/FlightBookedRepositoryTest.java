package com.conlean.challenge.repository;

import com.conlean.challenge.model.FlightBooked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightBookedRepositoryTest {

    @Autowired
    private FlightBookedRepository flightBookedRepository;

    @Test
    public void whenFindFlightBookedByAirLineCode_thenGetAvg() {

        List <FlightBooked> flightsBooked = new ArrayList<>();
        FlightBooked flightOne = new FlightBooked("AA", new BigDecimal(5000),new Date());
        FlightBooked flightTwo = new FlightBooked("AA", new BigDecimal(5001),new Date());
        flightsBooked.add(flightOne);
        flightsBooked.add(flightTwo);
        flightBookedRepository.saveAll(flightsBooked);

        assertThat(flightBookedRepository.getTotalWeightByAirlineCode("AA")).isGreaterThanOrEqualTo(new BigDecimal(10000));

    }

}
