package ar.edu.unsam.ddso.giftcards.repository;

import ar.edu.unsam.ddso.giftcards.model.Company;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("test")
public class CompanyRepositoryEnMemoria implements CompanyRepository {

    private final Map<Long, Company> companies = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public CompanyRepositoryEnMemoria() {
        save(
                new Company(
                        null,
                        "Acme",
                        "Retail de electrodomésticos",
                        "30-11111111-1",
                        "https://acme.example.com/webhooks/giftcards"));
        save(new Company(null, "Bookish", "Cadena de librerías", "30-22222222-2", null));
    }

    @Override
    public Company save(Company company) {
        if (company.getId() == null) {
            company.setId(nextId.getAndIncrement());
        }
        companies.put(company.getId(), company);
        return company;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.ofNullable(companies.get(id));
    }

    @Override
    public boolean existsByCuil(String cuil) {
        return companies.values().stream().anyMatch(company -> company.getCuil().equals(cuil));
    }
}
