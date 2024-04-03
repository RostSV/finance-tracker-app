package sk.posam.fsa.moneymate.domain.exceptions;

public class InstanceAlreadyExistsException extends RuntimeException {
    public InstanceAlreadyExistsException() {
    }

    public InstanceAlreadyExistsException(String message) {
        super(message);
    }
}
