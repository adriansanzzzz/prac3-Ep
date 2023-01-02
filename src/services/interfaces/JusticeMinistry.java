package services.interfaces;

import data.Goal;
import citizenmanagementplatform.exceptions.DigitalSignatureException;
import data.exceptions.WrongCreditCardNumberException;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertf;

import java.net.ConnectException;

public interface JusticeMinistry {
    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g)
            throws DigitalSignatureException, ConnectException, WrongCreditCardNumberException;
}

