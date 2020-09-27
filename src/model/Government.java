package model;

import java.util.ArrayList;

public class Government extends Citizen {

    public Database database = new Database();

    public Government(String firstname, String middlename, String lastname, String homeadress, String workadress, String phonenum, String email, String username, String password) {
        super(firstname, middlename, lastname, homeadress, workadress, phonenum, email, username, password);
    }

    public Government() {

    }

    public ArrayList<Case> getCases(String tracerName) {
        ArrayList<Case> cases;
        cases = database.getCasesAssignedToTracer(tracerName);

        return cases;
    }

    public boolean checkIfTraced(int casenum) {
        return database.checkTraced(casenum);
    }

    public void setTraced(String casenum) {
        database.setTraced(casenum);
    }

    public void notifyUsers(String exposedUsername, String code, String date) {
        database.setNotify(exposedUsername, code, date);
    }

    public ArrayList<Establishment> getRecords(String casenum, int xNum) {
        return database.traceAccounts(casenum, xNum);
    }

}
