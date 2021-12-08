package com.project.Parking.service;

import com.project.Parking.DAO.CustomerDAO;
import com.project.Parking.exception.ConflictExceptionHandler;
import com.project.Parking.exception.BadRequestExceptionHandler;
import com.project.Parking.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {


    CustomerDAO customerDAO;
    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public Customer addCustomer(Customer customer) {

        Optional<Customer> optionalCustomer = customerDAO.findById(customer.getCustomerId());
        if ((optionalCustomer.isPresent())) {
            throw new ConflictExceptionHandler("There is already customer with the same ID");
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
                throw new BadRequestExceptionHandler("There have to be a least 1 sign!!!");
            }
            return customerDAO.save(customer);
        }else{
            throw new BadRequestExceptionHandler("Required 2-20 signs!!!");
        }

    }
    public List<Customer> getCustomer(){

        return customerDAO.findAll();
    }
}
