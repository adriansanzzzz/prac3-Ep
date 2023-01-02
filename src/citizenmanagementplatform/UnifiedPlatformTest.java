package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import citizenmanagementplatform.exceptions.ProceduralException;
import data.Goal;
import data.Nif;
import data.SmallCode;
import data.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import services.exceptions.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformTest  {
    UnifiedPlatform unifiedPlatform;
    Citizen citizen;


    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, IncompleteFormException, InsufficientBalanceException, ConnectException, ProceduralException {
        unifiedPlatform = new UnifiedPlatform();
        unifiedPlatform.dissable_procedures();
        citizen = new Citizen();
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var correctmobile = "666666666";


        unifiedPlatform.initialize_citz(correctnif, correctvalD, correctmobile);
        unifiedPlatform.set_pin(new SmallCode("123"));


    }

    @Test
    public void NifNotRegisteredException() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFandPINobt(new Nif("49255398R"), citizen.getValidationDate());
                });
    }


    @Test
    public void IncorrectValDateExceptionTest() throws WrongNifFormatException {
        var baddate = LocalDate.of(1999, 1, 1);

        var correctnif = new Nif("12345678A");
        assertThrows(
                IncorrectValDateException.class,
                () -> {
                    unifiedPlatform.enterNIFandPINobt(correctnif, baddate);
                });


    }

    @Test
    public void AnyMobileRegisteredExceptionTest() throws WrongNifFormatException, WrongSmallCodeFormatException {
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var correctmobile = "666666666";
        unifiedPlatform.initialize_citz(correctnif, correctvalD, null);
        unifiedPlatform.set_pin(new SmallCode("123"));

        assertThrows(
                AnyMobileRegisteredException.class,
                () -> {
                    unifiedPlatform.enterNIFandPINobt(correctnif, correctvalD);
                });


    }

    //enterPin Test
    @Test
    public void NotValidPINExceptionTest() throws WrongSmallCodeFormatException {
        var badpin = new data.SmallCode("125");
        assertThrows(
                services.exceptions.NotValidPINException.class,
                () -> {
                    unifiedPlatform.enterPIN(badpin);
                });
    }

    // enterForm Test
    @Test
    public void IncompleteFormExceptionTest() {
        assertThrows(
                citizenmanagementplatform.exceptions.IncompleteFormException.class,
                () -> {
                    unifiedPlatform.enterForm(null, null);
                });
    }

    @Test
    public void IncorrectVerificationExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        var correctnif = new Nif("12345678A");
        var badcitizen = new Citizen(correctnif, "Name", "add", "666666666");
        var badgoal = new Goal("PUBLICWORKERS", "desc", "4");
        assertThrows(
                services.exceptions.IncorrectVerificationException.class,
                () -> {
                    unifiedPlatform.enterForm(badcitizen, badgoal);
                });
    }


    //enterCardData Test
    @Test
    public void NotValidPaymentDataExceptionTest() throws WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, WrongNifFormatException, ProceduralException {
        var correctnif = new Nif("12545678A");
        var correctvalD = LocalDate.of(2026, 12, 12);
        var correctcred = new CreditCard(correctnif, "3333333333335321", correctvalD, new data.SmallCode("333"));
        unifiedPlatform.set_creditcard_data(correctcred);
        var badcreditcard = new CreditCard(correctnif, "3333333333535324", correctvalD, new data.SmallCode("333"));

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
        var correctcreditcard = new CreditCard(correctnif, "3333333333334321", correctvalD, new data.SmallCode("123"));
        correctcreditcard.setBalance(new BigDecimal(0));
        assertThrows(
                InsufficientBalanceException.class,
                () -> {
                    unifiedPlatform.enterCardData(correctcreditcard);
                });
    }

    @Test
    public void BadPathExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, NotValidPINException, ProceduralException {

        var badgoal = new Goal("PUBLICWORKERS", "desc", "4");
        var badcitz = new Citizen(new Nif("49266398R"), "Name", "add", "666666666");

        unifiedPlatform.set_formdata(badcitz, badgoal);


        assertThrows(
                citizenmanagementplatform.exceptions.BadPathException.class,
                () -> {
                    unifiedPlatform.obtainCertificate();
                });

    }

    @Test
    public void DigitalSignatureExceptionTest() {

        assertThrows(
                citizenmanagementplatform.exceptions.DigitalSignatureException.class,
                () -> {
                    unifiedPlatform.obtainCertificate();
                });

    }

    @Test
    public void ProceduralExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, NotValidPINException, ProceduralException {
        unifiedPlatform.reset_procedures();

        assertThrows(
                ProceduralException.class,
                () -> {
                    unifiedPlatform.selectProcedures();
                });

        unifiedPlatform.reset_procedures();

        assertThrows(
                ProceduralException.class,
                () -> {
                    unifiedPlatform.selectJusMin();
                    unifiedPlatform.selectProcedures();
                    unifiedPlatform.enterPIN(new SmallCode("123"));
                });




    }
}



