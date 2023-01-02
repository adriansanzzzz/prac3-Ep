package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import citizenmanagementplatform.exceptions.NotValidCredException;
import citizenmanagementplatform.exceptions.ProceduralException;
import data.Nif;
import data.Password;
import data.SmallCode;
import data.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;
import services.exceptions.InsufficientBalanceException;
import services.exceptions.NifNotRegisteredException;
import services.exceptions.NotValidPaymentDataException;

import java.net.ConnectException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnifiedPlatformTestClavePermanente {
    UnifiedPlatform unifiedPlatform;
    Citizen citizen;


    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, IncompleteFormException, InsufficientBalanceException, ConnectException, ProceduralException, WrongPasswordFormatException {
        unifiedPlatform = new UnifiedPlatform();
        unifiedPlatform.dissable_procedures();
        citizen = new Citizen();
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var correctmobile = "666666666";
        var pswd = new Password("$contraseñaejemplo123");


        unifiedPlatform.initialize_citz(correctnif, correctvalD, correctmobile, pswd);
        unifiedPlatform.set_pin(new SmallCode("123"));


    }

    @Test
    void NifNotRegisteredExceptionTest_ClavePermanente() {
        assertThrows(
                NifNotRegisteredException.class,
                () -> {
                    var pswd = new Password("$contraseñaejemplo123");
                    unifiedPlatform.enterCred(new Nif("49255399L"), pswd);

                });
    }

    @Test
    void NotValidCredExceptionTest_ClavePermanente() throws WrongNifFormatException {
        var correctnif = new Nif("12345678A");

        assertThrows(
                NotValidCredException.class,
                () -> {
                    var wrongpswd = new Password("$contraseñaejemplo333");
                    unifiedPlatform.enterCred(correctnif, wrongpswd);
                }
        );
    }
    @Test
    void AnyMobileRegisteredExceptionTest_ClavePermanente() throws WrongPasswordFormatException, WrongNifFormatException {
        var correctnif = new Nif("12345678A");
        var correctvalD = LocalDate.of(2025, 12, 12);
        var pswd = new Password("$contraseñaejemplo123");

        unifiedPlatform.initialize_citz(correctnif, correctvalD, null, pswd);
        assertThrows(
                NotValidCredException.class,
                () -> {
                    var wrongpswd = new Password("$contraseñaejemplo333");
                    unifiedPlatform.enterCred(correctnif, wrongpswd);
                }
        );

    }

}
