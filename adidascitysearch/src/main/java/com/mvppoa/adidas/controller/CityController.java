package com.mvppoa.adidas.controller;

import com.mvppoa.adidas.domain.City;
import com.mvppoa.adidas.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing cities.
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation(value = "Add a new city to the database", response = City.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with the created city"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem when saving the registry"),
    })
    @PostMapping(value = "/city/add",
        produces = "application/json")
    public ResponseEntity<City> saveCity(
        @ApiParam(value = "City name to be saved") @RequestParam("cityName") String cityName) {

        log.debug("REST fetch all connections to city {}", cityName);
        City result = cityService.saveCity(cityName);

        return ResponseEntity.ok()
            .body(result);
    }

    @ApiOperation(value = "Find an existing city in the database", response = City.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and with the created city"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem when saving the registry"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/city/find/{id}",
        produces = "application/json")
    public ResponseEntity<City> findById(
        @ApiParam(value = "City id to be searched") @PathVariable("id") Long id) {

        log.debug("REST fetch city by id {}", id);

        City result = cityService.findById(id);

        return ResponseEntity.ok()
            .body(result);

    }

    @ApiOperation(value = "Find all cities in the database", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The ResponseEntity with status 200 (OK) and all the cities in the database"),
        @ApiResponse(code = 500, message = "(Internal Server Error) if the there was a problem when saving the registry"),
    })
    @GetMapping(value = "/city/find/",
        produces = "application/json")
    public ResponseEntity<List<City>> findAll() {

        log.debug("REST fetch all cities");

        return ResponseEntity.ok()
            .body(cityService.findAll());

    }
}
