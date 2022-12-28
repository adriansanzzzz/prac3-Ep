package publicadministration.interfaces;

import data.Nif;
import exceptions.ImportnegativePayamentException;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;
import publicadministration.CardPayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CardPaymentTestInterface {

    @Test
    void getImportofpayamentTest();

    @Test
    default void getImportnegativePayamentExceptionTest() throws WrongNifFormatException {
        var date = LocalDateTime.now();
        var nif = new Nif("49255398R");
        var impo= BigDecimal.valueOf(-4.4444444444444444444444);

        assertThrows(ImportnegativePayamentException.class,
                () -> new CardPayment(1828383842, nif, date, impo));
    }
}


