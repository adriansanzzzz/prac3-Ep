package publicadministration;


import data.DigitalSignature;
import data.Goal;
import data.Nif;
import exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CrimRecordTestInterface;

import java.time.LocalDate;


public class CriminalRecordCerfTest implements CrimRecordTestInterface {
    CriminalRecordCertf criminalRecordCertf;


    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongGoalTypeException, CrimConvictionsDateException, RepeatedCrimConvictionException {
        var nif = new Nif("81871430D");
        var name = "Javier";
        var goal = new Goal("WORKWITHMINORS", "descp","3");
        var digSign = new DigitalSignature(new byte[]{1, 2, 3, 4, 5});

        var crimConvs = new CrimConvictionsColl();
        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        var crimConviction = new CrimConviction(dategood, offense, sentence);
        crimConvs.addCriminalConviction(crimConviction);

        criminalRecordCertf = new CriminalRecordCertf(nif, name, goal, digSign, crimConvs);
        }

    @Override
    @Test
    public void getCrimeRecordNameTest(){
        Assertions.assertEquals("Javier", criminalRecordCertf.getName());
    }
    @Test
    public void getCrimeRecordNifTest() throws WrongNifFormatException {
        var nif = new Nif("81871430D");

        Assertions.assertEquals(nif, criminalRecordCertf.getNif());
    }
    @Test
    public void getCrimeRecordGoalTest() throws WrongGoalTypeException {
        var goal = new Goal("WORKWITHMINORS", "descp","3");

        Assertions.assertEquals(goal, criminalRecordCertf.getGoal());
    }
    @Test
    public void getCrimeRecordDigSignTest(){
        var digSign = new DigitalSignature(new byte[]{1, 2, 3, 4, 5});
        Assertions.assertEquals(digSign, criminalRecordCertf.getDigSign());
    }
    @Test
    public void getCrimeRecordCrimConvCollTest() throws CrimConvictionsDateException, RepeatedCrimConvictionException {
        var crimConvs = new CrimConvictionsColl();
        var dategood=  LocalDate.of(2020, 12, 12);
        var offense = "crimConviction";
        var sentence = "sentence";
        var crimConviction = new CrimConviction(dategood, offense, sentence);
        crimConvs.addCriminalConviction(crimConviction);
        Assertions.assertEquals(crimConvs.getconv().size(), criminalRecordCertf.getCrimConvs().size());



    }
}