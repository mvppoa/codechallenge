package com.mvppoa.adidas.domain;

import com.mvppoa.adidas.domain.dto.CityConnectionDTO;
import lombok.Data;

import java.util.List;

@Data
public class Connections {

    private Long maxDistance;
    private Integer connectionNumber;

    private List<CityConnectionDTO> cityConnectionDTOS;

    public Connections() {
        maxDistance = 0L;
        connectionNumber = 0;
    }

    public void addConnectionNumber(){
        connectionNumber = connectionNumber+1;
    }

    public void addMaxDistance(Long distance){
        maxDistance += distance;
    }
}
