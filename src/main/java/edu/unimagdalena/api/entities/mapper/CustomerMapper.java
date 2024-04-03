package edu.unimagdalena.api.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entities.Customer;
import edu.unimagdalena.api.entities.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
