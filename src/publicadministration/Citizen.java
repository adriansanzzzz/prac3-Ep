package publicadministration;

import data.Nif;
import exceptions.WrongCitizenMobileNumbFormat;
import exceptions.WrongCitizenMobileNumblength;

public class Citizen {

    private Nif nif;
    private String name;
    private String address;
    private String mobileNumb;

    public Citizen(Nif nif,String name, String add, String mobile) throws WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        checkCitizen(name, add, mobile);
        this.nif = nif;
        this.name = name;
        this.address = add;
        this.mobileNumb = mobile;
    }
    // the getters

    void checkCitizen(String name, String add, String mobile) throws WrongCitizenMobileNumblength, WrongCitizenMobileNumbFormat {
        if( name == null) {
            throw new NullPointerException("The citizen name is not valid");
        }
        if( add == null) {
            throw new NullPointerException("The citizen adress is not valid");
        }
        if( mobile == null) {
            throw new NullPointerException("The citizen mobile is null");
        }
        //si el numero de telefono no tiene 9 digitos
        if(mobile.length() != 9) {
            throw new WrongCitizenMobileNumblength("The mobile number is not valid");
        }
        //si el numero de telefono no empieza por 6 o 9
        if(mobile.charAt(0) != '6' && mobile.charAt(0) != '9') {
            throw new WrongCitizenMobileNumbFormat("The mobile number is not valid");
        }
        //si el numero de telefono contiene letras
        for (int i = 0; i < mobile.length(); i++) {
            if(!Character.isDigit(mobile.charAt(i))) {
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
    public String getMobileNumb() {
        return mobileNumb;
    }

    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return nif.equals(citizen.nif) && name.equals(citizen.name) && address.equals(citizen.address) && mobileNumb.equals(citizen.mobileNumb);
    }



        @Override
    public String toString () {
        return "Citizen: " + this.name + " " + this.address + " " + this.mobileNumb;
    }
}