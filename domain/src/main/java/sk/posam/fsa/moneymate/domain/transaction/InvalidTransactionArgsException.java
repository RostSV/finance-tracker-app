package sk.posam.fsa.moneymate.domain.transaction;

public class InvalidTransactionArgsException extends Exception {
    public InvalidTransactionArgsException(String message) {
        super(message);
    }
}