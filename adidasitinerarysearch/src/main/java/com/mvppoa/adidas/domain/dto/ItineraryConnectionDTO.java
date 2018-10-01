package com.mvppoa.adidas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItineraryConnectionDTO {

    String originCity;
    String destinyCity;

    ZonedDateTime departureTime;
    ZonedDateTime arrivalTime;

    public void setDepartureTime(Long departureTime) {
        this.departureTime = Instant.ofEpochMilli(departureTime).atZone(ZoneId.systemDefault());
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = Instant.ofEpochMilli(arrivalTime).atZone(ZoneId.systemDefault());
    }
}
