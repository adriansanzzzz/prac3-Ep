import citizenmanagementplatform.UnifiedPlatform;
import citizenmanagementplatform.exceptions.*;
import data.Nif;
import data.Password;
import data.exceptions.*;
import publicadministration.Citizen;
import services.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static UnifiedPlatform platform = new UnifiedPlatform();
    static Integer option_auth=1;


    public static void main(String[] args) throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, NotValidCredException, WrongPasswordFormatException, BadPathException, ProceduralException {
        System.out.println("Selecciona Ministerio al que quieres acceder");
        System.out.println("1. Justicia");
        System.out.println("2. Interior");


        switch (scanner.nextInt()) {
            case 1:
                platform.selectJusMin(); //1
                showMinisteriodeJusticia(); //1.1
                System.out.println("FINALIZADO");
                break;


            case 2:
                System.out.println("NO IMPLEMENTADO");
                break;
        }


    }
    private static void showMinisteriodeJusticia() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, NotValidCredException, WrongPasswordFormatException, BadPathException, ProceduralException {
        System.out.println("Ministerio de Justicia");
        System.out.println("1. Trámites");
        System.out.println("2. Información");
        System.out.println("3. Contacto");

        switch (scanner.nextInt()) {
            case 1:
                platform.selectProcedures();
                showTramites();

                break;
            case 2:
                System.out.println("Información");
                break;
            case 3:
                System.out.println("Contacto");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void showTramites() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, NotValidCredException, WrongPasswordFormatException, BadPathException, ProceduralException {
        System.out.println("Trámites");
        System.out.println("1. Obtener el certificado de antecedentes penales");
        switch (scanner.nextInt()) {
            case 1:
                platform.selectCriminalReportCertf(); //3
                System.out.println("Opciones de identificación");
                showIdentificacionPossibilities(); //3.1
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private static void showIdentificacionPossibilities() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, NotValidCredException, WrongPasswordFormatException, BadPathException, ProceduralException {
        System.out.println("1. Cl@ve PIN");
        System.out.println("2. Cl@ve Permanente");
        switch (scanner.nextInt()) {
            case 1:
                option_auth=1;
                System.out.println("Cl@ve PIN");
                platform.selectAuthMethod((byte) 1); //4
                showformulario_identificacion(); //4.1
                break;
            case 2:
                option_auth=2;
                System.out.println("Cl@ve Permanente");
                platform.selectAuthMethod((byte) 2); //4
                showformulario_identificacion_clave_per();


                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
    private static void showformulario_identificacion_clave_per() throws WrongNifFormatException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, IncompleteFormException, WrongGoalTypeException, IncorrectVerificationException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, WrongPasswordFormatException, NotValidCredException, BadPathException, ProceduralException {
        System.out.println("---FORMULARIO DE IDENTIFICACIÓN---");
        System.out.println("Introduce tu NIF:");
        String nif = scanner.next();
        Nif NIF = new Nif(nif);
        System.out.println("Introduce password");
        Password password = new Password(scanner.next());
        System.out.println("Formulario: Introduce Valdate del Nif:");
        System.out.println("Introduce el dia");
        var day = scanner.next();
        System.out.println("Introduce el mes");
        var month = scanner.next();
        System.out.println("Introduce el año");
        var year = scanner.next();
        System.out.println("Formulario: Introduce el número telefono móvil: ");
        var mobile = scanner.next();
        LocalDate dategood = LocalDate.of(2028, 12, 12);
        platform.initialize_citz(NIF, dategood,mobile,password); //5
        platform.enterCred(NIF,password);
        platform.generateandsetPIN();
        platform.enterNIFandPINobt(NIF, dategood); //5
        System.out.println("Introduce el código:");
        var pin_code = scanner.next();
        var pin = new data.SmallCode(pin_code);
        platform.enterPIN(pin); //6
        showformularioDGP(NIF,mobile); //6.1
    }



        private static void showformulario_identificacion() throws WrongNifFormatException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, IncompleteFormException, WrongGoalTypeException, IncorrectVerificationException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, BadPathException, ProceduralException {
        System.out.println("Formulario: Introduce tu NIF");
        String nif = scanner.next();
        Nif NIF = new Nif(nif);
        System.out.println("Formulario: Introduce Valdate del Nif");
        System.out.println("Introduce el dia");
        var day = scanner.next();
        System.out.println("Introduce el mes");
        var month = scanner.next();
        System.out.println("Introduce el año");
        var year = scanner.next();
        LocalDate dategood = LocalDate.of(2028, 12, 12);
        System.out.println("Introduce el numero telfono movil ");
        var mobile = scanner.next();
        platform.initialize_citz(NIF, dategood,mobile); //5
        platform.generateandsetPIN();
        platform.enterNIFandPINobt(NIF, dategood); //5
        System.out.println("Introduce el código");
        var pin_code = scanner.next();
        var pin = new data.SmallCode(pin_code);
        platform.enterPIN(pin); //6
        showformularioDGP(NIF,mobile); //6.1
    }

    private static void showformularioDGP(Nif nif, String movil) throws WrongNifFormatException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, IncompleteFormException, IncorrectVerificationException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, BadPathException, ProceduralException {
        System.out.println("Introduce tu nombre");
        var name = scanner.next();
        System.out.println("Introduce tu add");
        var add = scanner.next();
        var citizenfinal= new Citizen(nif,name,add,movil);
        //var goal= scanner.next();
        var goal="WORKWITHMINORS";
        var goalfinal= new data.Goal(goal,"DESCP","4");
        platform.set_formdata(citizenfinal,goalfinal);
        platform.enterForm(citizenfinal,goalfinal); //7
        platform.realizePayment();
        showformulario_datos_tarjeta(nif); //7.1

    }

    private static void showformulario_datos_tarjeta(Nif nif) throws WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, IOException, WrongSmallCodeFormatException, WrongCreditCardNumberException, DigitalSignatureException, IncompleteFormException, WrongNifFormatException, BadPathException, ProceduralException {
        var card=platform.creditcard_form();
        platform.set_creditcard_data(card);
        platform.enterCardData(card); //8
        showCertificadoptions(nif); //9.1
    }

    private static void showCertificadoptions(Nif nif) throws WrongCreditCardNumberException, DigitalSignatureException, IOException, BadPathException {
        System.out.println("1. Obtener certificado");
        switch (scanner.nextInt()) {
            case 1:
                platform.obtainCertificate(); //29
                platform.printDocument();
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

}
