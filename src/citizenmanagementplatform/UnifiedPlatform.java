package citizenmanagementplatform;

import citizenmanagementplatform.exceptions.*;
import data.*;
import data.exceptions.*;
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

    //boolean for each function
    boolean selectJusMin = false;
    boolean selectProcedures = false;
    boolean selectCriminalReportCertf = false;
    boolean selectAuthMethod = false;
    boolean enternifnobt = false;
    boolean enterpin = false;
    boolean enterform = false;
    boolean realizepayment = false;
    boolean entercreditcarddata = false;
    boolean obtaincertf = false;
    boolean printcertf = false;


    static BigDecimal import_of_pay= new BigDecimal("3.86");
    static LocalDate datenow = LocalDate.now();
    static String ntrans = random_number();


    public  void selectJusMin() {
        selectJusMin = true;
        System.out.println("Aviso: Se ha seleccionado Ministerio de Justicia.");
    }
    public  void selectProcedures() throws ProceduralException {
        selectProcedures = true;
        if(!selectJusMin) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia");
        System.out.println("Aviso: Se ha seleccionado Tramites..");
    }
    public  void selectCriminalReportCertf() throws ProceduralException {
        selectCriminalReportCertf = true;
        if(!selectJusMin || !selectProcedures) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites");
        System.out.println("Aviso: Se ha seleccionado el trámite: Obtener el certificado de antecedentes penales..");
    }
    public  void selectAuthMethod(byte opc) throws ProceduralException {
        selectAuthMethod = true;
        if(!selectJusMin || !selectProcedures || !selectCriminalReportCertf) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites o Obtener el certificado de antecedentes penales");
        if(opc == 1){
            System.out.println("Aviso: Se ha seleccionado el metodo de identificacion: Cl@ve PIN");
        }else if(opc == 2){
            System.out.println("Aviso: Se ha seleccionado el metodo de identificacion: Cl@ve Permanente");
        }
    }
    public void initialize_citz(Nif nif, LocalDate valDate, String mobile)  {
        //AUXILIAR METHOD
        citz = new Citizen();
        ca = new CertificationAuthorityClass(citz);
        citz.setNif(nif);
        citz.setValidationDate(valDate);

    }
    public void initialize_citz(Nif nif, LocalDate valDate, String mobile, Password pass)  {
        //AUXILIAR METHOD
        citz = new Citizen();
        ca = new CertificationAuthorityClass(citz);
        citz.setNif(nif);
        citz.setValidationDate(valDate);
        citz.setPassword(pass);
        citz.setMobileNumb(mobile);
    }
    public  void enterNIFandPINobt(Nif nif, LocalDate valDate) throws ConnectException, IncorrectValDateException, WrongNifFormatException, AnyMobileRegisteredException, NifNotRegisteredException, WrongSmallCodeFormatException, NotValidPINException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, ProceduralException {
        enternifnobt = true;
        if(!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod ) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites o Obtener el certificado de antecedentes penales o metodo de identificacion");
        if(ca.sendPIN(nif, valDate)) {
            System.out.println("Aviso: ENVIADO!! el PIN al usuario con DNI -> " + citz.getNif());
        }
        else {
            throw new ConnectException("No se ha podido enviar el PIN al usuario con DNI -> " + citz.getNif());
        }
    }
    public void generateandsetPIN() throws WrongSmallCodeFormatException, ProceduralException {
        //AUXILIAR METHOD
        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        var pin= new SmallCode(String.valueOf(PIN));
        System.out.println("Mensaje: PIN generado: " + PIN);
        citz.setPin(pin);

    }
    public void enterPIN (SmallCode pin) throws NotValidPINException, IOException, DigitalSignatureException, WrongCreditCardNumberException, ProceduralException {
        enterpin = true;
        if(!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod || !enternifnobt) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites o Obtener el certificado de antecedentes penales o metodo de identificacion o DNI y fecha de nacimiento o PIN");
        if (ca.checkPIN(this.citz.getNif(), pin)) {
            System.out.println("Aviso: El PIN introducido es correcto!!");
            System.out.println("Se procede a mostrar el certificado de antecedentes penales.");
        } else {
            throw new ConnectException("ERROR: No se ha podido conectar el usuario.");
        }
    }
    public void set_formdata(Citizen citizen, Goal goal) throws NotValidPINException, ProceduralException {
        //AUXILIAR METHOD
        go=new Goal();
        citz.setName(citizen.getName());
        citz.setGoal(goal);
        citz.setAddress(citizen.getAddress());
        go.setType(goal.getType());
        go.setPriority(goal.getPriority());
        go.setDescription(goal.getDescription());

    }
    public void enterCred(Nif nif, Password passwd) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {

        int res = ca.ckeckCredent(nif, passwd);
        switch (res) {
            case 0 -> throw new NifNotRegisteredException("El ciudadano no esta registrado en el sistema");
            case 1 -> System.out.println("Los datos introducidos no son correctos");

            case 2 -> System.out.println("Datos correctos");

        }
    }
    public  void enterForm (Citizen citizen, Goal goal) throws IncorrectVerificationException, ConnectException, IncompleteFormException, ProceduralException {
        enterform = true;
        if(!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod  || !enternifnobt  || !enterpin) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites o Obtener el certificado de antecedentes penales o metodo de identificacion o DNI y fecha de nacimiento o PIN");
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
    public void realizePayment() throws ProceduralException {
        realizepayment = true;
        if (!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod  || !enternifnobt  || !enterpin  || !enterform) throw new ProceduralException("No se ha seleccionado Ministerio de Justicia o Tramites o Obtener el certificado de antecedentes penales o metodo de identificacion o DNI y fecha de nacimiento o PIN o datos del formulario o formulario");
        System.out.println("Aviso: Se ha registrado el pago del certificado de antecedentes penales. ");

    }
    public void set_creditcard_data(CreditCard creditCard) throws ProceduralException {

        //AUXILIAR METHOD
        cc=creditCard;
        cc.setCardNumb(creditCard.getCardNumb());
        cc.setExpirDate(creditCard.getExpirDate());
        cc.setSvc(creditCard.getSvc());
        cc.setNif(creditCard.getNif());
        cas= new CASClass(ntrans, creditCard,datenow,import_of_pay);

    }

    public CreditCard creditcard_form() throws WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, WrongSmallCodeFormatException, WrongNifFormatException, IncompleteFormException {
        //AUXILIAR METHOD
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
        entercreditcarddata = true;
        if (!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod  || !enternifnobt  || !enterpin || !enterform || !realizepayment ) throw new IncompleteFormException("ERROR");
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
        //AUXILIAR METHOD

        //return a random 3 digit number
        int PIN = (int) (Math.random() * 900) + 100;
        //return string value of integer
        return String.valueOf(PIN);
    }




    public void openDocument(DocPath path) throws BadPathException {
        try {
            pdf.openDoc(path);
        } catch (IOException e) {
            throw new BadPathException("El documento no se ha podido abrir.");
        }
    }



    public void obtainCertificate () throws DigitalSignatureException, IOException, WrongCreditCardNumberException, BadPathException {
        obtaincertf = true;
        if (!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod  || !enternifnobt  || !enterpin || !enterform || !realizepayment || !entercreditcarddata) throw new DigitalSignatureException("ERROR");
        System.out.println("Aviso: Se va a generar el certificado de antecedentes penales estandar.");

        jm = new JusticeMinistryClass();
        pdf=jm.getCriminalRecordCertf(citz, go);
        citz.setPDFDocument(pdf);
        openDocument(citz.getPDFDocument().pdfgetPath());

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

    public void printDocument () throws DigitalSignatureException {
        printcertf = true;
        if (!selectJusMin || !selectProcedures || !selectCriminalReportCertf || !selectAuthMethod  || !enternifnobt  || !enterpin || !enterform || !realizepayment || !entercreditcarddata || !obtaincertf) throw new DigitalSignatureException("ERROR");
        System.out.println("Aviso: Se va a imprimir el certificado de antecedentes penales.");
    }

    //-----------------------FOR TESTING PURPOSES----------------------

    public void set_pin(SmallCode pin) {
        citz.setPin(pin);
    }
    public void dissable_procedures() {
        //FOR TESTING PURPOSES
        selectJusMin = true;
        selectProcedures = true;
        selectCriminalReportCertf = true;
        selectAuthMethod = true;
        enternifnobt = true;
        enterpin = true;
        enterform = true;
        realizepayment = true;
        entercreditcarddata = true;
        obtaincertf = true;
        printcertf = true;
    }
    public void reset_procedures(){
        selectJusMin = false;
        selectProcedures = false;
        selectCriminalReportCertf = false;
        selectAuthMethod = false;
        enternifnobt = false;
        enterpin = false;
        enterform = false;
        realizepayment = false;
        entercreditcarddata = false;
        obtaincertf = false;
        printcertf = false;
    }




}