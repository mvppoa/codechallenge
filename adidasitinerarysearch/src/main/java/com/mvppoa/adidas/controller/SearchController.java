package com.mvppoa.adidas.controller;

import com.mvppoa.adidas.domain.dto.CityConnectionDTO;
import com.mvppoa.adidas.service.SearchService;
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
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @ApiOperation(value = "Get all registers with the shortest connection path between origin and destiny city", response = CityConnectionDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with the shortest connection path between cities"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem with the search"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/search/connections/shortest/path/{originCity}/{destinyCity}",
        produces = "application/json")
    public ResponseEntity<List<CityConnectionDTO>> getShortestPathFromOriginToDestinyCity(
        @PathVariable String originCity, @PathVariable String destinyCity) {

        log.debug("REST fetch all connections between cities {} and {}", originCity);
        return ResponseEntity.ok().body(searchService.getShortestConnectionsFromOriginCity(originCity.toLowerCase().replaceAll("\\s", ""), destinyCity.toLowerCase().replaceAll("\\s", "")));
    }

    @ApiOperation(value = "Get all registers with the shortest connection distance between cities with originId and destinyId", response = CityConnectionDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with itineraries forming shortest connection distance between cities"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem with the search"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/search/connections/shortest/distance/{originCity}/{destinyCity}",
        produces = "application/json")
    public ResponseEntity<List<CityConnectionDTO>> getShortestDistanceFromOriginToDestinyCity(
        @PathVariable String originCity, @PathVariable String destinyCity) {
        log.debug("REST fetch all connections between cities {} and {}", originCity);
        return ResponseEntity.ok().body(searchService.getShortestDistanceFromOriginCity(originCity.toLowerCase().replaceAll("\\s", ""), destinyCity.toLowerCase().replaceAll("\\s", "")));
    }
}
