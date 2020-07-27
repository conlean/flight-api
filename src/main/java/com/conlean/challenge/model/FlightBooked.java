package com.conlean.challenge.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * Entity with all the transaccion flights
 *
 */

@Entity
@Table(name = "flight_booked")
public class FlightBooked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineCode;

    private BigDecimal weight;

    private Date flightDate;

    public FlightBooked(String airlineCode, BigDecimal weight, Date flightDate) {
        this.airlineCode = airlineCode;
        this.weight = weight;
        this.flightDate = flightDate;
    }

    public FlightBooked() {
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }
}
