package com.example.meireles.banker.application.controller.v1;

import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.example.meireles.banker.application.mapper.CustomerDtoMapper;
import com.example.meireles.banker.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Class rest controller to manage {@link CustomerController} endpoints
 */
@RestController
@RequestMapping(value = "${customer.controller}", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Customer management API")
public class CustomerController {

    @Value("${customer.controller}")
    private String path;

    private final CustomerDtoMapper customerDtoMapper;

    private final UserService userService;

    /**
     * Creates a new customer
     *
     * @param customerRequest an instance of {@link CustomerRequest} content information about the customer to be created
     * @return an instance of {@link CustomerResponse} content the id of the created customer
     */
    @PostMapping
    @Operation(summary = "Create a new customer", description = "Create a juridic or physical person")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = userService.addCustomer(customerDtoMapper.toCustomer(customerRequest));
        CustomerResponse customerResponse = customerDtoMapper.toCustomerResponse(customer);
        return ResponseEntity.created(URI.create(path + "/" + customerResponse.getId()))
                .body(customerResponse);
    }

}
