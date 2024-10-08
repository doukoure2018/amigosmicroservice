package com.amigoscode.repository;

import com.amigoscode.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByEmail(String email);
}
