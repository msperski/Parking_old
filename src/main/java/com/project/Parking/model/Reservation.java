package com.project.Parking.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    private String ParkingSpaceId;
    private String CustomerId;


    public String getParkingSpaceId() {
        return ParkingSpaceId;
    }

    public void setParkingSpaceId(String parkingSpaceId) {
        ParkingSpaceId = parkingSpaceId;
    }


    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }




}
