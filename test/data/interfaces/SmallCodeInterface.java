package data.interfaces;

import data.SmallCode;
import data.exceptions.WrongSmallCodeFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface SmallCodeInterface {
    @Test
    void getSmallCodeTest();

    @Test
    default void getNullPointerSmallCodeTest() {
        assertThrows(NullPointerException.class,
                () -> new SmallCode(null));
    }
    @Test
    default void tooFewNumbersTest() {
        assertThrows(WrongSmallCodeFormatException.class,
                () -> {
                    String fewNumbers = "12";
                    new SmallCode(fewNumbers);
                });
    }
    @Test
    default void tooManyNumbersTest() {
        assertThrows(WrongSmallCodeFormatException.class,
                () -> {
                    String manyNumbers = "1234";
                    new SmallCode(manyNumbers);
                });
    }
    @Test
    default void LettersTest() {
        assertThrows(WrongSmallCodeFormatException.class,
                () -> {
                    String letters = "ACB";
                    new SmallCode(letters);
                });
    }

    @Test
    default void lowerCaseLettersTest() {
        assertThrows(WrongSmallCodeFormatException.class,
                () -> {
                    String lowerCaseLetters = "a";
                    new SmallCode(lowerCaseLetters);
                });
    }

}

