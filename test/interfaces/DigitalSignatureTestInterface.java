package interfaces;

import data.DigitalSignature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface DigitalSignatureTestInterface {
    @Test
    void getDigitalSignatureTest();

    @Test
    default void getNullPointerDigitalSignatureTest() {
        assertThrows(NullPointerException.class,
                () -> new DigitalSignature(null));
    }
}
