package ar.edu.unsam.ddso.giftcards.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("Company not found with id " + id);
    }
}
