package data;

import data.interfaces.DigitalSignatureTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalSignatureTest implements DigitalSignatureTestInterface {
    DigitalSignature digitalSignature;

    @BeforeEach
    public void setUp() {
        byte[] correctDigitalSignature = new byte[]{1, 2, 3, 4, 5, 6};
        digitalSignature = new DigitalSignature(correctDigitalSignature);
    }

    @Test
    @Override
    public void getDigitalSignatureTest() {
        byte[] correctDigitalSignature = new byte[]{1, 2, 3, 4, 5, 6};
        DigitalSignature digitalSignature = new DigitalSignature(correctDigitalSignature);
        assertEquals(correctDigitalSignature, digitalSignature.getDigitalSignature());
    }
}