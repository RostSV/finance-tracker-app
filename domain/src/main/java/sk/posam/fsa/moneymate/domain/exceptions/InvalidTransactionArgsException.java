package sk.posam.fsa.moneymate.domain.exceptions;

public class InvalidTransactionArgsException extends RuntimeException {

    public InvalidTransactionArgsException(String message) {
        super(message);
    }
}
