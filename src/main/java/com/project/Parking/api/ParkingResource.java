package com.project.Parking.api;


import com.project.Parking.model.Customer;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import com.project.Parking.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parking")
public class ParkingResource {

    @Autowired
    private ParkingSpaceService parkingSpaceService;


    @PostMapping(value = "/add")
    public ParkingSpace addParkingSpace(ParkingSpace parkingSpace){
        return parkingSpaceService.addParkingSpace(parkingSpace);
    }
    @GetMapping(value = "/list")
    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaceService.getParkingSpace();
    }

    @PostMapping(value = "/delete/{parkingSpaceId}")
    void deleteParkingSpace(String parkingSpaceId) {
        parkingSpaceService.deleteParkingSpace(parkingSpaceId);
    }

    @PostMapping(value = "/reservation/add")
    Reservation SetReservation_(Reservation reservation){
        return parkingSpaceService.SetReservation_(reservation);
    }
    @GetMapping(value = "/reservation/list")
    public List<Reservation> GetReservation() {
        return parkingSpaceService.GetReservation();
    }
    @GetMapping(value = "/reservation/list/{CustomerId}")
    public List<Reservation> GetReservationn(String CustomerId) {
        return parkingSpaceService.GetReservationn(CustomerId);
    }
    @PostMapping(value = "reservation/delete/{ParkingSpaceId_}")
    void DeleteReservation(String ParkingSpaceId_) {
        parkingSpaceService.DeleteReservation(ParkingSpaceId_);
    }

    @PostMapping(value = "customer/add")
    public Customer AddCustomer(Customer customer){
        return parkingSpaceService.AddCustomer(customer);
    }

    @GetMapping(value = "customer/list")
    public List<Customer> GetCustomer() {
        return parkingSpaceService.GetCustomer();
    }



}
