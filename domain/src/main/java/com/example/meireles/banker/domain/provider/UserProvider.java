package com.example.meireles.banker.domain.provider;

import com.example.meireles.banker.domain.model.User;

public interface UserProvider {

    /**
     * Saves a Customer in database
     *
     * @param user the customer to be created
     * @return the created customer
     */
    User addUser(User user);

}
