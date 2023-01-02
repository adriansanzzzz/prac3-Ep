package citizenmanagementplatform.interfaces;

import citizenmanagementplatform.exceptions.ProceduralException;
import exceptions.*;
import org.junit.jupiter.api.Test;
import services.exceptions.NotValidPINException;

public interface UnifiedPlatformTestInterface {

    @Test
    void NifNotRegisteredException();
    @Test
    void IncorrectValDateException() throws WrongNifFormatException;
    @Test
    void NotValidPINExceptionTest() throws WrongSmallCodeFormatException;
    @Test
    void IncompleteFormExceptionTest();
    @Test
    void IncorrectVerificationExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat;
    @Test
    void NotValidPaymentDataExceptionTest() throws WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, WrongNifFormatException, ProceduralException;
    @Test
    void InsufficientBalanceExceptionTest() throws WrongNifFormatException, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat;
    @Test
    void BadPathExceptionTest() throws WrongNifFormatException, WrongGoalTypeException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, NotValidPINException, ProceduralException;
    @Test
    void DigitalSignatureExceptionTest();


    }






