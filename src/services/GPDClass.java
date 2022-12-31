package services;

import data.Goal;
import publicadministration.Citizen;
import services.exceptions.IncorrectVerificationException;
import services.interfaces.GPD;

import java.net.ConnectException;

public class GPDClass implements GPD {
    Citizen citizen;
    Goal goal;

    public GPDClass(Citizen citizen, Goal goal) {
        this.citizen = citizen;
        this.goal = goal;
    }

    @Override
    public boolean verifyData(Citizen persData, Goal goal) throws IncorrectVerificationException, ConnectException {
        if(persData == null) throw new IncorrectVerificationException("Citizen is null");
        if(goal == null) throw new IncorrectVerificationException("Goal is null");
        return true;
    }
}