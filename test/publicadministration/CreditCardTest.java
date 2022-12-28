package publicadministration;

import data.Nif;
import data.SmallCode;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CreditCardTestInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest implements CreditCardTestInterface {
    CreditCard creditCard;

    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardExceptionFormat, WrongCreditCardDataException {

        var nif = new Nif("49255398R");
        var cardNumb = "3333333333334321";
        var svc = new SmallCode("123");
        var dategood=  LocalDate.of(2025, 12, 12);
        creditCard = new CreditCard(nif, cardNumb, dategood, svc);
    }

    @Override
    @Test
    public void getCardNumb() {
        assertEquals("3333333333334321", creditCard.getCardNumb());
    }

    @Override
    @Test
    public void getExpirDate() {
        var dategood=  LocalDate.of(2025, 12, 12);
        assertEquals(dategood, creditCard.getExpirDate());
    }
}
