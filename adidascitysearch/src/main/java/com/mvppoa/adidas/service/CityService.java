package com.mvppoa.adidas.service;

import com.mvppoa.adidas.domain.City;

import java.util.List;

/**
 * Service Interface for managing cities.
 */
public interface CityService {

    Iterable<City> findById();

    City findById(Long id);

    List<City> findAll();

    City saveCity(String cityName);
}
