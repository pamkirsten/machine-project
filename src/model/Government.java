package model;

import java.util.ArrayList;

public class Government extends Citizen {

    public Database db = new Database();

    public Government(String firstname, String middlename, String lastname, String homeadress, String workadress, String phonenum, String email, String username, String password) {
        super(firstname, middlename, lastname, homeadress, workadress, phonenum, email, username, password);
    }

    public Government(){

    }

    public ArrayList<Case> getCases(String tracerName){
        ArrayList<Case> cases;
        cases = db.getCasesAssignedToTracer(tracerName);

        return cases;
    }

    public void setTraced(String casenum){
        db.setTraced(casenum);
    }

}
