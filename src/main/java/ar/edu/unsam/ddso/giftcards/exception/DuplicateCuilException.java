package ar.edu.unsam.ddso.giftcards.exception;

public class DuplicateCuilException extends RuntimeException {

    public DuplicateCuilException(String cuil) {
        super("A company with cuil " + cuil + " already exists");
    }
}
