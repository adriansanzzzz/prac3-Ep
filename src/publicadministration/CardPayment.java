package publicadministration;

import data.Nif;
import exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardPayment {
    private final String reference; // The code of the operation
    private final Nif nif; // The nif of the user
    private final LocalDateTime date; // The date of the operation
    private final BigDecimal importofpayament; // The import of the payment

    public CardPayment (int ref, Nif nif, LocalDateTime d, BigDecimal imp) throws ImportnegativePayamentException, NullCardPayamentException {
        checkCardPayment(imp);
        this.reference = generateReference();
        this.nif = nif;
        this.date = d;
        this.importofpayament = imp;
    }

    private void checkCardPayment( BigDecimal imp) throws ImportnegativePayamentException, NullCardPayamentException {
        if (imp == null) {
            throw new NullCardPayamentException("The import of the payment is null");
        }
        //comprobar que el importe es positivo
        if(imp.compareTo(BigDecimal.ZERO) < 0) {
            throw new ImportnegativePayamentException("El importe es negativo");
        }

    }

    public String generateReference() {
        var ref= new StringBuilder();
        var random= new java.util.Random();
        for (int i = 0; i < 9; i++) {
            ref.append(random.nextInt(10));
        }
        return ref.toString();
    }



    public String getReference() {
        return reference;
    }
    public Nif getNif() {
        return nif;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public BigDecimal getImportofpayament() {
        return importofpayament;
    }

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardPayment cardPayment = (CardPayment) o;
        return reference.equals(cardPayment.reference) && nif.equals(cardPayment.nif) && date.equals(cardPayment.date) && importofpayament.equals(cardPayment.importofpayament);
    }

    @Override
    public int hashCode() {
        return reference.hashCode();
    }
    @Override
    public String toString () {
        return "CardPayment: " + this.reference + " " + this.nif + " " + this.date + " " + this.importofpayament;
    }
}
