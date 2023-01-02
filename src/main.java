import citizenmanagementplatform.UnifiedPlatform;
import citizenmanagementplatform.exceptions.IncompleteFormException;
import data.Nif;
import exceptions.*;
import publicadministration.Citizen;
import services.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    static Scanner scanner = new Scanner(System.in);
    static UnifiedPlatform platform = new UnifiedPlatform();


    public static void main(String[] args) throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
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
    private static void showMinisteriodeJusticia() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
        System.out.println("Ministerio de Justicia");
        System.out.println("1. Trámites");
        System.out.println("2. Información");
        System.out.println("3. Contacto");

        switch (scanner.nextInt()) {
            case 1:
                platform.selectProcedures(); //2
                showTramites(); //2.1

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

    private static void showTramites() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
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
    private static void showIdentificacionPossibilities() throws IncompleteFormException, IncorrectVerificationException, IOException, NotValidPINException, IncorrectValDateException, WrongSmallCodeFormatException, NifNotRegisteredException, DigitalSignatureException, WrongNifFormatException, AnyMobileRegisteredException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
        System.out.println("1. Cl@ve PIN");
        System.out.println("2. Cl@ve Permanente");
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("Cl@ve PIN");
                platform.selectAuthMethod((byte) 1); //4
                showformulario_identificacion(); //4.1
                break;
            case 2:
                System.out.println("Cl@ve Permanente");
                platform.selectAuthMethod((byte) 2); //4


                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void showformulario_identificacion() throws WrongNifFormatException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, IncompleteFormException, WrongGoalTypeException, IncorrectVerificationException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
        System.out.println("Introduce tu NIF");
        String nif = scanner.next();
        Nif NIF = new Nif(nif);
        System.out.println("Formulario: Introduce Valdate del Nif--");
        System.out.println("Introduce el dia");
        var day = scanner.next();
        System.out.println("Introduce el mes");
        var month = scanner.next();
        System.out.println("Introduce el año");
        var year = scanner.next();
        LocalDate dategood = LocalDate.of(2028, 12, 12);
        platform.initialize_citz(NIF, dategood); //5
        platform.generateandsetPIN();
        platform.enterNIFandPINobt(NIF, dategood); //5
        System.out.println("Introduce el código");
        var pin_code = scanner.next();
        var pin = new data.SmallCode(pin_code);
        platform.enterPIN(pin); //6
        showformularioDGP(NIF,dategood); //6.1
    }

    private static void showformularioDGP(Nif nif, LocalDate date) throws WrongNifFormatException, IncorrectValDateException, NifNotRegisteredException, AnyMobileRegisteredException, IOException, WrongSmallCodeFormatException, NotValidPINException, DigitalSignatureException, WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat, WrongCreditCardNumberException, WrongGoalTypeException, IncompleteFormException, IncorrectVerificationException, WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException {
        System.out.println("Introduce tu nombre");
        var name = scanner.next();
        System.out.println("Introduce tu add");
        var add = scanner.next();
        System.out.println("Introduce tu tel");
        //var tel = scanner.next();
        var tel="666666666";
        var citizenfinal= new Citizen(nif,name,add,tel);
        //var goal= scanner.next();
        var goal="WORKWITHMINORS";
        var goalfinal= new data.Goal(goal,"DESCP","4");
        platform.setform(citizenfinal,goalfinal);
        platform.enterForm(citizenfinal,goalfinal); //7
        platform.realizePayment();
        showformulario_datos_tarjeta(nif); //7.1

    }

    private static void showformulario_datos_tarjeta(Nif nif) throws WrongCreditCardLengthException, WrongCreditCardDataException, WrongCreditCardExceptionFormat, NotValidPaymentDataException, InsufficientBalanceException, IOException, WrongSmallCodeFormatException, WrongCreditCardNumberException, DigitalSignatureException, IncompleteFormException, WrongNifFormatException {
        var card=platform.creditCardForm();
        platform.setCreditCard(card);
        platform.enterCardData(card); //8
        showCertificadoptions(nif); //9.1
    }

    private static void showCertificadoptions(Nif nif) throws WrongCreditCardNumberException, DigitalSignatureException, IOException {
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
