package services;

import data.Goal;
import exceptions.DigitalSignatureException;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertf;

import java.net.ConnectException;

public interface JusticeMinistry {
    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g)
            throws DigitalSignatureException, ConnectException;
}
