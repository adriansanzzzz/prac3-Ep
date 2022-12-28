package data;

import java.util.Arrays;

final public class DigitalSignature {
    private final byte[] signature;

    public DigitalSignature(byte[] signature) throws NullPointerException {
        if (signature == null) {
            throw new NullPointerException();
        }
        this.signature = signature;
    }
    public byte[] getDigitalSignature() {
        return this.signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature digit = (DigitalSignature) o;
        return Arrays.equals(signature, digit.signature);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(signature);
    }

    @Override
    public String toString() {
        return "DigitalSignature{" + "signature='" + Arrays.toString(signature) + '\'' + '}';
    }


}
