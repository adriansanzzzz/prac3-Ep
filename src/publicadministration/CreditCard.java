package publicadministration;

import data.Nif;
import data.SmallCode;
import exceptions.WrongCreditCardDataException;
import exceptions.WrongCreditCardExceptionFormat;
import exceptions.WrongCreditCardLengthException;

import java.time.LocalDate;

public class CreditCard {
    // The payment information for using the card via internet
    private final Nif nif; // The nif of the user
    private final String cardNumb; // The number of the credit card
    private final LocalDate expirDate; // The expiration date for the credit card
    private final SmallCode svc; // The Safe Verification Code

    public CreditCard (Nif nif, String cNum, LocalDate d, SmallCode c) throws WrongCreditCardLengthException, WrongCreditCardExceptionFormat, WrongCreditCardDataException {
        checkCreditCard(cNum, d, c);
        this.nif = nif;
        this.cardNumb = cNum;
        this.expirDate = d;
        this.svc = c;
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

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard card = (CreditCard) o;
        return cardNumb.equals(card.cardNumb) && expirDate.equals(card.expirDate) && svc.equals(card.svc);
    }

    @Override
    public String toString () {
    return "CreditCard: " + this.nif + " " + this.cardNumb + " " + this.expirDate + " " + this.svc;
    } // converts to String

}
