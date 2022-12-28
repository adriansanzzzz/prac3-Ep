package publicadministration.interfaces;

import data.Nif;
import exceptions.WrongCitizenMobileNumbFormat;
import exceptions.WrongCitizenMobileNumblength;
import exceptions.WrongNifFormatException;
import org.junit.jupiter.api.Test;
import publicadministration.Citizen;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CitizienTestInterface {
    @Test
    void getCitizenNameTest();

    @Test
    void getCitizenAddressTest();

    @Test
    void getCitizenMobileTest();

    @Test
    default void NullCitizenNameTest() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(NullPointerException.class, () -> new Citizen(nif, null, "Calle de la paz, 1,Huesca,22001,Spain", "666666666"));
    }

    @Test
    default void NullAdressTest() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(NullPointerException.class, () -> new Citizen(nif, "Javier", null, "666666666"));
    }

    @Test
    default void NullMobileTest() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(NullPointerException.class, () -> new Citizen(nif, "Javier", "Calle de la paz, 1,Huesca,22001,Spain", null));
    }
    @Test
    //si el numero de telefono no tiene 9 digitos
    default void WrongCitizenMobileNumblength() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(WrongCitizenMobileNumblength.class, () -> new Citizen(nif, "Javier", "Calle de la paz, 1,Huesca,22001,Spain", "66666666"));
    }

    @Test
    //si el numero de telefono contiene letras
    default void WrongCitizenMobileNumbFormat() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(WrongCitizenMobileNumbFormat.class, () -> new Citizen(nif, "Javier", "Calle de la paz, 1,Huesca,22001,Spain", "68208577A"));
    }
    @Test
    default void WrongCitizenMobileNumbFormat2() throws WrongNifFormatException {
        var nif = new Nif("49255398R");
        assertThrows(WrongCitizenMobileNumbFormat.class, () -> new Citizen(nif, "Javier", "Calle de la paz, 1,Huesca,22001,Spain", "481041777"));
    }
}
