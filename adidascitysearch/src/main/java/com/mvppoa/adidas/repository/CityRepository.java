package com.mvppoa.adidas.repository;

import com.mvppoa.adidas.domain.City;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends Neo4jRepository<City, Long> {

    @Query("MATCH p=(a {name: {originCity}})-[*]-(connected) RETURN p")
    List<Object> getAllConnectionsBetweenCities(@Param("originCity") String originCity);

    @Override
    @Query("MATCH (n:`City`) WHERE ID(n) = {id} WITH n RETURN n")
    Optional<City> findById(@Param("id") Long id);

    @Query("MATCH (n:`City`) RETURN n")
    List<City> findAll();


}
