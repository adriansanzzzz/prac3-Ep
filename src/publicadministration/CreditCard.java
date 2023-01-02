package publicadministration;

import data.Nif;
import data.SmallCode;
import exceptions.WrongCreditCardDataException;
import exceptions.WrongCreditCardExceptionFormat;
import exceptions.WrongCreditCardLengthException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCard {
    // The payment information for using the card via internet
    private Nif nif; // The nif of the user
    private String cardNumb; // The number of the credit card
    private LocalDate expirDate; // The expiration date for the credit card
    private SmallCode svc; // The Safe Verification Code
    private BigDecimal balance;

    public CreditCard (Nif nif, String cNum, LocalDate d, SmallCode c) throws WrongCreditCardLengthException, WrongCreditCardExceptionFormat, WrongCreditCardDataException {
        checkCreditCard(cNum, d, c);
        this.nif = nif;
        this.cardNumb = cNum;
        this.expirDate = d;
        this.svc = c;
        this.balance = new BigDecimal(1000);
    }
    public CreditCard() {
        this.nif = null;
        this.cardNumb = null;
        this.expirDate = null;
        this.svc = null;
        this.balance = new BigDecimal(1000);
    }

    void checkCreditCard(String cNum, LocalDate d, SmallCode c) throws  WrongCreditCardExceptionFormat, WrongCreditCardLengthException, WrongCreditCardDataException {
        if (cNum == null) {
            throw new NullPointerException(" credit card number es null");
        }
        if (cNum.length() != 16) {
            throw new WrongCreditCardLengthException(" credit card number no tiene 16 digits");
        }
        if(d==null){
            throw new NullPointerException(" expiration date es null");
        }
        if(c==null){
            throw new NullPointerException("Svc es null");
        }
        //if cnum is not a number
        if (!cNum.matches("[0-9]+")) {
            throw new WrongCreditCardExceptionFormat(" credit card number is no es un  numero");
        }
        //if expiration date is not valid (before today)
        if (d.isBefore(LocalDate.now())) {
            throw new WrongCreditCardDataException("la fecha de expiracion no es valida");
        }


        }

    public Nif getNif() {
        return nif;
    }
    public String getCardNumb() {
        return cardNumb;
    }
    public LocalDate getExpirDate() {
        return expirDate;
    }
    public SmallCode getSvc() {
        return svc;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal Balance) {
        this.balance = Balance;
    }
    public void setNif(Nif nif) {
        this.nif = nif;
    }
    public void setCardNumb(String cardNumb) {
        this.cardNumb = cardNumb;
    }
    public void setExpirDate(LocalDate expirdate) {
        this.expirDate = expirdate;
    }
    public void setSvc(SmallCode svc) {
        this.svc = svc;
    }


    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return nif.equals(that.nif) &&
                cardNumb.equals(that.cardNumb) &&
                expirDate.equals(that.expirDate) &&
                svc.equals(that.svc);

    }

    @Override
    public String toString () {
    return "CreditCard: " + this.nif + " " + this.cardNumb + " " + this.expirDate + " " + this.svc;
    } // converts to String



}
