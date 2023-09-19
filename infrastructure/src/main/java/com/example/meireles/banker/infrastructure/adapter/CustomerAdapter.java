package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.model.enums.CustomerType;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import com.example.meireles.banker.infrastructure.client.ZipCodeClient;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
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

    private final ZipCodeClient zipCodeClient;


    /**
     * {@inheritDoc}
     */
    @Override
    public Customer addCustomer(Customer customer) {
        Address customerAddress = customer.getAddress();

        log.info("Searching for address with zip code = {}", customer.getAddress().getZipCode());
        Endereco endereco = zipCodeClient.
                getAddress(customer.getAddress().getZipCode());
        var address = customerMapper.toAddress(endereco);
        log.info("Address founded. Address = {}", address);

        Address mergedAddress = customerMapper.mergeAddress(customerAddress, address);
        customer.setAddress(mergedAddress);
        if(customer.getCustomerType() == null){
            customer.setCustomerType(CustomerType.SILVER);
        }

        log.info("Saving Customer = {} in database", customer);
        CustomerEntity customerEntity = customerRepository.
                save(customerMapper.toCustomerEntity(customer));
        log.info("Customer = {} was saved", customerEntity);
        return customerMapper.toCustomer(customerEntity);
    }
}
