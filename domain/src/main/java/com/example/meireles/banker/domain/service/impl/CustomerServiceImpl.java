package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerProvider customerProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer addCustomer(Customer customer) {
        log.info("Creating customer = {}", customer);
        return customerProvider.addCustomer(customer);
    }
}
