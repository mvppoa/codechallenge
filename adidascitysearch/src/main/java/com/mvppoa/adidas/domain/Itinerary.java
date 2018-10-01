package com.mvppoa.adidas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "ITINERARY")
@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Itinerary {

    @Id
    @GeneratedValue
    private Long nodeId;

    @StartNode
    City city;

    @EndNode
    City destinyCity;

    Long departureTime;

    Long arrivalTime;
}
