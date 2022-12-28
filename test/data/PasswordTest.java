package data;

import exceptions.WrongPasswordFormatException;
import data.interfaces.PasswordTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest implements PasswordTestInterface {
    Password password;

    @BeforeEach
    public void setUp() throws WrongPasswordFormatException {
        password = new Password("$contraseñaejemplo123");
    }

    @Test
    @Override
    public void getPasswordTest() {
        assertEquals("$contraseñaejemplo123", password.getPassword());
    }
}