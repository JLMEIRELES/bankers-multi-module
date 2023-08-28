package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.Customer;

public interface CustomerService {

    /**
     * Creates a new customer
     *
     * @param customer the customer to be created
     * @return the created customer
     */
    Customer addCustomer(Customer customer);

}
