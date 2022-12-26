import data.SmallCode;
import exceptions.WrongSmallCodeFormatException;
import interfaces.SmallCodeInterface;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallCodeTest implements SmallCodeInterface {
    SmallCode smallCode;

    @BeforeEach
    public void setUp() throws WrongSmallCodeFormatException {
        String correctSmallCode = "222";
        smallCode = new SmallCode(correctSmallCode);
    }

    @Test
    @Override
    public void getSmallCodeTest() {
        String correctSmallCode = "222";
        assertEquals(correctSmallCode, smallCode.getSmallCode());
    }


}