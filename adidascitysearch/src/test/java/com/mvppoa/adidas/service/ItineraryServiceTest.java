package com.mvppoa.adidas.service;

import com.mvppoa.adidas.config.JacksonConfiguration;
import com.mvppoa.adidas.domain.City;
import com.mvppoa.adidas.domain.Itinerary;
import com.mvppoa.adidas.domain.dto.ItineraryDTO;
import com.mvppoa.adidas.exceptions.CustomParameterizedException;
import com.mvppoa.adidas.mapper.ItineraryMapper;
import com.mvppoa.adidas.mapper.impl.ItineraryMapperImpl;
import com.mvppoa.adidas.repository.CityRepository;
import com.mvppoa.adidas.repository.ItineraryRepository;
import com.mvppoa.adidas.service.impl.ItineraryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ItineraryServiceTest {

    @Mock
    CityRepository cityRepository;

    @Mock
    ItineraryRepository itineraryRepository;

    ItineraryMapper itineraryMapper;

    ItineraryService itineraryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        itineraryMapper = new ItineraryMapperImpl(new JacksonConfiguration().objectMapper());
        itineraryService = new ItineraryServiceImpl(cityRepository, itineraryRepository, itineraryMapper);

    }

    @Test
    public void createRelationshipSuccess() {

        //when

        Long originId = 2L;
        Long destinyId = 3L;

        City origin = getCity(originId);

        City destiny = getCityDestiny(destinyId);

        ZonedDateTime departureTime = ZonedDateTime.now();
        ZonedDateTime arrivalTime = ZonedDateTime.now().plusHours(1);

        Itinerary itinerary = getItinerary(origin, destiny, departureTime, arrivalTime);

        when(cityRepository.findById(originId)).thenReturn(Optional.of(origin));
        when(cityRepository.findById(destinyId)).thenReturn(Optional.of(destiny));
        when(itineraryRepository.save(any(Itinerary.class))).thenReturn(itinerary);

        //do
        ItineraryDTO itineraryDTO = itineraryService.createRelationship(originId, destinyId, departureTime.toInstant(), arrivalTime.toInstant());

        //then
        assertEquals(itineraryDTO.getCity().getId(), originId);
        assertEquals(itineraryDTO.getDestinyCity().getId(), destinyId);
        assertEquals(itineraryDTO.getDepartureTime().toInstant().toEpochMilli(), itinerary.getDepartureTime().longValue());
        assertEquals(itineraryDTO.getArrivalTime().toInstant().toEpochMilli(), itinerary.getArrivalTime().longValue());


    }

    private City getCityDestiny(Long destinyId) {
        City destiny = new City();
        destiny.setId(destinyId);
        destiny.setName("MonteCarlo");
        return destiny;
    }

    @Test(expected = CustomParameterizedException.class)
    public void createRelationshipOriginErrors() {

        //when
        Long originId = 2L;
        City origin = getCity(originId);

        Long destinyId = 3L;
        City destiny = getCityDestiny(destinyId);

        ZonedDateTime departureTime = ZonedDateTime.now();
        ZonedDateTime arrivalTime = ZonedDateTime.now().plusHours(1);

        Itinerary itinerary = getItinerary(origin, destiny, departureTime, arrivalTime);

        when(cityRepository.findById(origin.getId())).thenReturn(Optional.empty());
        when(cityRepository.findById(destiny.getId())).thenReturn(Optional.of(destiny));

        //do
        itineraryService.createRelationship(originId, destinyId, departureTime.toInstant(), arrivalTime.toInstant());

    }

    @Test(expected = CustomParameterizedException.class)
    public void createRelationshipDestinyErrors() {

        //when
        Long originId = 2L;
        City origin = getCity(originId);

        Long destinyId = 3L;
        City destiny = getCityDestiny(destinyId);

        ZonedDateTime departureTime = ZonedDateTime.now();
        ZonedDateTime arrivalTime = ZonedDateTime.now().plusHours(1);

        Itinerary itinerary = getItinerary(origin, destiny, departureTime, arrivalTime);

        when(cityRepository.findById(origin.getId())).thenReturn(Optional.of(origin));
        when(cityRepository.findById(destiny.getId())).thenReturn(Optional.empty());

        //do
        itineraryService.createRelationship(originId, destinyId, departureTime.toInstant(), arrivalTime.toInstant());

    }

    @Test(expected = CustomParameterizedException.class)
    public void createRelationshipDatetimeErrors() {

        //when
        Long originId = 2L;
        City origin = getCity(originId);

        Long destinyId = 3L;
        City destiny = getCityDestiny(destinyId);

        ZonedDateTime departureTime = ZonedDateTime.now().plusHours(1);
        ZonedDateTime arrivalTime = ZonedDateTime.now();

        Itinerary itinerary = getItinerary(origin, destiny, departureTime, arrivalTime);

        when(cityRepository.findById(origin.getId())).thenReturn(Optional.of(origin));
        when(cityRepository.findById(destiny.getId())).thenReturn(Optional.of(destiny));

        //do
        itineraryService.createRelationship(originId, destinyId, departureTime.toInstant(), arrivalTime.toInstant());

    }

    private Itinerary getItinerary(City origin, City destiny, ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
        Itinerary itinerary = new Itinerary();

        itinerary.setNodeId(1L);
        itinerary.setCity(origin);
        itinerary.setDestinyCity(destiny);

        itinerary.setDepartureTime(departureTime.toInstant().toEpochMilli());
        itinerary.setArrivalTime(arrivalTime.toInstant().toEpochMilli());
        return itinerary;
    }

    private City getCity(Long originId) {
        City origin = new City();
        origin.setId(originId);
        origin.setName("NewVegas");
        return origin;
    }
}
