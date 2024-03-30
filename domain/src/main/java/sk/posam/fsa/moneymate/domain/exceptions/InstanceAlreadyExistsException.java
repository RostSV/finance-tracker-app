package sk.posam.fsa.moneymate.domain.exceptions;

public class InstanceAlreadyExistsException extends RuntimeException {

    public InstanceAlreadyExistsException(String message) {
        super(message);
    }
}
