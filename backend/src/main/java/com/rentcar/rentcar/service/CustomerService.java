package com.rentcar.rentcar.service;

import com.rentcar.rentcar.dto.CustomerDTO;
import com.rentcar.rentcar.model.CustomerEntity;
import com.rentcar.rentcar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(this::convertCustomerEntityToDTO)
                .collect(Collectors.toList());

    }

    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::convertCustomerEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = convertCustomerDTOtoEntity(customerDTO);
        CustomerEntity customerEntitySaved = customerRepository.save(customerEntity);

        return convertCustomerEntityToDTO(customerEntitySaved);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerEntity updatedCustomer = updateCustomerNonNullProperty(customerDTO, customer);

        return convertCustomerEntityToDTO(customerRepository.save(updatedCustomer));
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertCustomerEntityToDTO(CustomerEntity customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .name(customer.getName())
                .build();
    }

    private CustomerEntity convertCustomerDTOtoEntity(CustomerDTO customerDTO) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    private CustomerEntity updateCustomerNonNullProperty(CustomerDTO customerSource, CustomerEntity customerTarget) {
        if (customerSource.getEmail() != null) {
            customerTarget.setEmail(customerSource.getEmail());
        }
        if (customerSource.getName() != null) {
            customerTarget.setName(customerSource.getName());
        }

        return customerTarget;
    }
}
