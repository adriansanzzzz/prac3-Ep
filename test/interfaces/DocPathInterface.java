package interfaces;

import data.DocPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface DocPathInterface {
    @Test
    void getDocPathTest();

    @Test
    default void getNullPointerDocPathTest() {
        assertThrows(NullPointerException.class,
                () -> new DocPath(null)); //si pasamos path NULL,  nos da un NullPointerException
    }
}

