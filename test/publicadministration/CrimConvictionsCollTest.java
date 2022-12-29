package publicadministration;

import exceptions.CrimConvictionsDateException;
import exceptions.RepeatedCrimConvictionException;
import exceptions.WrongDateFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CrimConvictionsCollTestInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrimConvictionsCollTest implements CrimConvictionsCollTestInterface {
    CrimConvictionsColl crimConvictionsColl;

    @BeforeEach
    public void setUp() throws CrimConvictionsDateException, RepeatedCrimConvictionException {
        crimConvictionsColl = new CrimConvictionsColl();
        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        var crimConviction = new CrimConviction(dategood, offense, sentence);
        crimConvictionsColl.addCriminalConviction(crimConviction);

    }

    @Test
    public void addandgetCriminalConvictionTest() throws CrimConvictionsDateException, WrongDateFormatException {
        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        var crimConviction = new CrimConviction(dategood, offense, sentence);
        assertEquals(crimConviction, crimConvictionsColl.getCriminalConviction(dategood));

    }


}
