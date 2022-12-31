package services;

import publicadministration.CreditCard;
import services.exceptions.InsufficientBalanceException;
import services.exceptions.NotValidPaymentDataException;
import services.interfaces.CAS;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDate;

public class CASClass implements CAS{
    String nTrans;
    CreditCard cardData;
    LocalDate date;
    BigDecimal imp;

    public CASClass(String nTrans, CreditCard cardData, LocalDate date, BigDecimal imp) {
        this.nTrans = nTrans;
        this.cardData = cardData;
        this.date = date;
        this.imp = imp;
    }

    @Override
    public boolean askForApproval(String nTrans, CreditCard cardData, LocalDate date, BigDecimal imp) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException {
        if(cardData == null) throw new NotValidPaymentDataException("Credit card is null");
        if(imp == null) throw new NotValidPaymentDataException("Amount is null");
        if(imp.compareTo(BigDecimal.ZERO) < 0) throw new NotValidPaymentDataException("Amount is negative");
        return true;
    }
}
