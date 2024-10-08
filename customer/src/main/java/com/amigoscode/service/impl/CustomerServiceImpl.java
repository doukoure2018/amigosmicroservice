package com.amigoscode.service.impl;

import com.amigoscode.entity.Customer;
import com.amigoscode.payload.CustomerDto;
import com.amigoscode.repository.CustomerRepository;
import com.amigoscode.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    private final RestTemplate restTemplate;


    @Override
    public CustomerDto create(CustomerDto customerDto) throws Exception {
        if(customerRepository.existsByEmail(customerDto.getEmail())){
             throw new Exception("User with this "+customerDto.getEmail()+" already existe");
        }
        Customer customer = mapper.map(customerDto,Customer.class);
        customerRepository.saveAndFlush(customer);
        // We want to know weither the customer is fraudster or not
        Boolean isFraudster;

            isFraudster = restTemplate.getForObject(
                    "http://localhost:8081/api/v1/fraud/{customerId}",
                    Boolean.class,
                    customer.getId()
            );
        if (Boolean.TRUE.equals(isFraudster)) {
            log.info("this customer is a fraudster");
            customer.setStatus("INACTIVE");
            throw new IllegalStateException("Customer is marked as a fraudster");
        }
        customer.setStatus("ACTIVE");
        Customer newCustomer = customerRepository.save(customer);
        return mapper.map(newCustomer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Integer customerId) {
        Customer customer =customerRepository.findById(Long.valueOf(customerId)).get();
        return mapper.map(customer,CustomerDto.class);
    }
}
