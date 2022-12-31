package data;
import exceptions.WrongSmallCodeFormatException;

public class SmallCode {
    private final String smallCode;
    public SmallCode(String smallCode) throws WrongSmallCodeFormatException{
        checkValidSmallCode(smallCode);
        this.smallCode = smallCode;
    }

    private void checkValidSmallCode(String smallCode) throws WrongSmallCodeFormatException {
        if (smallCode == null) throw new NullPointerException("El SmallCode es null");
        if (smallCode.length() != 3)
            throw new WrongSmallCodeFormatException("La longitud del SmallCode no es la correcta");
        if (wrongSmallCodeFormat(smallCode))
            throw new WrongSmallCodeFormatException("El formato no es el correcto. (3 dígitos)");
    }

    private boolean wrongSmallCodeFormat(String smallCode) {
        char[] smallCodeArray = smallCode.toCharArray();
        for (char c : smallCodeArray) {
            return (!Character.isDigit(c));
        }
        return false;
    }
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallCode smallCode0 = (SmallCode) o;
        return smallCode.equals(smallCode0.smallCode);
    }
    public String getSmallCode () {
        return smallCode;
    }
    @Override
    public int hashCode () { return smallCode.hashCode(); }
    @Override
    public String toString () {
        return "SmallCode{" + "SmallCode ciudadano='" + smallCode + '\'' + '}';
    }


}