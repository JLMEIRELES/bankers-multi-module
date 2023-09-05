package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import com.example.meireles.banker.infrastructure.mapper.CustomerMapper;
import com.example.meireles.banker.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerAdapter implements CustomerProvider {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer addCustomer(Customer customer) {
        log.debug(customerRepository.findAll().toString());
        CustomerEntity customerEntity = customerRepository.
                save(customerMapper.toCustomerEntity(customer));
        return customerMapper.toCustomer(customerEntity);
    }
}
