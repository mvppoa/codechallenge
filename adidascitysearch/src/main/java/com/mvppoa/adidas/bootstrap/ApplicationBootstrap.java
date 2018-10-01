package com.mvppoa.adidas.bootstrap;

import com.mvppoa.adidas.domain.City;
import com.mvppoa.adidas.repository.CityRepository;
import com.mvppoa.adidas.service.ItineraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Component
@Slf4j
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CityRepository cityRepository;
    private final ItineraryService itineraryService;

    public ApplicationBootstrap(CityRepository cityRepository, ItineraryService itineraryService) {
        this.cityRepository = cityRepository;
        this.itineraryService = itineraryService;
    }

    @Override
    @Transactional
    @Profile("dev")
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Bootstrap Data");

        if(!cityRepository.findAll().iterator().hasNext()) {
            loadData();
        }
    }

    private void loadData(){

        City barcelona = new City();
        barcelona.setLowerCaseWithoutSpaceName("Barcelona");

        City madrid = new City();
        madrid.setLowerCaseWithoutSpaceName("Madrid");

        City zaragoza = new City();
        zaragoza.setLowerCaseWithoutSpaceName("Zaragoza");

        City portoAlegre = new City();
        portoAlegre.setLowerCaseWithoutSpaceName("Porto Alegre");

        cityRepository.save(barcelona);
        cityRepository.save(madrid);
        cityRepository.save(zaragoza);
        cityRepository.save(portoAlegre);

        ZonedDateTime zonedDateTimeCurrent = ZonedDateTime.now();
        ZonedDateTime zonedDateTimeOneHour = zonedDateTimeCurrent.plusHours(1L);
        ZonedDateTime zonedDateTimeTwoHours = zonedDateTimeCurrent.plusHours(2L);
        ZonedDateTime zonedDateTimeTenHours = zonedDateTimeCurrent.plusHours(10L);

        itineraryService.createRelationship(barcelona.getId(),madrid.getId(),
            zonedDateTimeCurrent.toInstant(),zonedDateTimeOneHour.toInstant());

        itineraryService.createRelationship(madrid.getId(),portoAlegre.getId(),
            zonedDateTimeOneHour.toInstant(),zonedDateTimeTwoHours.toInstant());

        itineraryService.createRelationship(portoAlegre.getId(),zaragoza.getId(),
            zonedDateTimeOneHour.toInstant(),zonedDateTimeTwoHours.toInstant());

        itineraryService.createRelationship(madrid.getId(),zaragoza.getId(),
            zonedDateTimeOneHour.toInstant(),zonedDateTimeTwoHours.toInstant());

        itineraryService.createRelationship(barcelona.getId(),zaragoza.getId(),
            zonedDateTimeCurrent.toInstant(),zonedDateTimeTenHours.toInstant());


    }
}
