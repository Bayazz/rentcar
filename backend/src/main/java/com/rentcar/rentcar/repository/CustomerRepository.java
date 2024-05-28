package com.rentcar.rentcar.repository;

import com.rentcar.rentcar.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
