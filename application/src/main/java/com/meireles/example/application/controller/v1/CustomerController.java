package com.meireles.example.application.controller.v1;

import com.meireles.example.application.dto.request.CustomerRequest;
import com.meireles.example.application.dto.response.CustomerResponse;
import com.meireles.example.application.mapper.CustomerDtoMapper;
import com.meireles.example.domain.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "${customer.controller}", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerController {

    @Value("${customer.controller}")
    private String path;

    private final CustomerDtoMapper customerDtoMapper;

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
        var customer = customerDtoMapper.toCustomer(customerRequest);
        var addedCustomer = customerService.addCustomer(customer);

        CustomerResponse customerResponse = customerDtoMapper.toCustomerResponse(addedCustomer);

        return ResponseEntity.created(URI.create(path + "/" + customerResponse.getId()))
                .body(customerResponse);
    }

}
