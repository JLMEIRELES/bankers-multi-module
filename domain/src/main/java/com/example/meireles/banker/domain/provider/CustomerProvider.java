package com.example.meireles.banker.domain.provider;

import com.example.meireles.banker.domain.model.Customer;

public interface CustomerProvider {

    /**
     * Saves a Customer in database
     *
     * @param customer the customer to be created
     * @return the created customer
     */
    Customer addCustomer(Customer customer);

}
