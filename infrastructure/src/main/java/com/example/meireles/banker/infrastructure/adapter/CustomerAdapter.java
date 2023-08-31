package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import com.example.meireles.banker.infrastructure.mapper.CustomerMapper;
import com.example.meireles.banker.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerProvider {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepository.
                save(customerMapper.toCustomerEntity(customer));
        return customerMapper.toCustomer(customerEntity);
    }
}
