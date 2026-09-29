package ar.edu.unsam.ddso.giftcards.exception;

public class GiftCardNotFoundException extends RuntimeException {

    public GiftCardNotFoundException(Long id) {
        super("GiftCard not found with id " + id);
    }
}
