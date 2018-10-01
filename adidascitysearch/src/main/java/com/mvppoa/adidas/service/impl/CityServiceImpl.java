package com.mvppoa.adidas.service.impl;

import com.mvppoa.adidas.domain.City;
import com.mvppoa.adidas.repository.CityRepository;
import com.mvppoa.adidas.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Iterable<City> findById() {
        return cityRepository.findAll();
    }

    @Override
    public City saveCity(String cityName) {
        City c = new City();
        c.setLowerCaseWithoutSpaceName(cityName);
        return cityRepository.save(c);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }


    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).get();
    }
}
