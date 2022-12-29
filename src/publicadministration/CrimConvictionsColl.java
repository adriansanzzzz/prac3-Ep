package publicadministration;

import exceptions.RepeatedCrimConvictionException;
import exceptions.WrongDateFormatException;

import java.time.LocalDate;
import java.util.ArrayList;

public class CrimConvictionsColl {
    private final ArrayList<CrimConviction> crimConvictions;


    public CrimConvictionsColl() {
        this.crimConvictions = new ArrayList<>();
    }

    // Adds a criminal conviction
    public void addCriminalConviction(CrimConviction conv) throws RepeatedCrimConvictionException {
        if(conv == null) throw new NullPointerException(" Conviction no puede ser null.");
        if(repeatedconv(conv)) throw new RepeatedCrimConvictionException("Conviction ya existe.");

        crimConvictions.add(conv);
    }

    private boolean repeatedconv(CrimConviction conv) {
        for(CrimConviction crmConv : crimConvictions) {
            if(conv.equals(crmConv)) {
                return true;
            }
        }
        return false;
    }

    public CrimConviction getCriminalConviction(LocalDate date) throws WrongDateFormatException {
        if(date == null) throw new NullPointerException("Date no puede ser null.");
        if(date.isAfter(LocalDate.now())) throw new WrongDateFormatException("Date no puede ser posterior a la fecha actual.");
        for(CrimConviction conv : crimConvictions) {
            if(conv.getCommitDate().equals(date)) {
                return conv;
            }
        }

        return null;
    }


    public String toString() {
        StringBuilder crimConvictionsStr = new StringBuilder("Crim convictions: ");
        for (CrimConviction crmC : crimConvictions) {
            crimConvictionsStr.append(crmC.toString());
        }

        return crimConvictionsStr.toString();
    }
}

