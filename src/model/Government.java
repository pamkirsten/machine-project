package model;

import java.util.ArrayList;

/**
 * The type Government.
 */
public class Government extends Citizen {

    /**
     * The Database.
     */
    public Database database = new Database();

    /**
     * Instantiates a new Government.
     *
     * @param firstname  the firstname
     * @param middlename the middlename
     * @param lastname   the lastname
     * @param homeadress the homeadress
     * @param workadress the workadress
     * @param phonenum   the phonenum
     * @param email      the email
     * @param username   the username
     * @param password   the password
     */
    public Government(String firstname, String middlename, String lastname, String homeadress, String workadress, String phonenum, String email, String username, String password) {
        super(firstname, middlename, lastname, homeadress, workadress, phonenum, email, username, password);
    }

    /**
     * Instantiates a new Government.
     */
    public Government() {

    }

    /**
     * Gets cases.
     *
     * @param tracerName the tracer name
     * @return the cases
     */
    public ArrayList<Case> getCases(String tracerName) {
        ArrayList<Case> cases;
        cases = database.getCasesAssignedToTracer(tracerName);

        return cases;
    }

    /**
     * Check if traced boolean.
     *
     * @param casenum the casenum
     * @return the boolean
     */
    public boolean checkIfTraced(int casenum) {
        return database.checkTraced(casenum);
    }

    /**
     * Sets traced.
     *
     * @param casenum the casenum
     */
    public void setTraced(String casenum) {
        database.setTraced(casenum);
    }

    /**
     * Notify users.
     *
     * @param exposedUsername the exposed username
     * @param code            the code
     * @param date            the date
     */
    public void notifyUsers(String exposedUsername, String code, String date) {
        database.setNotify(exposedUsername, code, date);
    }

    /**
     * Gets records.
     *
     * @param casenum the casenum
     * @param xNum    the x num
     * @return the records
     */
    public ArrayList<Establishment> getRecords(String casenum, int xNum) {
        return database.traceAccounts(casenum, xNum);
    }

}
