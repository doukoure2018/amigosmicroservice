package com.amigoscode.service;

import com.amigoscode.payload.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerDto customerDto) throws Exception;

    List<CustomerDto> getAll();
    CustomerDto getById(Integer customerId);
}
