package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Customer;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile({"local", "prod"})
public interface CustomerRepositoryJpa extends JpaRepository<Customer, Long>, CustomerRepository {}
