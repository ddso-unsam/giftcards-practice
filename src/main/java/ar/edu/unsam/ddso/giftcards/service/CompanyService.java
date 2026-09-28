package ar.edu.unsam.ddso.giftcards.service;

import ar.edu.unsam.ddso.giftcards.dto.CompanyCreateRequestDTO;
import ar.edu.unsam.ddso.giftcards.dto.CompanyResponseDTO;
import ar.edu.unsam.ddso.giftcards.exception.CompanyNotFoundException;
import ar.edu.unsam.ddso.giftcards.model.Company;
import ar.edu.unsam.ddso.giftcards.repository.CompanyRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // ---------- Operaciones ----------

    @Transactional
    public CompanyResponseDTO create(CompanyCreateRequestDTO request) {
        Company company =
                new Company(
                        null,
                        request.name(),
                        request.description(),
                        request.cuil(),
                        request.notificationUrl());
        Company saved = companyRepository.save(company);
        return toResponse(saved);
    }

    // ---------- Consultas ----------

    public CompanyResponseDTO findById(Long id) {
        Company company =
                companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        return toResponse(company);
    }

    // ---------- Privados ----------

    private CompanyResponseDTO toResponse(Company company) {
        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getCuil(),
                company.getNotificationUrl());
    }
}
