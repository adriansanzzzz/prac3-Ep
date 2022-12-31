package services.interfaces;

import publicadministration.CreditCard;
import services.exceptions.InsufficientBalanceException;
import services.exceptions.NotValidPaymentDataException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDate;

public interface CAS {// External service that represents the Credit Authorization Service
        boolean askForApproval(String nTrans, CreditCard cardData, LocalDate date, BigDecimal imp) throws NotValidPaymentDataException,
                InsufficientBalanceException, ConnectException;
    }


