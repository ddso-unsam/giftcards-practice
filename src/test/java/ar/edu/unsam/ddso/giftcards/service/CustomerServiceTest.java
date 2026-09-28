package ar.edu.unsam.ddso.giftcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ar.edu.unsam.ddso.giftcards.dto.CustomerCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.CustomerResponseDTO;
import ar.edu.unsam.ddso.giftcards.exception.CustomerNotFoundException;
import ar.edu.unsam.ddso.giftcards.model.Customer;
import ar.edu.unsam.ddso.giftcards.repository.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock private CustomerRepository customerRepository;

    @Test
    void createsAndReturnsCustomer() {
        // Given
        CustomerService customerService = new CustomerService(customerRepository);
        CustomerCreateRequestDTO request =
                new CustomerCreateRequestDTO("Juana Pérez", "Clienta frecuente", "27-33333333-3");
        when(customerRepository.save(any(Customer.class)))
                .thenAnswer(
                        invocation -> {
                            Customer customer = invocation.getArgument(0);
                            customer.setId(1L);
                            return customer;
                        });

        // When
        CustomerResponseDTO response = customerService.create(request);

        // Then
        assertEquals(1L, response.id());
        assertEquals("Juana Pérez", response.name());
        assertEquals("Clienta frecuente", response.description());
        assertEquals("27-33333333-3", response.cuil());
    }

    @Test
    void findsExistingCustomerById() {
        // Given
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer = new Customer(1L, "Juana Pérez", "Clienta frecuente", "27-33333333-3");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // When
        CustomerResponseDTO response = customerService.findById(1L);

        // Then
        assertEquals(1L, response.id());
        assertEquals("Juana Pérez", response.name());
    }

    @Test
    void throwsWhenCustomerDoesNotExist() {
        // Given
        CustomerService customerService = new CustomerService(customerRepository);
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(99L));
    }
}
