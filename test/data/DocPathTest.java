package data;

import data.interfaces.DocPathTestInterface;
import exceptions.WrongSmallCodeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocPathTest implements DocPathTestInterface{
    DocPath dPath;

    @BeforeEach
    public void setUp() throws WrongSmallCodeFormatException {
        dPath = new DocPath("C:\\Users\\Usuario\\Desktop\\prueba.txt");
    }

    @Test
    @Override
    public void getDocPathTest () {
        assertEquals("C:\\Users\\Usuario\\Desktop\\prueba.txt", dPath.getDocPath());
    }


}