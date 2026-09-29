package ar.edu.unsam.ddso.giftcards.service;

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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class GiftCardService {

    private final GiftCardRepository giftCardRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    public GiftCardService(
            GiftCardRepository giftCardRepository,
            CompanyRepository companyRepository,
            CustomerRepository customerRepository) {
        this.giftCardRepository = giftCardRepository;
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
    }

    // ---------- Operaciones ----------

    @Transactional
    public GiftCardResponseDTO create(GiftCardCreateRequestDTO request) {
        Company company =
                companyRepository
                        .findById(request.companyId())
                        .orElseThrow(() -> new CompanyNotFoundException(request.companyId()));
        Customer customer =
                customerRepository
                        .findById(request.customerId())
                        .orElseThrow(() -> new CustomerNotFoundException(request.customerId()));
        GiftCard giftCard =
                new GiftCard(
                        null,
                        customer,
                        company,
                        LocalDateTime.now(),
                        new ArrayList<>(),
                        GiftCardStatus.ACTIVE,
                        request.amount());
        GiftCard saved = giftCardRepository.save(giftCard);
        return toResponse(saved);
    }

    // ---------- Consultas ----------

    @Transactional(readOnly = true)
    public GiftCardResponseDTO findById(Long id) {
        GiftCard giftCard =
                giftCardRepository
                        .findById(id)
                        .orElseThrow(() -> new GiftCardNotFoundException(id));
        return toResponse(giftCard);
    }

    // ---------- Privados ----------

    private GiftCardResponseDTO toResponse(GiftCard giftCard) {
        return new GiftCardResponseDTO(
                giftCard.getId(),
                giftCard.getCustomer().getId(),
                giftCard.getCompany().getId(),
                giftCard.getCreationDate(),
                giftCard.getStatus(),
                giftCard.getAmount());
    }
}
