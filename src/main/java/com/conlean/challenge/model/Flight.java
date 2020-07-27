package com.conlean.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity with flights
 *
 *
 */

@Entity
@Table(name = "flight")
public class Flight {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlineCode;

    private Long flightNumber;

    private String originAirport;

    private String destinationAirport;

    private int flightTime;

    private BigDecimal price;

    @Transient
    private BigDecimal finalPriceWithDiscount;


    public Flight() {
    }

    public Flight(String airlineCode, Long flightNumber, String originAirport, String destinationAirport, int flightTime, BigDecimal price) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.flightTime = flightTime;
        this.price = price;
    }

    public Flight(String aa, long l, String ar, String br, long l1, int i, BigDecimal bigDecimal) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public long getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFinalPriceWithDiscount() {
        return finalPriceWithDiscount;
    }

    public BigDecimal setFinalPriceWithDiscount(BigDecimal finalPriceWithDiscount) {
        this.finalPriceWithDiscount = finalPriceWithDiscount;
        return finalPriceWithDiscount;
    }
}
