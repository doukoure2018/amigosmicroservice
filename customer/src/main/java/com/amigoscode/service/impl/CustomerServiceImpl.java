package com.amigoscode.service.impl;

import com.amigoscode.clients.FraudClient;
import com.amigoscode.clients.NotificationClient;
import com.amigoscode.clients.dto.NotificationDto;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;


    @Override
    public CustomerDto create(CustomerDto customerDto) throws Exception {
        // Check if customer already exists and log the action
        if(customerRepository.existsByEmail(customerDto.getEmail())){
            log.warn("Attempt to create a customer with an existing email: {}", customerDto.getEmail());
            throw new Exception("User with this "+customerDto.getEmail()+" already exists");
        }

        // Log the customer data being saved
        log.info("Saving new customer with email: {}", customerDto.getEmail());
        Customer customer = mapper.map(customerDto, Customer.class);
        customerRepository.saveAndFlush(customer);

        // Log the fraud check
        log.info("Checking if customer with ID: {} is a fraudster", customer.getId());
        Boolean isFraudster = fraudClient.isFraudulentCustomer(customer.getId());
        if (Boolean.TRUE.equals(isFraudster)) {
            log.warn("Customer with ID: {} is marked as a fraudster", customer.getId());
            customer.setStatus("INACTIVE");
            throw new IllegalStateException("Customer is marked as a fraudster");
        }

        // Log successful fraud check
        log.info("Customer with ID: {} passed the fraud check", customer.getId());
        customer.setStatus("ACTIVE");

        // Log the notification action
        log.info("Sending notification to customer with ID: {}", customer.getId());
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setToCustomerId(customer.getId());
        notificationDto.setToCustomerEmail(customer.getEmail());
        notificationDto.setSender("douklifsa93@gmail.com");
        notificationDto.setMessage("hello i have just sent the notification");
        notificationDto.setSentAt(LocalDateTime.now());
        notificationClient.saveNotification(notificationDto);

        // Log the customer after final save
        Customer newCustomer = customerRepository.save(customer);
        log.info("Customer with ID: {} saved successfully with status: {}", newCustomer.getId(), newCustomer.getStatus());

        return mapper.map(newCustomer, CustomerDto.class);
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
