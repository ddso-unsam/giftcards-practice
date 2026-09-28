package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Company;

import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);

    Optional<Company> findById(Long id);
}
