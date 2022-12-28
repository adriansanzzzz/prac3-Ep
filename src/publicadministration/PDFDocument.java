package publicadministration;

import data.DocPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class PDFDocument   { // Represents a PDF document
    private Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument() {
        this.creatDate = new Date();
        this.path = new DocPath("C:\\Users\\Usuario\\Desktop\\EP.pdf");
        this.file = new File(path.getDocPath());

    }

    public DocPath getPath() {
        return path;
    }

    public File getFile() {
        return file;
    }


    public void moveDoc(DocPath destPath) throws IOException {
        if (!new File(destPath.getDocPath()).exists()) throw new IOException("El path especificado no existe.");
        Path in = Paths.get(path.getDocPath());
        Path end = Paths.get(destPath.getDocPath());
        Files.move(in, end, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        path = destPath;
    }

    public void openDoc(DocPath path) throws IOException {
        if (!new File(path.getDocPath()).exists()) throw new IOException("El documento en el path no existe.");
        File file = new File(path.getDocPath());
        Desktop.getDesktop().open(file);
    }

    public String toString() {
        return "PDFDocument: " + this.creatDate + " " + this.path;
    } // Returns a string with the document creation date and path

}


