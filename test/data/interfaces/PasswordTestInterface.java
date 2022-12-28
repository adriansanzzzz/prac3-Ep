package data.interfaces;

import data.Password;
import exceptions.WrongPasswordFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public interface PasswordTestInterface {
    @Test
    void getPasswordTest();
    @Test
    default void wrongPasswordTooShortTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "123";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoDigitTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contraseña";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "123456789101112";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNoSpecialCharTest() {
        assertThrows(WrongPasswordFormatException.class,
                () -> {
                    String wrongPassword = "contrasenatest123";
                    new Password(wrongPassword);
                });
    }

    @Test
    default void wrongPasswordNullTest() {
        assertThrows(NullPointerException.class,
                () -> new Password(null));
    }
}
