package com.mvppoa.adidas.mapper;
import com.mvppoa.adidas.domain.Itinerary;
import com.mvppoa.adidas.domain.dto.ItineraryDTO;


/**
 * @Author mfachinelli
 */
public interface ItineraryMapper{

    ItineraryDTO toDto(Itinerary input);
    Itinerary fromDto(ItineraryDTO input);
}
