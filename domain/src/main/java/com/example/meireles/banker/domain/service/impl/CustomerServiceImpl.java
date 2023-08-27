package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerProvider customerProvider;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerProvider.addCustomer(customer);
    }
}
