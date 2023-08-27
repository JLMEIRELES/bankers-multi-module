package com.meireles.example.domain.service.impl;

import com.meireles.example.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import com.meireles.example.domain.provider.CustomerProvider;
import com.meireles.example.domain.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerProvider customerProvider;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerProvider.addCustomer(customer);
    }
}
