package services.interfaces;

import data.*;
import exceptions.*;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.IncorrectValDateException;
import services.exceptions.NifNotRegisteredException;
import services.exceptions.NotValidPINException;

import java.net.ConnectException;
import java.util.Date;


public interface CertificationAuthority {
    boolean sendPIN(Nif nif, Date valD) throws WrongNifFormatException, NifNotRegisteredException, AnyMobileRegisteredException, IncorrectValDateException, ConnectException;

    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException,
            ConnectException;
}
