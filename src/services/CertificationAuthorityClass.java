package services;

import data.Nif;
import data.Password;
import citizenmanagementplatform.exceptions.NotValidCredException;
import publicadministration.Citizen;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.NifNotRegisteredException;
import services.exceptions.NotValidPINException;

import java.net.ConnectException;
import java.time.LocalDate;

public class CertificationAuthorityClass implements services.interfaces.CertificationAuthority {
    Citizen citizen;

    public CertificationAuthorityClass(Citizen citizen) {
        this.citizen = citizen;
    }
    @Override
    public boolean sendPIN(Nif nif, LocalDate valD) throws services.exceptions.NifNotRegisteredException, AnyMobileRegisteredException, services.exceptions.IncorrectValDateException, ConnectException {
        if(!citizen.getNif().equals(nif)) throw new services.exceptions.NifNotRegisteredException("NIF not registered");
        if(!citizen.getValidationDate().equals(valD)) throw new services.exceptions.IncorrectValDateException("Incorrect validation date");
        return true;
    }

    @Override
    public boolean checkPIN(data.Nif nif, data.SmallCode pin) throws services.exceptions.NotValidPINException, ConnectException {
        if (!citizen.getPin(nif).equals(pin)) throw new NotValidPINException("PIN not valid para el NIF " + nif);
        return true;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws
            NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {
        return 0;
    }


}

