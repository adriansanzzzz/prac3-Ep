package data;


import exceptions.WrongPasswordFormatException;

/**
 * Essential data classes
 */
final public class Password {
    private final String password;

    public Password(String pswd) throws WrongPasswordFormatException {
        checkPassword(pswd);
        this.password = pswd;
    }

    private void checkPassword(String pass) throws WrongPasswordFormatException {
        if (pass == null)
            throw new NullPointerException("El valor de la contraseña es null");
        else if (pass.length() < 4)
            throw new WrongPasswordFormatException("La longitud de la contraseña no es la correcta");
        else if (!passwordHasDigit(pass))
            throw new WrongPasswordFormatException("La contraseña no tiene dígitos");
        else if (!passwordHasChar(pass))
            throw new WrongPasswordFormatException("La contraseña no tiene caracteres");
        else if (!passwordHasSpecialChar(pass))
            throw new WrongPasswordFormatException("La contrasenya no tiene caracteres especiales");
    }

    private boolean passwordHasDigit(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasChar(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (Character.isAlphabetic(c))
                return true;
        }
        return false;
    }

    private boolean passwordHasSpecialChar(String pass) {
        char[] passwd = pass.toCharArray();
        for (char c : passwd) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isSpaceChar(c))
                return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password pass = (Password) o;
        return password.equals(pass.password);
    }

    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public String toString() {
        return "Password{" + "password ciudadano='" + password + '\'' + '}';
    }
}
