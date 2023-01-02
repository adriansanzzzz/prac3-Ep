package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.IncompleteFormException;
import data.Goal;
import data.Nif;
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
import java.util.Scanner;

public class UnifiedPlatform {
    static Scanner scanner = new Scanner(System.in);
    Goal go;
    GPD gpd;
    CAS cas;
    JusticeMinistry jm;
    Citizen citz;
    CertificationAuthority ca;
    CreditCard cc;
    PDFDocument pdf;

    //boolean for each step
    boolean selecjustmin = false;
    boolean selecprocedures = false;
    boolean seleccriminalreportcerf = false;
    boolean selecauthmethod = false;
    static BigDecimal import_of_pay= new BigDecimal("3.86");
    static LocalDate datenow = LocalDate.now();
    static String ntrans = random_number();



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
    public void initialize_citz(Nif nif, LocalDate valDate){ //FOR TESTING PURPOSES
        citz = new Citizen();
        ca = new CertificationAuthorityClass(citz);
        citz.setNif(nif);
        citz.setValidationDate(valDate);

    }
    public  void enterNIFandPINobt(Nif nif, LocalDate valDate) throws ConnectException, IncorrectValDateException, WrongNifFormatException, AnyMobileRegisteredException, NifNotRegisteredException, WrongSmallCodeFormatException, NotValidPINException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {

        if(ca.sendPIN(nif, valDate)) {
            System.out.println("Aviso: ENVIADO!! el PIN al usuario con DNI -> " + citz.getNif());
        }
    }
    public void generateandsetPIN() throws WrongSmallCodeFormatException {
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        var pin= new SmallCode(String.valueOf(PIN));
        System.out.println("Aviso: PIN generado: " + pin);
        citz.setPin(pin);

    }
    public void setpin(SmallCode pin) { //FOR TESTING PURPOSES
        citz.setPin(pin);
    }
    public void enterPIN (SmallCode pin) throws NotValidPINException, IOException, DigitalSignatureException, WrongCreditCardNumberException {

        if (ca.checkPIN(this.citz.getNif(), pin)) {
            System.out.println("Aviso: El PIN introducido es correcto!!");
            System.out.println("Se procede a mostrar el certificado de antecedentes penales.");
        } else {
            throw new NotValidPINException("El PIN introducido no es correcto y no se corresponde con el generado por el sistema previamente. Se indica al usuario que podria no estar vigente.");
        }
    }
    public void setform(Citizen citizen, Goal goal){
        go=new Goal();
        citz.setName(citizen.getName());
        citz.setAddress(citizen.getAddress());
        citz.setMobileNumb(citizen.getMobileNumb());
        go.setType(goal.getType());
        go.setPriority(goal.getPriority());
        go.setDescription(goal.getDescription());

    }
    public  void enterForm (Citizen citizen, Goal goal) throws IncorrectVerificationException, ConnectException, IncompleteFormException {

        if (citz == null || goal == null) {
            throw new IncompleteFormException("El usuario no existe en el sistema.");
        }
        gpd = new GPDClass(citizen, goal);
        if(!(citizen.equals(citz) && goal.equals(go))) {
            throw new IncorrectVerificationException("El usuario no existe en el sistema.");
        }
        citz=citizen;
        go=goal;


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
    public void setCreditCard(CreditCard creditCard) {
        cc=creditCard;
        cc.setCardNumb(creditCard.getCardNumb());
        cc.setExpirDate(creditCard.getExpirDate());
        cc.setSvc(creditCard.getSvc());
        cc.setNif(creditCard.getNif());
        cas= new CASClass(ntrans, creditCard,datenow,import_of_pay);

    }

    public CreditCard creditCardForm() throws WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, WrongSmallCodeFormatException, WrongNifFormatException, IncompleteFormException {
        System.out.println("Formulario: Introduce el Nif del titular de la tarjeta: ");
        var nif = new Nif(scanner.nextLine());
        System.out.println("Formulario: Introduce el número de la tarjeta");
        var num="3333333333334321";
        System.out.println("Formulario: Introduce el titular");
        var titular = scanner.next();
        System.out.println("Formulario: Introduce la fecha de caducidad de la tarjeta:");
        System.out.println("Introduce el dia:");
        var day = scanner.nextInt();
        System.out.println("Introduce el mes:");
        var month = scanner.nextInt();
        System.out.println("Introduce el año:");
        var year = scanner.nextInt();
        var expdate = LocalDate.of(year, month, day);
        System.out.println("Formulario: Introduce el código de seguridad");
        var code = scanner.next();
        var finalcode = new data.SmallCode(code);
        if(nif==null || num==null || titular==null || expdate==null || finalcode==null){
            throw new IncompleteFormException("ERROR: Los datos introducidos no son correctos.");
        }
        return new CreditCard(nif, num, expdate, finalcode);

    }

    public void enterCardData(CreditCard cardD) throws ConnectException, NotValidPaymentDataException, InsufficientBalanceException, IncompleteFormException, WrongNifFormatException, WrongSmallCodeFormatException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat {
        System.out.println(import_of_pay);
        System.out.println(cardD.getBalance());
        if(import_of_pay.compareTo(cardD.getBalance())==1) { //if import_of_pay is greater than balance
            throw new InsufficientBalanceException("El usuario no tiene saldo suficiente para realizar el pago.");
        }


        if(cas.askForApproval(ntrans,cardD,datenow,import_of_pay)){
            System.out.println("Aviso: Se ha realizado el pago del certificado de antecedentes penales CORRECTAMENTE.");
        }
        else{
            throw new ConnectException("ERROR: No se ha podido conectar el usuario.");
        }

    }
    private static String random_number() {
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        return String.valueOf(PIN);
    }


    public void obtainCertificate () throws DigitalSignatureException, IOException, WrongCreditCardNumberException {
        System.out.println("Aviso: Se va a generar el certificado de antecedentes penales estandar.");

        jm = new JusticeMinistryClass();
        var pdf=jm.getCriminalRecordCertf(citz, go);
        citz.setPDFDocument(pdf);
        pdf.openDoc(citz.getPDFDocument().pdfgetPath());

        System.out.println("Aviso: Se ha generado el certificado de antecedentes penales estandar.");
        System.out.println("1: Desea Mover.");

        switch (scanner.nextInt()){
            case 1:
                pdf.moveDoc(citz.getPDFDocument().pdfgetPath());
                System.out.println("Aviso: Se ha movido el certificado de antecedentes penales estandar. al path: "+citz.getPDFDocument().pdfgetPath());

                break;
            default:
                break;
        }


    }

    public void printDocument () {
        System.out.println("Aviso: Se va a imprimir el certificado de antecedentes penales.");
    }

    public void setCitz(Citizen citz) {
        this.citz = citz;
    }




}