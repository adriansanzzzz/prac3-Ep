package services;

import publicadministration.Citizen;

public class CertificationAuthorityClass implements services.interfaces.CertificationAuthority {
    Citizen citizen;

    public CertificationAuthorityClass(Citizen citizen) {
        this.citizen = citizen;
    }
    @Override
    public boolean sendPIN(data.Nif nif, java.time.LocalDate valD) throws exceptions.WrongNifFormatException, services.exceptions.NifNotRegisteredException, services.exceptions.AnyMobileRegisteredException, services.exceptions.IncorrectValDateException, java.net.ConnectException {
        if(nif == null) throw new exceptions.WrongNifFormatException("NIF is null");
        if(valD == null) throw new services.exceptions.IncorrectValDateException("ValDate is null");
        if(!citizen.getNif().equals(nif)) throw new services.exceptions.NifNotRegisteredException("NIF not registered");
        return true;
    }

    @Override
    public boolean checkPIN(data.Nif nif, data.SmallCode pin) throws services.exceptions.NotValidPINException, java.net.ConnectException {
        if(nif == null) throw new services.exceptions.NotValidPINException("NIF is null");
        if(pin == null) throw new services.exceptions.NotValidPINException("PIN is null");
        //check if citien REGISTRED PIN IS EQUAL TO PIN

        return true;
    }

    @Override
    public byte ckeckCredent(data.Nif nif, data.Password passw) throws services.exceptions.NifNotRegisteredException, exceptions.NotValidCredException, services.exceptions.AnyMobileRegisteredException, java.net.ConnectException {
        return 0;
    }
}

