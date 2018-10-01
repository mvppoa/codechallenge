package com.mvppoa.adidas.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class ItineraryJoinDTO implements Serializable {

    private Long cityId;
    private Long destinyCityId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime arrivalTime;

}
