package com.mvppoa.adidas.service.impl;

import com.mvppoa.adidas.domain.City;
import com.mvppoa.adidas.domain.Itinerary;
import com.mvppoa.adidas.domain.dto.ItineraryDTO;
import com.mvppoa.adidas.exceptions.CustomParameterizedException;
import com.mvppoa.adidas.mapper.ItineraryMapper;
import com.mvppoa.adidas.repository.CityRepository;
import com.mvppoa.adidas.repository.ItineraryRepository;
import com.mvppoa.adidas.service.ItineraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItineraryServiceImpl implements ItineraryService {

    private final CityRepository cityRepository;
    private final ItineraryRepository itineraryRepository;
    private final ItineraryMapper itineraryMapper;

    private static final String SEARCH_FAILURE_EXCEPTION = "SearchFailureException - ";

    public ItineraryServiceImpl(CityRepository cityRepository, ItineraryRepository itineraryRepository, ItineraryMapper itineraryMapper) {
        this.cityRepository = cityRepository;
        this.itineraryRepository = itineraryRepository;
        this.itineraryMapper = itineraryMapper;
    }

    @Override
    public ItineraryDTO createRelationship(Long originId, Long destinyId, Instant departureTime, Instant arrivalTime) {

        Optional<City> originCity = cityRepository.findById(originId);

        if (!originCity.isPresent()) {
            throw new CustomParameterizedException(SEARCH_FAILURE_EXCEPTION + "The given id ('" + originCity + "') for the origin city could not be found");
        }

        Optional<City> destinyCity = cityRepository.findById(destinyId);
        if (!destinyCity.isPresent()) {
            throw new CustomParameterizedException(SEARCH_FAILURE_EXCEPTION + "The given id ('" + destinyId + "') for the destiny city could not be found");
        }

        if (departureTime.toEpochMilli() >= arrivalTime.toEpochMilli()) {
            throw new CustomParameterizedException("InvalidDateException - The arrival date " + departureTime + " is equal or greater than the departure date " + arrivalTime);
        }

        Itinerary itinerary = new Itinerary();

        itinerary.setCity(originCity.get());
        itinerary.setDestinyCity(destinyCity.get());

        itinerary.setDepartureTime(departureTime.toEpochMilli());
        itinerary.setArrivalTime(arrivalTime.toEpochMilli());

        itineraryRepository.save(itinerary);

        return itineraryMapper.toDto(itinerary);

    }

    @Override
    public List<Object> getAllConnectionsFromOriginCity(String originCity) {

        log.debug("Service fetch all connections from city {}", originCity);

        return cityRepository.getAllConnectionsBetweenCities(originCity);
    }

}
