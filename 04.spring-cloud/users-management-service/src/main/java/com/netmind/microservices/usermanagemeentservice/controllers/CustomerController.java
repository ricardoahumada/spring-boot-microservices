package com.netmind.microservices.usermanagemeentservice.controllers;


import com.netmind.microservices.usermanagemeentservice.entites.Customer;
import com.netmind.microservices.usermanagemeentservice.payloads.ApiResponse;
import com.netmind.microservices.usermanagemeentservice.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService ICustomerService;

    // Create
    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ICustomerService.create(customer));
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(ICustomerService.getAll());
    }


    // Get one
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {

        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.get(customerId));

    }

    //delete
    @DeleteMapping("/{customerId}")
    public ApiResponse deleteCustomer(@PathVariable String customerId) {

        this.ICustomerService.delete(customerId);
        return new ApiResponse(" Customer is Successfully Deleted !!", true);
    }

    //update
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable String customerId) {

        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.update(customerId, customer));

    }

}
