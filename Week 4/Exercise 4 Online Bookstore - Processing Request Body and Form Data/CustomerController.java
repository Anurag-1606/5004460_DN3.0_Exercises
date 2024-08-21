package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST /customers - Create a new customer via JSON request body
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    @PostMapping("/register")
public ResponseEntity<Customer> registerCustomer(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("address") String address,
        @RequestParam("phoneNumber") String phoneNumber) {

    Customer customer = new Customer();
    customer.setName(name);
    customer.setEmail(email);
    customer.setAddress(address);
    customer.setPhoneNumber(phoneNumber);

    Customer registeredCustomer = customerService.createCustomer(customer);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredCustomer);
}
}
