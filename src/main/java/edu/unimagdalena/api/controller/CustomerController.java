package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.entities.dto.CustomerDTO;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.service.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.create(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO customer = customerService.update(customerDTO, id);
            return ResponseEntity.ok().body(customer);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            customerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        try {
            CustomerDTO customerDTO = customerService.getCustomerById(id);
            return ResponseEntity.ok().body(customerDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable("email") String email) {
        try {
            CustomerDTO customer = customerService.getCustomerByEmail(email);
            return ResponseEntity.ok().body(customer);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByAddress(@PathVariable("address") String address) {
        try {
            List<CustomerDTO> customers = customerService.getCustomersByAddress(address);
            return ResponseEntity.ok().body(customers);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByNameStartsWith(@PathVariable("name") String name) {
        try {
            List<CustomerDTO> customers = customerService.getCustomersByNameStartsWith(name);
            return ResponseEntity.ok().body(customers);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
