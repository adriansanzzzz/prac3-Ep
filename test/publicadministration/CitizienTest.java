package publicadministration;

import data.Nif;
import exceptions.WrongCitizenMobileNumbFormat;
import exceptions.WrongCitizenMobileNumblength;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import publicadministration.interfaces.CitizienTestInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitizienTest implements CitizienTestInterface {
    Citizen citizen;

    @BeforeEach
    public void setUp() throws WrongNifFormatException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        var nif = new Nif("49255398R");
        var name = "Javier";
        var add = "Calle de la paz, 1,Huesca,22001,Spain";
        var mobile = "666666666";
        citizen = new Citizen(nif, name, add, mobile);

    }

    @Override
    @Test
    public void getCitizenNameTest(){
        assertEquals("Javier", citizen.getName());
    }
    @Override
    @Test
    public void getCitizenAddressTest() {
        assertEquals("Calle de la paz, 1,Huesca,22001,Spain", citizen.getAddress());
    }
    @Override
    @Test
    public void getCitizenMobileTest() {
        assertEquals("666666666", citizen.getMobileNumb());
    }
}
