package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.User;

public interface UserService {

    /**
     * Creates a new customer
     *
     * @param user the customer to be created
     * @return the created customer
     */
    User addCustomer(User user);

}
