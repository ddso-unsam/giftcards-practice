package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Customer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class CustomerRepositoryEnMemoria implements CustomerRepository {

    private final Map<Long, Customer> customers = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public CustomerRepositoryEnMemoria() {
        save(new Customer(null, "Juana Pérez", "Clienta frecuente", "27-33333333-3"));
        save(new Customer(null, "Martín Gómez", "Cliente nuevo", "20-44444444-4"));
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(nextId.getAndIncrement());
        }
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customers.get(id));
    }
}
