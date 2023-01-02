package publicadministration.interfaces;

import data.exceptions.CrimConvictionsDateException;
import org.junit.jupiter.api.Test;
import publicadministration.CrimConviction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CrimConvintionTestInterface {
    @Test
    void getOffenseTest();

    @Test
    void getSentenceTest();

    @Test
    void getDateTest();

    @Test
    default void NullOffenseTest()  {
        var dategood = LocalDate.of(2020, 12, 12);
        var sentence = "sentence";
        assertThrows(NullPointerException.class, () -> new CrimConviction(dategood, null, sentence));
    }

    @Test
    default void NullDateTest()  {
        var sentence = "sentence";
        var offense = "crimConviction";
        assertThrows(NullPointerException.class, () -> new CrimConviction(null, offense, sentence));
    }

    @Test
    default void NullSentenceTest()  {
        var dategood = LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        assertThrows(NullPointerException.class, () -> new CrimConviction(dategood, offense, null));
    }

    @Test
    default void FutureDataTest(){
        var dategood = LocalDate.of(2080, 12, 12);
        var sentence = "sentence";
        var offense = "crimConviction";
        assertThrows(CrimConvictionsDateException.class, () -> new CrimConviction(dategood, offense, sentence));

    }
}
