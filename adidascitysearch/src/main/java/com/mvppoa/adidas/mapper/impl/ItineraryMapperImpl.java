package com.mvppoa.adidas.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvppoa.adidas.domain.Itinerary;
import com.mvppoa.adidas.domain.dto.ItineraryDTO;
import com.mvppoa.adidas.mapper.ItineraryMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

@Component
public class ItineraryMapperImpl implements ItineraryMapper {

    private final ObjectMapper objectMapper;

    public ItineraryMapperImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ItineraryDTO toDto(Itinerary input) {
        ItineraryDTO itineraryDTO = new ItineraryDTO();
        itineraryDTO.setNodeId(input.getNodeId());
        itineraryDTO.setCity(input.getCity());
        itineraryDTO.setDestinyCity(input.getDestinyCity());
        if(input.getDepartureTime()!= null) {
            itineraryDTO.setDepartureTime(Instant.ofEpochMilli(input.getDepartureTime()).atZone(ZoneId.systemDefault()));
        }
        if(input.getArrivalTime()!= null) {
            itineraryDTO.setArrivalTime(Instant.ofEpochMilli(input.getArrivalTime()).atZone(ZoneId.systemDefault()));
        }
        return itineraryDTO;
    }

    @Override
    public Itinerary fromDto(ItineraryDTO input) {
        Itinerary itinerary = new Itinerary();
        itinerary.setNodeId(input.getNodeId());
        itinerary.setCity(input.getCity());
        itinerary.setDestinyCity(input.getDestinyCity());
        if(input.getDepartureTime()!= null) {
            itinerary.setDepartureTime(input.getDepartureTime().toInstant().toEpochMilli());
        }
        if(input.getArrivalTime()!= null) {
            itinerary.setArrivalTime(input.getArrivalTime().toInstant().toEpochMilli());
        }
        return itinerary;
    }
}
