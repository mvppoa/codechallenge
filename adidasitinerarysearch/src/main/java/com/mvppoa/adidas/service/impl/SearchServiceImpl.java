package com.mvppoa.adidas.service.impl;

import com.mvppoa.adidas.client.CityPathClient;
import com.mvppoa.adidas.domain.Connections;
import com.mvppoa.adidas.domain.NodeHelp;
import com.mvppoa.adidas.domain.dto.CityConnectionDTO;
import com.mvppoa.adidas.exceptions.CustomParameterizedException;
import com.mvppoa.adidas.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final CityPathClient cityPathClient;

    private static final String SEARCH_FAILURE_EXCEPTION = "SearchFailureException - ";
    private static final String NAME = "name";
    private static final String DEPARTURE_TIME = "departureTime";
    private static final String ARRIVAL_TIME = "arrivalTime";

    public SearchServiceImpl(CityPathClient cityPathClient) {
        this.cityPathClient = cityPathClient;
    }

    @Override
    public List<CityConnectionDTO> getShortestConnectionsFromOriginCity(String originCity, String destinyCity) {

        log.debug("Service calulate shortest way from origin city {}", originCity);

        List<Object> connectionsBetweenCitiesList = getAllConnectionsFromOriginCity(originCity);
        List<Connections> cityConnectionDTOS = getAllConnectionsToDestinyCity(connectionsBetweenCitiesList,destinyCity);

        if(!cityConnectionDTOS.isEmpty()) {

            Integer minValue = cityConnectionDTOS.stream().min(Comparator.comparing(Connections::getConnectionNumber)).get().getConnectionNumber();

            return cityConnectionDTOS.stream()
                .filter(connections -> connections.getConnectionNumber().equals(minValue))
                .findFirst()
                .map(Connections::getCityConnectionDTOS).get();
        } else{
            throw new CustomParameterizedException(SEARCH_FAILURE_EXCEPTION + "The given city origin ("+originCity+") and destination ('" + destinyCity + "') could not be found");
        }

    }

    @Override
    public List<CityConnectionDTO> getShortestDistanceFromOriginCity(String originCity, String destinyCity) {

        log.debug("Service calulate shortest way from origin city {}", originCity);

        List<Object> connectionsBetweenCitiesList = getAllConnectionsFromOriginCity(originCity);
        List<Connections> cityConnectionDTOS = getAllConnectionsToDestinyCity(connectionsBetweenCitiesList,destinyCity);

        if(!cityConnectionDTOS.isEmpty()) {

            Long minDistance = cityConnectionDTOS.stream().min(Comparator.comparing(Connections::getMaxDistance)).get().getMaxDistance();

            return cityConnectionDTOS.stream()
                .filter(connections -> connections.getMaxDistance().equals(minDistance))
                .findFirst()
                .map(Connections::getCityConnectionDTOS).get();
        } else{
            throw new CustomParameterizedException(SEARCH_FAILURE_EXCEPTION + "The given city origin ("+originCity+") and destination ('" + destinyCity + "') could not be found");

        }

    }

    private List<Connections> getAllConnectionsToDestinyCity(List<Object> rawNodeObjects, String destinyCity) {

        Map<String, NodeHelp> nodes = new HashMap<>();
        AtomicReference<NodeHelp> originNode = new AtomicReference<>();

        List<Connections> connectionsList = new ArrayList<>();

        for (Object rawNodeObjectList : rawNodeObjects) {
            AtomicReference<Long> departureTime = new AtomicReference<>();
            AtomicReference<Boolean> containsDestinyCity = new AtomicReference<>(false);
            AtomicReference<Long> arrivalTime = new AtomicReference<>();
            AtomicReference<NodeHelp> currentNodeAux = new AtomicReference<>();

            Connections connections = new Connections();

            List<CityConnectionDTO> cityConnectionDTOS = new ArrayList<>();

            ((List<LinkedHashMap<String, Object>>) rawNodeObjectList).forEach(rawNodeObjectMap -> {
                if (rawNodeObjectMap.size() == 1) {
                    String name = rawNodeObjectMap.get(NAME).toString();
                    if (name.equals(destinyCity)) {
                        containsDestinyCity.set(true);
                    }
                    if (nodes.get(name) == null) {
                        NodeHelp currentNode = new NodeHelp(name);
                        if (nodes.size() == 0) {
                            originNode.set(currentNode);
                        }
                        nodes.put(name, currentNode);
                    }
                    if (currentNodeAux.get() != null && !currentNodeAux.get().equals(nodes.get(name))) {
                        CityConnectionDTO cityConnectionDTO = new CityConnectionDTO();
                        cityConnectionDTO.setOriginCity(currentNodeAux.get().getName());
                        cityConnectionDTO.setDestinyCity(name);
                        cityConnectionDTO.setDepartureTime(departureTime.get());
                        cityConnectionDTO.setArrivalTime(arrivalTime.get());
                        cityConnectionDTOS.add(cityConnectionDTO);
                        connections.addConnectionNumber();
                        connections.addMaxDistance(arrivalTime.get() - departureTime.get());
                    }
                    currentNodeAux.set(nodes.get(name));
                } else {
                    departureTime.set(Long.parseLong(rawNodeObjectMap.get(DEPARTURE_TIME).toString()));
                    arrivalTime.set(Long.parseLong(rawNodeObjectMap.get(ARRIVAL_TIME).toString()));
                }
            });
            if (containsDestinyCity.get()) {
                connections.setCityConnectionDTOS(cityConnectionDTOS);
                connectionsList.add(connections);
            }
        }

        return connectionsList;
    }

    private List<Object> getAllConnectionsFromOriginCity(String originCity) {

        log.debug("Service fetch all connections from city {}", originCity);

        return cityPathClient.getShortestPathAndConnections(originCity);
    }

}
