package com.kafkaproducerdemo.controller;

import com.kafkaproducerdemo.dto.Customer;
import com.kafkaproducerdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    Customer c1 = new Customer(1, "Haidar","haidar@gmail.com", "Lucknow",25 );
    Customer c2 = new Customer(2, "Rahul","rahul@gmail.com", "Agra",24 );
    Customer c3 = new Customer(3, "Anand","anand@gmail.com", "Bali",23 );

    @PostMapping(value = "/addCustomer", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public String addCustomer(@RequestBody List<Customer> customers){
        System.out.println(customers);
        return customerService.add(customers);
    }
}
