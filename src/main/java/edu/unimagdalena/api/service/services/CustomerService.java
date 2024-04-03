package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.entities.dto.CustomerDTO;

public interface CustomerService {

    // CRUD

    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long customerId);

    CustomerDTO update(CustomerDTO customerDTO, Long customerId);

    void delete(Long customerId);

    // Others methods

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerByEmail(String email);

    List<CustomerDTO> getCustomersByAddress(String address);

    List<CustomerDTO> getCustomersByNameStartsWith(String productName);

}
