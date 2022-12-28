package exceptions;

public class WrongCreditCardException extends Exception {
    public WrongCreditCardException(String message) {
        super(message);
    }
}