package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import data.Goal;
import data.Nif;
import data.SmallCode;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import services.exceptions.IncorrectValDateException;
import services.exceptions.InsufficientBalanceException;
import services.exceptions.NifNotRegisteredException;
import services.exceptions.NotValidPaymentDataException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformTest {
    UnifiedPlatform unifiedPlatform;
    Citizen citizen;


    @BeforeEach
    void setUp() throws WrongNifFormatException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, IncompleteFormException, InsufficientBalanceException, ConnectException {
        unifiedPlatform = new UnifiedPlatform();
        citizen = new Citizen();
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var correctcreditcard = new CreditCard(correctnif,"3333333333334321",correctvalD,new data.SmallCode("123"));


        unifiedPlatform.initialize_citz(correctnif, correctvalD);
        unifiedPlatform.setpin(new SmallCode("123"));


    }
    @Test
    public void NifNotRegisteredException() throws WrongNifFormatException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFandPINobt(new Nif("49255398R"), citizen.getValidationDate());
                });
    }



    @Test
    public void IncorrectValDateException() throws WrongNifFormatException {
        var baddate = LocalDate.of(1999, 1, 1);

        var correctnif = new Nif("12345678A");
        assertThrows(
                IncorrectValDateException.class,
                () -> {
                    unifiedPlatform.enterNIFandPINobt(correctnif, baddate);
                });


    }
    //enterPin Test
    @Test
    public void NotValidPINExceptionTest () throws WrongSmallCodeFormatException {
        var badpin = new data.SmallCode("125");
        assertThrows(
                services.exceptions.NotValidPINException.class,
                () -> {
                    unifiedPlatform.enterPIN(badpin);
                });
    }

    // enterForm Test
    @Test
    public void IncompleteFormExceptionTest(){
        assertThrows(
                citizenmanagementplatform.exceptions.IncompleteFormException.class,
                () -> {
                    unifiedPlatform.enterForm(null,null);
                });
    }

    @Test
    public void IncorrectVerificationExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        var correctnif = new Nif("12345678A");
        var badcitizen= new Citizen(correctnif,"Name","add","666666666");
        var badgoal= new Goal("PUBLICWORKERS","desc","4");
        assertThrows(
                services.exceptions.IncorrectVerificationException.class,
                () -> {
                    unifiedPlatform.enterForm(badcitizen,badgoal);
                });
    }


    //enterCardData Test
    @Test
    public void NotValidPaymentDataExceptionTest() throws WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, WrongNifFormatException {
        var correctnif = new Nif("12545678A");
        var correctvalD = LocalDate.of(2026, 12, 12);
        var correctcred = new CreditCard(correctnif,"3333333333335321",correctvalD,new data.SmallCode("333"));
        unifiedPlatform.setCreditCard(correctcred);
        var badcreditcard = new CreditCard(correctnif,"3333333333535324",correctvalD,new data.SmallCode("333"));

        assertThrows(
                NotValidPaymentDataException.class,
                () -> {
                    unifiedPlatform.enterCardData(badcreditcard);
                });
    }

    @Test
    public void InsufficientBalanceExceptionTest() throws WrongNifFormatException, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat {
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var correctcreditcard = new CreditCard(correctnif,"3333333333334321",correctvalD,new data.SmallCode("123"));
        correctcreditcard.setBalance(new BigDecimal(0));
        assertThrows(
                InsufficientBalanceException.class,
                () -> {
                    unifiedPlatform.enterCardData(correctcreditcard);
                });
    }







}



