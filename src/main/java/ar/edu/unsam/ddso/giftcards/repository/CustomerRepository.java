package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);
}
