package services;

import citizenmanagementplatform.exceptions.IncompleteFormException;
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
    public boolean askForApproval(String nTrans, CreditCard cardD, LocalDate date, BigDecimal imp) throws NotValidPaymentDataException, InsufficientBalanceException, ConnectException, IncompleteFormException {

        if (!cardData.getCardNumb().equals(cardD.getCardNumb())) throw new NotValidPaymentDataException("Credit card number is not valid");
        if (!cardD.getNif().equals(cardData.getNif())) throw new NotValidPaymentDataException("NIF card is not valid");
        if (!cardD.getExpirDate().equals(cardData.getExpirDate())) throw new NotValidPaymentDataException("EXPDAT card is not valid");
        if (!cardD.getSvc().equals(cardData.getSvc())) throw new NotValidPaymentDataException("SVC card is not valid");
        if (imp.compareTo(BigDecimal.ZERO) < 0) throw new NotValidPaymentDataException("Amount is negative");

        return true;
    }

    public void setCardData(CreditCard cardData) {
        this.cardData = cardData;
    }
    public void getCardData(LocalDate date) {
        this.date = date;
    }
}
