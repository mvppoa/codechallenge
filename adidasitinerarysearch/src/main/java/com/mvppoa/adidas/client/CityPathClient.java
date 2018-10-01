package com.mvppoa.adidas.client;

import com.mvppoa.adidas.config.FeignConfigure;
import com.mvppoa.adidas.exceptions.ConnectionServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Marcelo.Fachinelli
 */

@FeignClient(value = "adidas-city-itinerary-management", path = "/api", configuration = FeignConfigure.class)
@RefreshScope
public interface CityPathClient {

    @GetMapping("/itinerary/connections/{originCity}")
    List<Object> getShortestPathAndConnections(@RequestParam("originCity") String originCity);

    @Component
    class CityPathClientFallback implements CityPathClient {

        private final Logger log = LoggerFactory.getLogger(CityPathClientFallback.class);

        @Override
        public List<Object> getShortestPathAndConnections(@RequestParam("originCity") String originCity) {
            log.error("Could not access: adidas-city-itinerary-management");
            throw new ConnectionServerException("Could not access: adidas-city-itinerary-management");
        }

    }
}
