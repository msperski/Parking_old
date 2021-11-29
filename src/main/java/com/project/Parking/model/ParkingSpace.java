package com.project.Parking.model;

import javax.persistence.*;

@Entity
public class ParkingSpace {

    @Id
    private String parkingSpaceId;
    private int placeNumber;
    private int storey;
    private boolean forDisabled;


    public String getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getStorey() {
        return storey;
    }

    public void setStorey(int storey) {
        this.storey = storey;
    }

    public boolean isForDisabled() {
        return forDisabled;
    }

    public void setForDisabled(boolean forDisabled) {
        this.forDisabled = forDisabled;
    }






}
