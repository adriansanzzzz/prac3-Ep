package citizenmanagementplatform;

import data.Goal;
import data.Nif;
import data.Password;
import data.SmallCode;
import exceptions.*;
import publicadministration.Citizen;
import publicadministration.CreditCard;
import publicadministration.PDFDocument;
import services.CASClass;
import services.CertificationAuthorityClass;
import services.GPDClass;
import services.JusticeMinistryClass;
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
    Goal go;
    GPD gpd;
    CAS cas;
    JusticeMinistry jm;
    Citizen citz;
    CertificationAuthority ca;


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
        if(opc==1) {
            System.out.println("Aviso: Se ha seleccionado el método de identificación: Cl@ve PIN.");
        }
        else if(opc==2) {
            System.out.println("Aviso: Se ha seleccionado el método de identificación: Cl@ve Permanente.");
        }
    }
    public  void enterNIFandPINobt(Nif nif, LocalDate valDate) throws ConnectException, IncorrectValDateException, WrongNifFormatException, AnyMobileRegisteredException, NifNotRegisteredException, WrongSmallCodeFormatException, NotValidPINException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        var pin= generatePIN();
        System.out.println("Aviso: Se ha introducido el NIF: "+nif.toString()+" y el PIN: "+pin.toString());
        SmallCode pinCode = new SmallCode(pin);
        this.citz = new Citizen(nif,"DEFAULT","ADDR","681081776");
        citz.setNif(nif);
        citz.setValidationDate(valDate);
        ca = new CertificationAuthorityClass(citz);

        if (ca.sendPIN(nif, valDate)) {
            System.out.println("Aviso: Se envia el PIN al usuario con DNI -> " + citz.getNif());
        } else {
            throw new ConnectException("ERROR de Conexión AL ENVIAR EL PIN.");
        }
        ca.checkPIN(nif,pinCode);
        ca.sendPIN(nif,valDate);
        System.out.println("Aviso: ENVIADO!! el PIN al usuario con DNI -> " + citz.getNif());

    }
    private static String generatePIN() {
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        return String.valueOf(PIN);
    }
    public void enterPIN (SmallCode pin) throws NotValidPINException, IOException, DigitalSignatureException, WrongCreditCardNumberException {

        if (ca.checkPIN(this.citz.getNif(), pin)) {
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
        gpd = new GPDClass(citz, goal);

        if (gpd.verifyData(citz, goal)) {
            System.out.println("Aviso: Se ha verificado correctamente el usuario.");
            System.out.println("Importe a pagar: 3,86");

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
        var ntrans = random_number();
        cas= new CASClass(ntrans, cardD,datenow,importtopay);
        if(cas.askForApproval(ntrans,cardD,datenow,importtopay)){
            System.out.println("Aviso: Se ha realizado el pago del certificado de antecedentes penales CORRECTAMENTE.");
        }
        else{
            throw new ConnectException("ERROR: No se ha podido conectar el usuario.");
        }

    }
    private String random_number() {
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        return String.valueOf(PIN);
    }


    public void obtainCertificate () throws DigitalSignatureException, ConnectException, WrongCreditCardNumberException {
        jm = new JusticeMinistryClass();

        System.out.println("Aviso: Se va a generar el certificado de antecedentes penales estandar.");
        System.out.println("Aviso: Se ha generado el certificado de antecedentes penales estandar.");


    }
    public void enterCred (Nif nif, Password passw) {
    }
    public void printDocument () {
        System.out.println("Aviso: Se va a imprimir el certificado de antecedentes penales.");
    }





}