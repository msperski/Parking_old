package com.project.Parking.api;


import com.project.Parking.model.Customer;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import com.project.Parking.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/parking")
public class ParkingResource {

    @Autowired
    private ParkingSpaceService parkingSpaceService;


    @PostMapping(value = "/add")
    public ParkingSpace addParkingSpace(ParkingSpace parkingSpace){
        return parkingSpaceService.AddParkingSpace(parkingSpace);
    }
    @GetMapping(value = "/list")
    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaceService.GetParkingSpace();
    }

    @PostMapping(value = "/delete/{parkingSpaceId}")
    void deleteParkingSpace(String parkingSpaceId) {
        parkingSpaceService.DeleteParkingSpace(parkingSpaceId);
    }

    @PostMapping(value = "/reservation/add")
    Reservation SetReservation_(Reservation reservation){
        return parkingSpaceService.SetReservation_(reservation);
    }

    @GetMapping(value = "/reservation/list/{CustomerId}")
    public List<Reservation> GetReservation(String CustomerId) {
        return parkingSpaceService.GetReservation(CustomerId);
    }
    @PostMapping(value = "reservation/delete/{ParkingSpaceId}")
    void DeleteReservation(String ParkingSpaceId) {
        parkingSpaceService.DeleteReservation(ParkingSpaceId);
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
