package com.mvppoa.adidas.controller;

import com.mvppoa.adidas.domain.dto.ItineraryDTO;
import com.mvppoa.adidas.domain.dto.ItineraryJoinDTO;
import com.mvppoa.adidas.service.ItineraryService;
import com.mvppoa.adidas.web.rest.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ItineraryControllerTest {

    @Mock
    ItineraryService itineraryService;

    ItineraryController itineraryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        itineraryController = new ItineraryController(itineraryService);

        mockMvc = MockMvcBuilders.standaloneSetup(itineraryController)
            .build();

    }

    @Test
    public void itineraryJoinSuccess() throws Exception{

        ItineraryJoinDTO itineraryJoinDTO = new ItineraryJoinDTO();
        itineraryJoinDTO.setCityId(0L);
        itineraryJoinDTO.setDestinyCityId(1L);
        itineraryJoinDTO.setDepartureTime(ZonedDateTime.now());
        itineraryJoinDTO.setArrivalTime(ZonedDateTime.now());

        ItineraryDTO itineraryDTO = new ItineraryDTO();

        Mockito.when(itineraryService.createRelationship(anyLong(), anyLong(), any(), any()))
            .thenReturn(itineraryDTO);

        mockMvc.perform(post("/api/itinerary/join")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itineraryJoinDTO)))
            .andExpect(status().isOk());

        Mockito.verify(itineraryService, times(1))
            .createRelationship(anyLong(),anyLong(),any(),any());


    }
}
