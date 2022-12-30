package services;

import data.Goal;
import publicadministration.Citizen;
import services.exceptions.IncorrectVerificationException;
import services.interfaces.GPD;

import java.net.ConnectException;

public class GPDclass implements GPD {
    Citizen citizen;
    Goal goal;

    public GPDclass(Citizen citizen, Goal goal) {
        this.citizen = citizen;
        this.goal = goal;
    }
    
    @Override
    public boolean verifyData(Citizen persData, Goal goal)
            throws IncorrectVerificationException, ConnectException {
        return true; //TODO: implement
    }
}
