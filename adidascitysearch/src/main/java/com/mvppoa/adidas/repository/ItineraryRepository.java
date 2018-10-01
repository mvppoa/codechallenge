package com.mvppoa.adidas.repository;

import com.mvppoa.adidas.domain.Itinerary;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends Neo4jRepository<Itinerary, Long> {

}
