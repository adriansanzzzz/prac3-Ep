package services;

import data.Nif;
import data.Password;
import exceptions.NotValidCredException;
import publicadministration.Citizen;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.NifNotRegisteredException;

import java.net.ConnectException;

public class CertificationAuthorityClass implements services.interfaces.CertificationAuthority {
    Citizen citizen;

    public CertificationAuthorityClass(Citizen citizen) {
        this.citizen = citizen;
    }
    @Override
    public boolean sendPIN(data.Nif nif, java.time.LocalDate valD) throws services.exceptions.NifNotRegisteredException, services.exceptions.AnyMobileRegisteredException, services.exceptions.IncorrectValDateException, java.net.ConnectException {
        if(!citizen.getNif().equals(nif)) throw new services.exceptions.NifNotRegisteredException("NIF not registered");
        if(!citizen.getValidationDate().equals(valD)) throw new services.exceptions.IncorrectValDateException("Incorrect validation date");
        if(!citizen.hasMobile()) throw new services.exceptions.AnyMobileRegisteredException("No mobile registered");
        return true;
    }

    @Override
    public boolean checkPIN(data.Nif nif, data.SmallCode pin) throws services.exceptions.NotValidPINException, java.net.ConnectException {
        return true;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw) throws
            NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException {
        return 0;
    }


}

