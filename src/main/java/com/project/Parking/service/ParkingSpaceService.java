package com.project.Parking.service;

import com.project.Parking.DAO.CustomerDAO;
import com.project.Parking.DAO.ParkingSpaceDAO;
import com.project.Parking.DAO.ReservationDAO;
import com.project.Parking.exception.Exceptions;
import com.project.Parking.exception.Exceptions_;
import com.project.Parking.exception.Exceptions__;
import com.project.Parking.model.Customer;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceDAO parkingSpaceDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private CustomerDAO customerDAO;


    public Customer AddCustomer(Customer customer) {


        Optional<Customer> optionalCustomer = customerDAO.findById(customer.getCustomerId());
        if ((optionalCustomer.isPresent())) {
            throw new Exceptions_("There is already customer with the same ID");
        }
        int signs = 0;
        int spaceCount = 0;
        int totalSigns = 0;
        for (char c : customer.getCustomerId().toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            } else signs++;

        }
        totalSigns = spaceCount + signs;
        if (totalSigns >=2 && totalSigns <= 20) {

            if (spaceCount == totalSigns) {
                throw new Exceptions__("There have to be a least 1 sign!!!");
            }
            return customerDAO.save(customer);
        }else{
            throw new Exceptions__("Required 2-20 signs!!!");
        }

    }
    public List<Customer> GetCustomer(){

        return customerDAO.findAll();
    }

    public ParkingSpace AddParkingSpace(ParkingSpace parkingSpace) {

        String prefix;
        String suffix;
        String nr;

        prefix = Integer.toString(parkingSpace.getPlaceNumber());
        suffix = Integer.toString(parkingSpace.getStorey());
        nr = String.join("-",prefix,suffix);
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(nr);
        if((optionalParkingSpace.isPresent())) {
            throw new Exceptions_("There is already parking space with same number and storey ");
        }

        parkingSpace.setParkingSpaceId(nr);
        return parkingSpaceDAO.save(parkingSpace);
    }

    public void DeleteParkingSpace(String id){
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(id);
        if(!(optionalParkingSpace.isPresent())) {
            throw new Exceptions("There is no such parking space.");
        }
        parkingSpaceDAO.deleteById(id);
    }

    public List<ParkingSpace> GetParkingSpace(){

        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        List<ParkingSpace> freeParkingSpaces = new ArrayList<>();
        parkingSpaces.addAll(parkingSpaceDAO.findAll());
        reservations.addAll(reservationDAO.findAll());

        if(reservations.isEmpty())
        {
            return parkingSpaceDAO.findAll();
        }

        for(ParkingSpace parkingSpace : parkingSpaces){
            for(Reservation reservation : reservations){
                if(!(Objects.equals(parkingSpace.getParkingSpaceId(), reservation.getParkingSpaceId())))
                    freeParkingSpaces.add(parkingSpace);
            }
        }
        return freeParkingSpaces;
    }



    public Reservation SetReservation_(Reservation reservation){

        Optional<Customer> optionalCustomer = customerDAO.findById(reservation.getCustomerId());
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(reservation.getParkingSpaceId());
        Optional<Reservation> optionalReservation = reservationDAO.findById(reservation.getParkingSpaceId());
        if(!(optionalCustomer.isPresent())) {
            throw new Exceptions("There is no such customer.");
        }
        if(!(optionalParkingSpace.isPresent()))
        {
            throw new Exceptions("There is no such parking space.");
        }
        if((optionalReservation.isPresent())) {
            throw new Exceptions_("There is reservation for this parking space already.");
        }

        return reservationDAO.save(reservation);
    }
    public void DeleteReservation(String id){
        Optional<Reservation> optionalReservation = reservationDAO.findById(id);
        if(!(optionalReservation.isPresent())) {
            throw new Exceptions("There is no such reservation.");
        }
        reservationDAO.deleteById(id);
    }

    public List<Reservation> GetReservation(String CustomerId)
    {
        List<Reservation> reservations = new ArrayList<>();
        List<Reservation> reservations_ = new ArrayList<>();
        Optional<Reservation> optionalReservation = reservationDAO.findById(CustomerId);
        if (!(optionalReservation.isEmpty())) {
            throw new Exceptions("There is no reservations for such customer");
        }

       reservations.addAll(reservationDAO.findAll());

            for(Reservation reservation : reservations)
            {
                if(Objects.equals(reservation.getCustomerId(), CustomerId))
                {
                    reservations_.add(reservation);
                }
            }
        return  reservations_;
    }

}
