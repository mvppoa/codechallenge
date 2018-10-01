package com.mvppoa.adidas.domain.dto;

import com.mvppoa.adidas.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItineraryDTO implements Serializable {

    private Long nodeId;

    private City city;
    private City destinyCity;

    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

}
