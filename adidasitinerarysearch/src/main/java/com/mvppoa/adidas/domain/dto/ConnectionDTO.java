package com.mvppoa.adidas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConnectionDTO implements Comparable{

    private String connections;
    private Long totalTravelTimeInSeconds;
    private String originCityTimezone;
    private String destinyCityTimezone;

    @Override
    public int compareTo(Object connectionsDTO) {
        if (this.totalTravelTimeInSeconds < ((ConnectionDTO)connectionsDTO).totalTravelTimeInSeconds) {
            return -1;
        }
        if (this.totalTravelTimeInSeconds > ((ConnectionDTO)connectionsDTO).totalTravelTimeInSeconds) {
            return 1;
        }
        return 0;
    }

}
