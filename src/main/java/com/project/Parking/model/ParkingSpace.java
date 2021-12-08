package com.project.Parking.model;

import javax.persistence.*;

@Entity
@Table(name= "parking_spaces")
public class ParkingSpace {

    @Id
    private String parkingSpaceId;
    @Column
    private int placeNumber;
    @Column
    private int storey;
    @Column
    private boolean forDisabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkingSpaceId")
    private Reservation reservation;

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
