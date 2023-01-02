package publicadministration;

import data.Nif;
import data.exceptions.ImportnegativePayamentException;
import data.exceptions.NullCardPayamentException;
import data.exceptions.WrongNifFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CardPaymentTestInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPayamentTest implements CardPaymentTestInterface {
    CardPayment cardPayment;

    @BeforeEach
    public void setUp() throws WrongNifFormatException, ImportnegativePayamentException, NullCardPayamentException {
        var date = LocalDateTime.now();
        var nif = new Nif("49255398R");
        var impo= new BigDecimal("4.4444444444444444444444");
        cardPayment = new CardPayment(1828383842, nif,date, impo);

    }

    @Override
    @Test
    public void getImportofpayamentTest() {
        var impo= new BigDecimal("4.4444444444444444444444");
        assertEquals(impo, cardPayment.getImportofpayament());
    }

}
