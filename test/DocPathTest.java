import data.DocPath;
import interfaces.DocPathTestInterface;
import interfaces.DocPathTestInterface;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocPathTest implements DocPathTestInterface{
    DocPath docPath;

    @BeforeEach
    public void setUp() {
        String correctDocPath = "/Users/adriansanz/Desktop/Universidad/EP/test.out";
        docPath = new DocPath(correctDocPath);
    }

    @Test
    @Override
    public void getDocPathTest() {
        String correctDocPath = "/Users/adriansanz/Desktop/Universidad/EP/test.out";
        assertEquals(correctDocPath, docPath.getDocPath());
    }


}