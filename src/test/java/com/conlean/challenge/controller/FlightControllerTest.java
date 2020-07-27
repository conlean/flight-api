package com.conlean.challenge.controller;

import com.conlean.challenge.model.Flight;
import com.conlean.challenge.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FlightControllerTest {

    private FlightController flightController;

    @MockBean
    private FlightService flightService;

    @Autowired
    private MockMvc mvc;

    private List<Flight> flightsMocks;

    @Before
    public void setUp() {
        flightController = new FlightController(flightService);
        this.flightsMocks = new ArrayList<>();
        this.flightsMocks.add(new Flight("AA", 1l, "AR", "BR", 60, new BigDecimal(1000)));
        this.flightsMocks.add(new Flight("TURKISH", 2l, "AR", "BR", 50, new BigDecimal(1500)));

    }


    @Test
    public void FindBestTwoFlights_thenReturn200() throws Exception {

        when(flightService.getFastAndCheapFlight("AR", "BR", new BigDecimal(1000))).thenReturn(this.flightsMocks);

        // when
        mvc.perform(get("/api/flight/search?origin=AR&destination=BR&weight=1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    public void FindBestTwoFlights_thenReturnEmtpyList() throws Exception {

        mvc.perform(get("/api/flight/search?origin=AR&destination=BR&weight=1000.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    public void saveFlight_theReturn200() throws Exception {

        Flight flightMocked = new Flight("AA", 1l, "AR", "BR", 60, new BigDecimal(1000));
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post("/api/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(flightMocked)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
