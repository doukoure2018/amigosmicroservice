package com.amigoscode.customer.service.impl;

import com.amigoscode.customer.entity.Customer;
import com.amigoscode.customer.payload.CustomerDto;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;


    @Override
    public CustomerDto create(CustomerDto customerDto) {

        Customer customer = mapper.map(customerDto,Customer.class);
        Customer newCustomer = customerRepository.save(customer);
        return mapper.map(newCustomer,CustomerDto.class);
    }
}
