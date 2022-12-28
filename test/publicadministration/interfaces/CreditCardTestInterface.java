package publicadministration.interfaces;

import data.Nif;
import data.SmallCode;
import exceptions.*;
import org.junit.jupiter.api.Test;
import publicadministration.CreditCard;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CreditCardTestInterface {
    @Test
    void getCardNumb();
    @Test
    void getExpirDate();
    @Test
    default void WrongCreditCardLengthExceptionTest() throws WrongSmallCodeFormatException, WrongNifFormatException {
        var cardNumb = "333333333333";
        var expirDate=  LocalDate.of(2025, 12, 12);
        var svc = new SmallCode("123");
        var nif = new Nif("49255398R");
        assertThrows(WrongCreditCardLengthException.class, () -> new CreditCard(nif, cardNumb, expirDate, svc));
    }
    @Test
    default void WrongCreditCardExceptionFormatTest() throws WrongSmallCodeFormatException, WrongNifFormatException {
        var cardNumb = "33333333333343BA";
        var expirDate=  LocalDate.of(2025, 12, 12);
        var svc = new SmallCode("123");
        var nif = new Nif("49255398R");
        assertThrows(WrongCreditCardExceptionFormat.class, () -> new CreditCard(nif, cardNumb, expirDate, svc));
    }
    @Test
    default void WrongCreditCardDataExceptionTest() throws WrongSmallCodeFormatException, WrongNifFormatException {
        var cardNumb = "3333333333334321";
        var expirDate=  LocalDate.of(2012, 12, 12);
        var svc = new SmallCode("123");
        var nif = new Nif("49255398R");
        assertThrows(WrongCreditCardDataException.class, () -> new CreditCard(nif, cardNumb, expirDate, svc));
    }
    @Test
    default void DateIsNullTest() throws WrongSmallCodeFormatException, WrongNifFormatException {
        var cardNumb = "3333333333334321";
        var svc = new SmallCode("123");
        var nif = new Nif("49255398R");
        assertThrows(NullPointerException.class, () -> new CreditCard(nif, cardNumb, null, svc));
    }
}
