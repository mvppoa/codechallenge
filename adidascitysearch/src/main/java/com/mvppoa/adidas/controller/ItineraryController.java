package com.mvppoa.adidas.controller;

import com.mvppoa.adidas.domain.dto.ItineraryDTO;
import com.mvppoa.adidas.domain.dto.ItineraryJoinDTO;
import com.mvppoa.adidas.service.ItineraryService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing itineraries.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @ApiOperation(value = "Make a join between two cities using originId, destinyId, departureTime, arrivalTime", response = ItineraryDTO.class)

    @ApiImplicitParams({
        @ApiImplicitParam(
            name = "itineraryJoinDTO",
            dataType = "ItineraryJoinDTO",
            value = "{\n" +
                "  \"arrivalTime\": \"2018-09-30T22:47:22-03:00\",\n" +
                "  \"cityId\": 1,\n" +
                "  \"departureTime\": \"2018-09-29T22:47:22-03:00\",\n" +
                "  \"destinyCityId\": 0\n" +
                "}",
            examples = @io.swagger.annotations.Example(
                value = {
                    @ExampleProperty(value = "{\n" +
                        "  \"arrivalTime\": \"2018-09-30T22:47:22-03:00\",\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"departureTime\": \"2018-09-29T22:47:22-03:00\",\n" +
                        "  \"destinyCityId\": 0\n" +
                        "}", mediaType = "application/json")
                }))
    })

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with body the shortest path to a city represented by ConnectionDTO"),
        @ApiResponse(code = 400, message = "ConnectionDTO is not valid"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem with the search"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "/itinerary/join",
        produces = "application/json",
        consumes = "application/json")
    public ResponseEntity<ItineraryDTO> itineraryJoin(
        @RequestBody ItineraryJoinDTO itineraryJoinDTO) {

        log.debug("REST create connection between cities with id {} and {}", itineraryJoinDTO.getCityId(), itineraryJoinDTO.getDestinyCityId());

        ItineraryDTO result = itineraryService.createRelationship(itineraryJoinDTO.getCityId(), itineraryJoinDTO.getDestinyCityId(), itineraryJoinDTO.getDepartureTime().toInstant(), itineraryJoinDTO.getArrivalTime().toInstant());

        return ResponseEntity.ok()
            .body(result);
    }

    @ApiOperation(value = "Get all the connections originated from a city.",
        notes = "This method will the departureTime and arrivalTime in epoch milliseconds. They can be converted" +
            " to a zoned datetime afterwards.", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with all the connections between of the first city"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem with the search"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/itinerary/connections/{originCity}",
        produces = "application/json")
    public ResponseEntity<List<Object>> getAllConnectionsFromOriginCity(
        @PathVariable String originCity) {

        log.debug("REST fetch all connections from city", originCity);
        List<Object> result = itineraryService.getAllConnectionsFromOriginCity(originCity.toLowerCase().replaceAll("\\s", ""));
        return ResponseEntity.ok()
            .body(result);
    }
}
