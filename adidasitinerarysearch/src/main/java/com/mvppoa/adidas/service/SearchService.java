package com.mvppoa.adidas.service;

import com.mvppoa.adidas.domain.dto.CityConnectionDTO;

import java.util.List;

public interface SearchService {

    List<CityConnectionDTO> getShortestConnectionsFromOriginCity(String originCity, String destinyCity);

    List<CityConnectionDTO> getShortestDistanceFromOriginCity(String originCity, String destinyCity);

}
