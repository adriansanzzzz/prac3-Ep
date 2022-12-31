package services.interfaces;

import data.Goal;
import exceptions.DigitalSignatureException;
import exceptions.WrongCreditCardNumberException;
import exceptions.WrongCreditCardSVCformatException;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertf;

import java.net.ConnectException;

public interface JusticeMinistry {
    boolean verifyCreditCard(String creditCardNumber, String creditCardSVC)
            throws WrongCreditCardNumberException, WrongCreditCardSVCformatException;

    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g)
            throws DigitalSignatureException, ConnectException, WrongCreditCardNumberException;
}

