package data;

import data.exceptions.WrongNifFormatException;

/**
 * Essential data classes
 *
 */

final public class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;
    public Nif (String code) throws WrongNifFormatException {
        checkNif(code);
        this.nif = code;
    }

    private void checkNif(String code) throws WrongNifFormatException {
        int correctNifLength = 9;

        if (code == null) throw new NullPointerException("El nif es null");

        if (code.length() != correctNifLength)
            throw new WrongNifFormatException("La longitud del DNI no es la correcta");

        if (wrongNifFormat(code))
            throw new WrongNifFormatException("El format  no es el correcto. (8 nÃºmeros y una letra mayuscula)");
    }

    private boolean wrongNifFormat(String code) {
        int correctNumbers = 8;
        int correctCapitalLetters = 1;

        int capitalLetters = 0;
        int numbers = 0;

        char[] nif = code.toCharArray();
        for (char c : nif) {
            if (Character.isDigit(c)) numbers++;
            else if (Character.isUpperCase(c)) capitalLetters++;
        }
        return (numbers != correctNumbers || capitalLetters != correctCapitalLetters);
    }

    public String getNif () {
        return nif; }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }
    @Override
    public int hashCode () { return nif.hashCode(); }
    @Override
    public String toString () {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }

}
