package com.project.Parking.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ParkingSpace {

    @Id
    private String parkingSpaceId_;
    private int PlaceNumber;
    private int Storey;
    private boolean ForDisabled;



    public boolean isReserved() {
        return Reserved;
    }

    public void setReserved(boolean reserved) {
        Reserved = reserved;
    }

    private boolean Reserved;

    public String getParkingSpaceId() {
        return parkingSpaceId_;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId_ = parkingSpaceId;
    }

    public int getPlaceNumber() {
        return PlaceNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        PlaceNumber = placeNumber;
    }

    public int getStorey() {
        return Storey;
    }

    public void setStorey(int storey) {
        Storey = storey;
    }

    public boolean isForDisabled() {
        return ForDisabled;
    }

    public void setForDisabled(boolean forDisabled) {
        ForDisabled = forDisabled;
    }






}
