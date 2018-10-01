package com.mvppoa.adidas.service;

import com.mvppoa.adidas.domain.dto.ItineraryDTO;

import java.time.Instant;
import java.util.List;

public interface ItineraryService {

    ItineraryDTO createRelationship(Long originId, Long destinyId, Instant departureTime, Instant arrivalTime);

    List<Object> getAllConnectionsFromOriginCity(String originCity);

}
