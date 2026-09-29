package ar.edu.unsam.ddso.giftcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.unsam.ddso.giftcards.dto.GiftCardCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.GiftCardResponseDTO;
import ar.edu.unsam.ddso.giftcards.exception.CompanyNotFoundException;
import ar.edu.unsam.ddso.giftcards.exception.CustomerNotFoundException;
import ar.edu.unsam.ddso.giftcards.exception.GiftCardNotFoundException;
import ar.edu.unsam.ddso.giftcards.model.Company;
import ar.edu.unsam.ddso.giftcards.model.Customer;
import ar.edu.unsam.ddso.giftcards.model.GiftCard;
import ar.edu.unsam.ddso.giftcards.model.enums.GiftCardStatus;
import ar.edu.unsam.ddso.giftcards.repository.CompanyRepository;
import ar.edu.unsam.ddso.giftcards.repository.CustomerRepository;
import ar.edu.unsam.ddso.giftcards.repository.GiftCardRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GiftCardServiceTest {

    @Mock private GiftCardRepository giftCardRepository;
    @Mock private CompanyRepository companyRepository;
    @Mock private CustomerRepository customerRepository;

    @Test
    void createsActiveGiftCard() {
        // Given
        GiftCardService giftCardService = newService();
        Company company = new Company(2L, "Acme", "Retail", "30-11111111-1", null);
        Customer customer = new Customer(3L, "Juana", "Frecuente", "27-33333333-3");
        when(companyRepository.findById(2L)).thenReturn(Optional.of(company));
        when(customerRepository.findById(3L)).thenReturn(Optional.of(customer));
        when(giftCardRepository.save(any(GiftCard.class)))
                .thenAnswer(
                        invocation -> {
                            GiftCard giftCard = invocation.getArgument(0);
                            giftCard.setId(1L);
                            return giftCard;
                        });
        GiftCardCreateRequestDTO request =
                new GiftCardCreateRequestDTO(3L, 2L, new BigDecimal("1500.00"));

        // When
        GiftCardResponseDTO response = giftCardService.create(request);

        // Then
        assertEquals(1L, response.id());
        assertEquals(3L, response.customerId());
        assertEquals(2L, response.companyId());
        assertEquals(GiftCardStatus.ACTIVE, response.status());
        assertEquals(new BigDecimal("1500.00"), response.amount());
        assertNotNull(response.creationDate());
    }

    @Test
    void throwsWhenCompanyDoesNotExist() {
        // Given
        GiftCardService giftCardService = newService();
        when(companyRepository.findById(2L)).thenReturn(Optional.empty());
        GiftCardCreateRequestDTO request = new GiftCardCreateRequestDTO(3L, 2L, BigDecimal.TEN);

        // When / Then
        assertThrows(CompanyNotFoundException.class, () -> giftCardService.create(request));
        verify(giftCardRepository, never()).save(any(GiftCard.class));
    }

    @Test
    void throwsWhenCustomerDoesNotExist() {
        // Given
        GiftCardService giftCardService = newService();
        Company company = new Company(2L, "Acme", "Retail", "30-11111111-1", null);
        when(companyRepository.findById(2L)).thenReturn(Optional.of(company));
        when(customerRepository.findById(3L)).thenReturn(Optional.empty());
        GiftCardCreateRequestDTO request = new GiftCardCreateRequestDTO(3L, 2L, BigDecimal.TEN);

        // When / Then
        assertThrows(CustomerNotFoundException.class, () -> giftCardService.create(request));
        verify(giftCardRepository, never()).save(any(GiftCard.class));
    }

    @Test
    void findsExistingGiftCardById() {
        // Given
        GiftCardService giftCardService = newService();
        Company company = new Company(2L, "Acme", "Retail", "30-11111111-1", null);
        Customer customer = new Customer(3L, "Juana", "Frecuente", "27-33333333-3");
        GiftCard giftCard =
                new GiftCard(
                        1L,
                        customer,
                        company,
                        LocalDateTime.now(),
                        new ArrayList<>(),
                        GiftCardStatus.ACTIVE,
                        BigDecimal.TEN);
        when(giftCardRepository.findById(1L)).thenReturn(Optional.of(giftCard));

        // When
        GiftCardResponseDTO response = giftCardService.findById(1L);

        // Then
        assertEquals(1L, response.id());
        assertEquals(3L, response.customerId());
        assertEquals(2L, response.companyId());
    }

    @Test
    void throwsWhenGiftCardDoesNotExist() {
        // Given
        GiftCardService giftCardService = newService();
        when(giftCardRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(GiftCardNotFoundException.class, () -> giftCardService.findById(99L));
    }

    private GiftCardService newService() {
        return new GiftCardService(giftCardRepository, companyRepository, customerRepository);
    }
}
