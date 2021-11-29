package com.project.Parking.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private String parkingSpaceId;
    private String customerId;


    public String getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }




}
