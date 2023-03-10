package publicadministration.interfaces;

import data.DocPath;
import org.junit.jupiter.api.Test;
import publicadministration.PDFDocument;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface PDFDocumentTestInterface {
    @Test
    void getPathTest();

    @Test
    void getFileTest();

    @Test
    void moveDocTest() throws IOException;

    @Test
    void openDocTest() throws IOException;

    @Test
    default void moveToNonExistentPathTest() {
        assertThrows(IOException.class,
                () -> {
                    DocPath nonExistentPath = new DocPath("/path/to/non/existent/file");
                    PDFDocument pdf = new PDFDocument();
                    pdf.moveDoc(nonExistentPath);
                });
    }

    @Test
    default void openNonExistentPathTest() {
        assertThrows(IOException.class,
                () -> {
                    DocPath nonExistentPath = new DocPath("/path/to/non/existent/file");
                    PDFDocument pdf = new PDFDocument();
                    pdf.openDoc(nonExistentPath);
                });
    }


}