package com.meireles.example.infrastructure.adapter;

import com.meireles.example.infrastructure.entity.CustomerEntity;
import com.meireles.example.infrastructure.mapper.CustomerMapper;
import com.meireles.example.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import com.meireles.example.domain.provider.CustomerProvider;
import com.meireles.example.infrastructure.repository.CustomerRepository;

public class CustomerAdapter implements CustomerProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer addCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepository.
                save(customerMapper.toCustomerEntity(customer));
        return customerMapper.toCustomer(customerEntity);
    }
}
