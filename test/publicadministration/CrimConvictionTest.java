package publicadministration;

import exceptions.CrimConvictionsDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CrimConvintionTestInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrimConvictionTest implements CrimConvintionTestInterface {
    CrimConviction crimConvictions;

    @BeforeEach
    @Test
    public void setUp() throws CrimConvictionsDateException {
        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        crimConvictions = new CrimConviction(dategood, offense, sentence);
    }

    @Override
    @Test
    public void getOffenseTest() {
        assertEquals("crimConviction", crimConvictions.getOffense());
    }
    @Override
    @Test
    public void getSentenceTest() {
        assertEquals("sentence", crimConvictions.getSentence());
    }
    @Override
    @Test
    public void getDateTest() {
        var dategood=  LocalDate.of(2020, 12, 12);
        assertEquals(dategood, crimConvictions.getCommitDate());
    }

}
