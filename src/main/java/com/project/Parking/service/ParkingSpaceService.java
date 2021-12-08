package com.project.Parking.service;

import com.project.Parking.DAO.ParkingSpaceDAO;
import com.project.Parking.DAO.ReservationDAO;
import com.project.Parking.exception.NotFoundExceptionHandler;
import com.project.Parking.exception.ConflictExceptionHandler;
import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class ParkingSpaceService {


    ParkingSpaceDAO parkingSpaceDAO;
    ReservationDAO reservationDAO;

    public ParkingSpaceService(ParkingSpaceDAO parkingSpaceDAO, ReservationDAO reservationDAO){
        this.parkingSpaceDAO = parkingSpaceDAO;
        this.reservationDAO= reservationDAO;
    }


    public ParkingSpace addParkingSpace(ParkingSpace parkingSpace) {

        String prefix;
        String suffix;
        String nr;

        prefix = Integer.toString(parkingSpace.getPlaceNumber());
        suffix = Integer.toString(parkingSpace.getStorey());
        nr = String.join("-",prefix,suffix);
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(nr);
        if((optionalParkingSpace.isPresent())) {
            throw new ConflictExceptionHandler("There is already parking space with same number and storey ");
        }

        parkingSpace.setParkingSpaceId(nr);
        return parkingSpaceDAO.save(parkingSpace);
    }

    public void deleteParkingSpace(String id){
        Optional<ParkingSpace> optionalParkingSpace = parkingSpaceDAO.findById(id);
        if(optionalParkingSpace.isEmpty()) {
            throw new NotFoundExceptionHandler("There is no such parking space.");
        }
        parkingSpaceDAO.deleteById(id);
    }

    public List<ParkingSpace> getParkingSpace(){

        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        List<ParkingSpace> freeParkingSpaces = new ArrayList<>();
        parkingSpaces.addAll(parkingSpaceDAO.findAll());
        reservations.addAll(reservationDAO.findAll());

        if(reservations.isEmpty())
        {
            return parkingSpaceDAO.findAll();
        }

        if(parkingSpaces.size() == reservations.size())
        {
            return freeParkingSpaces;
        }

        for(ParkingSpace parkingSpace : parkingSpaces){
            for(Reservation reservation : reservations){
                if(!(Objects.equals(parkingSpace.getParkingSpaceId(), reservation.getParkingSpaceId())))
                    freeParkingSpaces.add(parkingSpace);
            }
        }
        return freeParkingSpaces;
    }



}
