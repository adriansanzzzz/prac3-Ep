package services.interfaces;

import data.Nif;
import data.SmallCode;
import exceptions.WrongNifFormatException;
import services.exceptions.AnyMobileRegisteredException;
import services.exceptions.IncorrectValDateException;
import services.exceptions.NifNotRegisteredException;
import services.exceptions.NotValidPINException;

import java.net.ConnectException;
import java.time.LocalDate;


public interface CertificationAuthority {

    boolean sendPIN(Nif nif, LocalDate valD) throws WrongNifFormatException, NifNotRegisteredException, AnyMobileRegisteredException, IncorrectValDateException, ConnectException;

    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException,
            ConnectException;

}
