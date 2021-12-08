package com.project.Parking.DAO;

import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceDAO extends CrudRepository<ParkingSpace, String> {

    @Override
    List<ParkingSpace> findAll();


}
