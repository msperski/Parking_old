package com.project.Parking.service;

import com.project.Parking.DAO.CustomerDAO;
import com.project.Parking.DAO.ParkingSpaceDAO;
import com.project.Parking.DAO.ReservationDAO;
import com.project.Parking.exception.Exceptions;
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

    public String prefix;
    public String suffix;
    public String nr;

    //dodawanie miejsca parkingowego
    public ParkingSpace addParkingSpace(ParkingSpace parkingSpace) {


        prefix = Integer.toString(parkingSpace.getPlaceNumber());
        suffix = Integer.toString(parkingSpace.getStorey());
        nr = String.join("-",prefix,suffix);
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(nr);
        if((optionalParkingSpace.isPresent())) {
            throw new Exceptions("There is already parking place with same number and storey ");
        }
        parkingSpace.setReserved(false);
        parkingSpace.setParkingSpaceId(nr);
        return parkingSpaceDAO.save(parkingSpace);
    }

    //usuniecie miejsca parkingowego
    public void deleteParkingSpace(String id){
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(id);
        if(!(optionalParkingSpace.isPresent())) {
            throw new Exceptions("There is no such parking place.");
        }
        //sprawdzenie czy istnieje takie miejsce i czy jest zarezerowwane
        parkingSpaceDAO.deleteById(id);
    }
    //otrzymanie wszystkich miejsc parkingowych
    public List<ParkingSpace> getParkingSpace(){

        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        List<ParkingSpace> freeParkingSpaces = new ArrayList<>();
        parkingSpaces.addAll(parkingSpaceDAO.findAll());

        for(ParkingSpace parkingSpace : parkingSpaces){
            if(!(parkingSpace.isReserved())){
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
            throw new Exceptions("There is reservation for this parking place already.");
        }
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        parkingSpaces.addAll(parkingSpaceDAO.findAll());
        for(ParkingSpace parkingSpace : parkingSpaces){
            if(Objects.equals(parkingSpace.getParkingSpaceId(),reservation.getParkingSpaceId()))
            parkingSpace.setReserved(true);
        }
        return reservationDAO.save(reservation);
    }
    public List<Reservation> GetReservation(){

        return reservationDAO.findAll();
    }
    public void DeleteReservation(String id){
        Optional<Reservation> optionalReservation = reservationDAO.findById(id);
        if(!(optionalReservation.isPresent())) {
            throw new Exceptions("There is no such reservation.");
        }
        reservationDAO.deleteById(id);
    }

    public Customer AddCustomer(Customer customer) {

        Optional<Customer> optionalCustomer = customerDAO.findById(customer.getCustomerId());
        if ((optionalCustomer.isPresent())) {
            throw new Exceptions("There is already customer with the same ID");
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
                throw new Exceptions("There have to be a least 1 sign!!!");
            }
            return customerDAO.save(customer);
        }else{
                throw new Exceptions("Required 2-20 signs!!!");
        }

    }
    public List<Customer> GetCustomer(){

        return customerDAO.findAll();
    }


    public List<Reservation> GetReservationn(String CustomerId)
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
