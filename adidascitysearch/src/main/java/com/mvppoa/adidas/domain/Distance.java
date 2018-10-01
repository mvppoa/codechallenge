package com.mvppoa.adidas.domain;

import java.util.Objects;

public class Distance {

    private Long departureTime;
    private Long arrivalTime;

    public Distance(Long departureTime, Long arrivalTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Long departureTime) {
        this.departureTime = departureTime;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getDistance() {
        return arrivalTime-departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Objects.equals(departureTime, distance.departureTime) &&
            Objects.equals(arrivalTime, distance.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureTime, arrivalTime);
    }

    @Override
    public String toString() {
        return "Distance{" +
            "departureTime=" + departureTime +
            ", arrivalTime=" + arrivalTime +
            '}';
    }
}
