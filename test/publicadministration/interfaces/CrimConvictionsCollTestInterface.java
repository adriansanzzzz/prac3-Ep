package publicadministration.interfaces;

import data.exceptions.CrimConvictionsDateException;
import data.exceptions.RepeatedCrimConvictionException;
import data.exceptions.WrongDateFormatException;
import org.junit.jupiter.api.Test;
import publicadministration.CrimConviction;
import publicadministration.CrimConvictionsColl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CrimConvictionsCollTestInterface {
    @Test
    void addandgetCriminalConvictionTest() throws CrimConvictionsDateException, WrongDateFormatException;

    @Test
    default void addNullCrimConvictionTest(){
        assertThrows(NullPointerException.class, () -> new CrimConvictionsColl().addCriminalConviction(null));
    }
    @Test
    default void getCrimConvictionsNullTest(){
        assertThrows(NullPointerException.class, () -> new CrimConvictionsColl().getCriminalConviction(null));

    }
    @Test
    default void getCrimConvictionsFutreDataTest(){
        assertThrows(WrongDateFormatException.class, () -> new CrimConvictionsColl().getCriminalConviction(LocalDate.of(2040, 12, 12)));
    }
    @Test
    default void addRepeatConvictionTest() throws CrimConvictionsDateException, RepeatedCrimConvictionException {
        CrimConvictionsColl crimConvictionsColl = new CrimConvictionsColl();

        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        var crimConviction = new CrimConviction(dategood, offense, sentence);
        crimConvictionsColl.addCriminalConviction(crimConviction);
        assertThrows(RepeatedCrimConvictionException.class, () -> crimConvictionsColl.addCriminalConviction(crimConviction));


    }

}
