package ar.edu.unsam.ddso.giftcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ar.edu.unsam.ddso.giftcards.dto.CompanyCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.CompanyResponseDTO;
import ar.edu.unsam.ddso.giftcards.exception.CompanyNotFoundException;
import ar.edu.unsam.ddso.giftcards.model.Company;
import ar.edu.unsam.ddso.giftcards.repository.CompanyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock private CompanyRepository companyRepository;

    @Test
    void createsAndReturnsCompany() {
        // Given
        CompanyService companyService = new CompanyService(companyRepository);
        CompanyCreateRequestDTO request =
                new CompanyCreateRequestDTO(
                        "Acme", "Retail", "30-11111111-1", "https://acme.example.com/webhook");
        when(companyRepository.save(any(Company.class)))
                .thenAnswer(
                        invocation -> {
                            Company company = invocation.getArgument(0);
                            company.setId(1L);
                            return company;
                        });

        // When
        CompanyResponseDTO response = companyService.create(request);

        // Then
        assertEquals(1L, response.id());
        assertEquals("Acme", response.name());
        assertEquals("Retail", response.description());
        assertEquals("30-11111111-1", response.cuil());
        assertEquals("https://acme.example.com/webhook", response.notificationUrl());
    }

    @Test
    void findsExistingCompanyById() {
        // Given
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1L, "Acme", "Retail", "30-11111111-1", null);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        // When
        CompanyResponseDTO response = companyService.findById(1L);

        // Then
        assertEquals(1L, response.id());
        assertEquals("Acme", response.name());
    }

    @Test
    void throwsWhenCompanyDoesNotExist() {
        // Given
        CompanyService companyService = new CompanyService(companyRepository);
        when(companyRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(CompanyNotFoundException.class, () -> companyService.findById(99L));
    }
}
