package com.meireles.example.domain.service;

import com.meireles.example.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Customer addCustomer(Customer customer);

}
