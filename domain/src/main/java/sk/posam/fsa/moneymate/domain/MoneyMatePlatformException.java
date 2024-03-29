package sk.posam.fsa.moneymate.domain;

public class MoneyMatePlatformException extends Exception {

    public MoneyMatePlatformException(String message) {
        super(message);
    }

    public MoneyMatePlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyMatePlatformException(Throwable cause) {
        super(cause);
    }

    public MoneyMatePlatformException() {
        super();
    }
}
