package exceptions;


public class WrongPasswordFormatException extends Exception {
    public WrongPasswordFormatException(String s) {
        super(s);
    }
}