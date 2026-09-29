package ar.edu.unsam.ddso.giftcards.service;

import ar.edu.unsam.ddso.giftcards.dto.CustomerCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.CustomerResponseDTO;
import ar.edu.unsam.ddso.giftcards.exception.CustomerNotFoundException;
import ar.edu.unsam.ddso.giftcards.model.Customer;
import ar.edu.unsam.ddso.giftcards.repository.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ---------- Operaciones ----------

    @Transactional
    public CustomerResponseDTO create(CustomerCreateRequestDTO request) {
        Customer customer =
                new Customer(null, request.name(), request.description(), request.cuil());
        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }

    // ---------- Consultas ----------

    public CustomerResponseDTO findById(Long id) {
        Customer customer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException(id));
        return toResponse(customer);
    }

    // ---------- Privados ----------

    private CustomerResponseDTO toResponse(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getDescription(),
                customer.getCuil());
    }
}
