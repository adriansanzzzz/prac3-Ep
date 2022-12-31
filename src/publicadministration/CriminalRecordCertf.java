package publicadministration;

import data.DigitalSignature;
import data.Goal;
import data.Nif;

import java.util.Objects;

public class CriminalRecordCertf extends PDFDocument {
    private Nif nif;
    private String name;
    private Goal goal;
    private DigitalSignature digSign;
    private CrimConvictionsColl crimConvs;

    public CriminalRecordCertf(Nif nif, String name, Goal g, DigitalSignature ds, CrimConvictionsColl crimConvictionsColl) {

        checkCriminalRecordCertf(nif, name, g,ds,crimConvictionsColl);

        this.nif = nif;
        this.name = name;
        this.goal = g;
        this.digSign = ds;
        this.crimConvs = crimConvictionsColl;
    }
    public CriminalRecordCertf() {
        this.nif = null;
        this.name = null;
        this.goal = null;
        this.digSign = null;
        this.crimConvs = null;
    }

    public String getName() {
        return name;
    }

    void checkCriminalRecordCertf(Nif nif, String name, Goal goal, DigitalSignature digSign, CrimConvictionsColl crimConvs) throws NullPointerException {
        if (nif == null) {
            throw new NullPointerException("The criminalrecordcertf nif is not valid");
        }
        if (name == null) {
            throw new NullPointerException("The criminalrecordcertf name is not valid");
        }
        if (goal == null) {
            throw new NullPointerException("The criminalrecordcertf goal is null");
        }
        if (digSign == null) {
            throw new NullPointerException("The criminalrecordcertf diSign is null");
        }
        if (crimConvs == null) {
            throw new NullPointerException("The criminalrecordcertf crimConvs is null");
        }

    }
    public Nif getNif() {
        return nif;
    }
    public Goal getGoal() {
        return goal;
    }
    public DigitalSignature getDigSign() {
        return digSign;
    }
    public CrimConvictionsColl getCrimConvs() {
        return crimConvs;
    }

    public void equals(CriminalRecordCertf criminalRecordCertf) {
        if (this.nif.equals(criminalRecordCertf.getNif()) && this.name.equals(criminalRecordCertf.getName()) && this.goal.equals(criminalRecordCertf.getGoal()) && this.digSign.equals(criminalRecordCertf.getDigSign()) && this.crimConvs.equals(criminalRecordCertf.getCrimConvs())) {
            System.out.println("The criminal record certificate is equal");
        } else {
            System.out.println("The criminal record certificate is not equal");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CriminalRecordCertf crimrec)) return false;
        return Objects.equals(nif, crimrec.nif) && Objects.equals(name, crimrec.name) && Objects.equals(goal, crimrec.goal) && Objects.equals(digSign, crimrec.digSign) && Objects.equals(crimConvs, crimrec.crimConvs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, name, goal, digSign, crimConvs);
    }
}


