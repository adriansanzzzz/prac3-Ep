package publicadministration;

import data.Goal;
import data.Nif;
import data.Password;
import data.SmallCode;
import data.exceptions.WrongCitizenMobileNumbFormat;
import data.exceptions.WrongCitizenMobileNumblength;

import java.time.LocalDate;

public class Citizen {

    private Nif nif;
    private String name;
    private String address;
    private String mobileNumb;
    public LocalDate valdate;
    private SmallCode pin;
    private PDFDocument pdf;
    Password password;
    Goal goal;
    private boolean reinforcedPINActivated = false;


    public Citizen(Nif nif, String name, String add, String mobile) throws WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        checkCitizen(name, add, mobile);
        this.nif = nif;
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
        this.pdf = null;
        this.pin = pin;
        this.password = password;
        this.goal = goal;

    }

    public Citizen() {
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.mobileNumb = mobileNumb;
        this.pdf = pdf;
        this.pin = pin;
        this.password = password;
        this.goal = goal;

    }
    // the getters

    void checkCitizen(String name, String add, String mobile) throws WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        if (name == null) {
            throw new NullPointerException("The citizen name is not valid");
        }
        if (add == null) {
            throw new NullPointerException("The citizen adress is not valid");
        }
        if (mobile == null) {
            throw new NullPointerException("The citizen mobile is null");
        }
        //si el numero de telefono no tiene 9 digitos
        if (mobile.length() != 9) {
            throw new WrongCitizenMobileNumblength("The mobile number is not valid");
        }
        //si el numero de telefono no empieza por 6 o 9
        if (mobile.charAt(0) != '6' && mobile.charAt(0) != '9') {
            throw new WrongCitizenMobileNumbFormat("The mobile number is not valid");
        }
        //si el numero de telefono contiene letras
        for (int i = 0; i < mobile.length(); i++) {
            if (!Character.isDigit(mobile.charAt(i))) {
                throw new WrongCitizenMobileNumbFormat("The mobile number is not valid");
            }
        }

    }

    public Nif getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    //BOOLEAN THAT CHECKS IF THE CITIZEN HAVE A MOBILE NUMBER
    public boolean hasMobile() {
        return mobileNumb != null;
    }

    public void setNif(Nif nif) {
        this.nif = nif;
    }

    public void setPin(SmallCode pin) {
        this.pin = pin;
    }


    public void setValidationDate(LocalDate valDates) {
        this.valdate = valDates;

    }


    public LocalDate getValidationDate() {
        return valdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return nif.equals(citizen.nif) &&
                name.equals(citizen.name) &&
                address.equals(citizen.address) &&
                mobileNumb.equals(citizen.mobileNumb);
    }


    public void setName(String nextLine) {
        this.name = nextLine;
    }

    public void setAddress(String nextLine) {
        this.address = nextLine;
    }

    public void setMobileNumb(String nextLine) {
        this.mobileNumb = nextLine;
    }


    public int hashCode() {
        return nif.hashCode() + name.hashCode() + address.hashCode() + mobileNumb.hashCode();
    }

    @Override
    public String toString() {
        return "Citizen: " + this.name + " " + this.address + " " + this.mobileNumb;
    }


    public String getMobileNumb() {
        return mobileNumb;
    }

    public SmallCode getPin(Nif nif) {
        return pin;
    }

    public void setPDFDocument(PDFDocument pdf) {
        this.pdf = pdf;
    }

    public PDFDocument getPDFDocument() {
        return pdf;
    }

    public Object getPassword() {
        return password;

    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public boolean permanentpin() {
        return reinforcedPINActivated;

    }


    public void setGoal(Goal goal) {
        this.goal = goal;

    }
}