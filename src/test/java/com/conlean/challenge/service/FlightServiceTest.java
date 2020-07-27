package com.conlean.challenge.service;


import com.conlean.challenge.model.Flight;
import com.conlean.challenge.repository.FlightBookedRepository;
import com.conlean.challenge.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private FlightBookedRepository flightBookedRepository;

    @Before
    public void setup() {
        flightService = new FlightService(flightRepository, flightBookedRepository);
    }

    @Test
    public void findCheaperflight_thenReturn() throws Exception {

        Flight flight = new Flight("AA", 1l, "Argentina", "Colombia", 60, new BigDecimal(1000));
        when(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc("ARGENTINA", "COLOMBIA")).thenReturn(flight);

        Optional<Flight> flightFound = flightService.getCheapest("ARGENTINA", "COLOMBIA", new BigDecimal(1000));
        assertThat(flightFound).isNotNull();

    }


    @Test
    public void findCheaperflight_thenReturnCheaperWithDiscont() throws Exception {

        Flight flight = new Flight("AA", 1l, "AR", "CL", 60, new BigDecimal(1000));
        when(flightBookedRepository.getTotalWeightByAirlineCode("AA")).thenReturn(new BigDecimal(10000));
        when(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc("AR", "CL")).thenReturn(flight);

        Optional<Flight> flightFound = flightService.getCheapest("AR", "CL", new BigDecimal(1000));
        assertThat(flightFound.get().getFinalPriceWithDiscount()).isEqualTo(new BigDecimal(995));

    }

    @Test
    public void findCheaperflight_thenReturnCheaperWithoutDiscont() throws Exception {

        Flight flight = new Flight("AA", 1l, "AR", "CL", 60, new BigDecimal(1000));

        //TOTAL kg Booked 9000
        when(flightBookedRepository.getTotalWeightByAirlineCode("AA")).thenReturn(new BigDecimal(9000));
        when(flightRepository.findFirstByOriginAirportAndDestinationAirportOrderByPriceAsc("AR", "CL")).thenReturn(flight);

        // find a fly but it cant apply discount
        Optional<Flight> flightFound = flightService.getCheapest("AR", "CL", new BigDecimal(999));
        assertThat(flightFound.get().getFinalPriceWithDiscount()).isNull();

    }

}
