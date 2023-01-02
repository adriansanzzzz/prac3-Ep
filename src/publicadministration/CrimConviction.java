package publicadministration;

import exceptions.CrimConvictionsDateException;

import java.time.LocalDate;
import java.util.Objects;

public class CrimConviction
{
    private final LocalDate commitDate;
    private final String offense;
    private final String sentence;

    public CrimConviction(LocalDate commitdate, String offe, String sent) throws CrimConvictionsDateException {
        checkCrimConvictions(commitdate, offe, sent);
        this.commitDate = commitdate;
        this.offense = offe;
        this.sentence = sent;
    }



    void checkCrimConvictions(LocalDate com, String off, String dat) throws CrimConvictionsDateException {
        if (com == null) throw new NullPointerException("La fecha de comisión del delito no puede ser nula.");
        if (off == null) throw new NullPointerException("El delito no puede ser nulo.");
        if (dat == null) throw new NullPointerException("La sentencia no puede ser nula.");
        if(com.isAfter(LocalDate.now())) throw new CrimConvictionsDateException("La fecha de comisión del delito no puede ser posterior a la fecha actual.");
    }

    public LocalDate getCommitDate() {
        return commitDate;
    }
    public String getOffense() {
        return offense;
    }
    public String getSentence() {
        return sentence;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrimConviction that = (CrimConviction) o;
        return commitDate.equals(that.commitDate) && offense.equals(that.offense) && sentence.equals(that.sentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commitDate, offense, sentence);
        }
    }


