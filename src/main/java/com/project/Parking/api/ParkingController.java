package com.project.Parking.api;


import com.project.Parking.model.Customer;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import com.project.Parking.service.CustomerService;
import com.project.Parking.service.ParkingSpaceService;
import com.project.Parking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/parking")
public class ParkingController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationService reservationService;


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
    Reservation setReservation(Reservation reservation){
        return reservationService.setReservation(reservation);
    }

    @GetMapping(value = "/reservation/list/{CustomerId}")
    public List<Reservation> getReservation(String CustomerId) {
        return reservationService.getReservation(CustomerId);
    }
    @PostMapping(value = "reservation/delete/{ParkingSpaceId}")
    void deleteReservation(String ParkingSpaceId) {
        reservationService.deleteReservation(ParkingSpaceId);
    }

    @PostMapping(value = "customer/add")
    public Customer addCustomer(Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping(value = "customer/list")
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }



}
