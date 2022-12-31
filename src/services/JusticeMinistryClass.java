package services;

import data.Goal;
import exceptions.DigitalSignatureException;
import publicadministration.Citizen;
import publicadministration.CriminalRecordCertf;

public class JusticeMinistryClass implements services.interfaces.JusticeMinistry {
    Citizen persData;
    Goal g;
    public JusticeMinistryClass() {
    }
    @Override
    public CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g)
            throws DigitalSignatureException {
        if(persD == null) throw new DigitalSignatureException("Citizen is null");
        if(g == null) throw new DigitalSignatureException("Goal is null");
        return new CriminalRecordCertf();
    }
}

