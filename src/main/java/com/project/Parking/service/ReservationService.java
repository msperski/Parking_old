package com.project.Parking.service;


import com.project.Parking.DAO.CustomerDAO;
import com.project.Parking.DAO.ParkingSpaceDAO;
import com.project.Parking.DAO.ReservationDAO;
import com.project.Parking.exception.NotFoundExceptionHandler;
import com.project.Parking.exception.ConflictExceptionHandler;
import com.project.Parking.model.Customer;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ReservationService {


    CustomerDAO customerDAO;
    ParkingSpaceDAO parkingSpaceDAO;
    ReservationDAO reservationDAO;

    public ReservationService(CustomerDAO customerDAO, ParkingSpaceDAO parkingSpaceDAO, ReservationDAO reservationDAO){
        this.customerDAO = customerDAO;
        this.parkingSpaceDAO = parkingSpaceDAO;
        this.reservationDAO = reservationDAO;
    }


    public Reservation setReservation(Reservation reservation){

        Optional<Customer> optionalCustomer = customerDAO.findById(reservation.getCustomerId());
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(reservation.getParkingSpaceId());
        Optional<Reservation> optionalReservation = reservationDAO.findById(reservation.getParkingSpaceId());
        if(optionalCustomer.isEmpty()) {
            throw new NotFoundExceptionHandler("There is no such customer.");
        }
        if(optionalParkingSpace.isEmpty())
        {
            throw new NotFoundExceptionHandler("There is no such parking space.");
        }
        if((optionalReservation.isPresent())) {
            throw new ConflictExceptionHandler("There is reservation for this parking space already.");
        }

        return reservationDAO.save(reservation);
    }
    public void deleteReservation(String id){
        Optional<Reservation> optionalReservation = reservationDAO.findById(id);
        if(optionalReservation.isEmpty()) {
            throw new NotFoundExceptionHandler("There is no such reservation.");
        }
        reservationDAO.deleteById(id);
    }

    public List<Reservation> getReservation(String CustomerId)
    {
        Optional<Reservation> optionalReservation = reservationDAO.findById(CustomerId);
        if (!(optionalReservation.isEmpty())) {
            throw new NotFoundExceptionHandler("There is no reservations for such customer");
        }
        return  reservationDAO.findAllByCustomerId(CustomerId);
    }
}
