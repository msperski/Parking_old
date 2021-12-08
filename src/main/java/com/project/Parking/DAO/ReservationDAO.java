package com.project.Parking.DAO;


import com.project.Parking.model.ParkingSpace;
import com.project.Parking.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationDAO extends CrudRepository<Reservation, String> {

    @Override
    List<Reservation> findAll();

    List<Reservation> findAllByCustomerId(String customerId);
}
