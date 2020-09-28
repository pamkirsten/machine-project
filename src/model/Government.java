package model;

import java.util.ArrayList;

/**
 * The Government Class
 */
public class Government extends Citizen {

    /**
     * The Database
     */
    public Database database = new Database();

    /**
     * Instantiates a new Government Object
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
     * Instantiates a new Government Object
     */
    public Government() {

    }

    /**
     * Gets all the Cases assigned to a Contact Tracer
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
     * Check if a case has already been traced
     *
     * @param casenum the casenum
     * @return the result
     */
    public boolean checkIfTraced(int casenum) {
        return database.checkTraced(casenum);
    }

    /**
     * Sets the status of a case to Traced
     *
     * @param casenum the casenum
     */
    public void setTraced(String casenum) {
        database.setTraced(casenum);
    }

    /**
     * Notify every user who has been exposed to a Positive Case
     *
     * @param exposedUsername the exposed username
     * @param code            the establishment code
     * @param date            the date the user was exposed
     */
    public void notifyUsers(String exposedUsername, String code, String date) {
        database.setNotify(exposedUsername, code, date);
    }

    /**
     * Traces all the users who have been exposed to a positive case
     *
     * @param casenum the casenum
     * @param xNum    the number of days before the positive case reported positive
     * @return the records
     */
    public ArrayList<Establishment> getRecords(String casenum, int xNum) {
        return database.traceAccounts(casenum, xNum);
    }

}
