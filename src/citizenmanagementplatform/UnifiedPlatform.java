package citizenmanagementplatform;

import data.Goal;
import data.Nif;
import data.SmallCode;
import exceptions.*;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import publicadministration.PDFDocument;
import services.exceptions.*;
import services.interfaces.CAS;
import services.interfaces.CertificationAuthority;
import services.interfaces.GPD;
import services.interfaces.JusticeMinistry;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.LocalDate;

public class UnifiedPlatform {
    private static CertificationAuthority authMethod;
    Goal go;
    GPD gpd;
    CAS cas;
    JusticeMinistry jm;
    Citizen citz;

    // Input events
    public  void selectJusMin() {
        System.out.println("Aviso: Se ha seleccionado Ministerio de Justicia.");
    }
    public  void selectProcedures() {
        System.out.println("Aviso: Se ha seleccionado Tramites..");
    }
    public  void selectCriminalReportCertf() {
        System.out.println("Aviso: Se ha seleccionado el trámite: Obtener el certificado de antecedentes penales..");
    }
    public  void selectAuthMethod(byte opc) {
        System.out.println("Aviso: Se ha seleccionado el método de identificación: CL@VE PIN.");
    }
    public  void enterNIFandPINobt(Nif nif, LocalDate valDate) throws ConnectException, IncorrectValDateException, WrongNifFormatException, AnyMobileRegisteredException, NifNotRegisteredException, WrongSmallCodeFormatException, NotValidPINException {
        var pin= generatePIN();
        SmallCode pinCode = new SmallCode(pin);
        citz.setNif(nif);
        citz.setValidationDate(valDate);

        if (authMethod.sendPIN(nif, valDate)) {
            System.out.println("Aviso: Se envia el PIN al usuario con DNI -> " + citz.getNif());
        } else {
            throw new ConnectException("ERROR de Conexión AL ENVIAR EL PIN.");
        }
        authMethod.checkPIN(nif,pinCode);
        authMethod.sendPIN(nif,valDate);
        System.out.println("Aviso: Se ha ENVIADO el PIN al usuario con DNI -> " + citz.getNif());

    }
    private static String generatePIN() {
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        return String.valueOf(PIN);
    }
    public void enterPIN (SmallCode pin) throws NotValidPINException, IOException, DigitalSignatureException, WrongCreditCardNumberException {
        if (authMethod.checkPIN(citz.getNif(), pin)) {
            System.out.println("Aviso: El PIN introducido es correcto!!");
            if (jm != null) {
                PDFDocument pdf = jm.getCriminalRecordCertf(citz, go);
                citz.setPDFDocument(pdf);
                pdf.openDoc(pdf.pdfgetPath());
                System.out.println("Se procede a mostrar el certificado de antecedentes penales.");
            } else {
                System.out.println("No se ha seleccionado ningún ministerio de justicia.");
            }
        } else {
            throw new NotValidPINException("El PIN introducido no es correcto y no se corresponde con el generado por el sistema previamente. Se indica al usuario que podria no estar vigente.");
        }
    }
    public  void enterForm (Citizen citz, Goal goal) throws IncorrectVerificationException, ConnectException, IncompleteFormException {
        if (citz == null || goal == null) {
            throw new IncompleteFormException("El usuario no existe en el sistema.");
        }
        if (gpd.verifyData(citz, goal)) {
            System.out.println("Aviso: Se ha verificado correctamente el usuario.");
        } else {
            throw new ConnectException("ERROR: No se ha podido conectar el usuario.");
        }
    }

    public void realizePayment() {
        System.out.println("Aviso: Se ha registrado el pago del certificado de antecedentes penales. ");

    }
    public void enterCardData(CreditCard cardD) throws  ConnectException, NotValidPaymentDataException, InsufficientBalanceException {
        var datenow = LocalDate.now();
        var importtopay= new BigDecimal("3.86");
        cas.askForApproval("1000",cardD,datenow,importtopay);

    }


    public void obtainCertificate () throws DigitalSignatureException, ConnectException, WrongCreditCardNumberException {
        System.out.println("Aviso: Se va a generar el certificado de antecedentes penales estandar.");
        jm.getCriminalRecordCertf(citz, go);


    }
    public void printDocument () {
        System.out.println("Aviso: Se va a imprimir el certificado de antecedentes penales.");
    }





}