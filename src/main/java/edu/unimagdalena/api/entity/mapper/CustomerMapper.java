package edu.unimagdalena.api.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entity.Customer;
import edu.unimagdalena.api.entity.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDto(Customer customer);

    @Mapping(target = "orders", ignore = true)
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}
