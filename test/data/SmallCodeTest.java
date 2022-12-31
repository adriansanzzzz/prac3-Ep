package data;

import data.interfaces.SmallCodeInterface;
import exceptions.WrongSmallCodeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallCodeTest implements SmallCodeInterface {
    SmallCode smC;

    @BeforeEach
    public void setUp() throws WrongSmallCodeFormatException {
        smC = new SmallCode("124");
    }

    @Test
    @Override
    public void getSmallCodeTest() {
        assertEquals("124", smC.getSmallCode());
    }

    }
