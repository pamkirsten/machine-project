package model;

/**
 * The type Case.
 */
public class Case {
    private String user = "Empty";
    private int casenum;
    private String dateReported = "Empty";
    private String tracerUsername = "000";
    private String status = "P";

    /**
     * Instantiates a new Case.
     */
    public Case() {

    }

    /**
     * Gets casenum.
     *
     * @return the casenum
     */
    public int getCasenum() {
        return casenum;
    }

    /**
     * Sets casenum.
     *
     * @param casenum the casenum
     */
    public void setCasenum(int casenum) {
        this.casenum = casenum;
    }

    /**
     * Gets date reported.
     *
     * @return the date reported
     */
    public String getDateReported() {
        return dateReported;
    }

    /**
     * Sets date reported.
     *
     * @param date the date
     */
    public void setDateReported(String date) {
        this.dateReported = date;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return user;
    }

    /**
     * Sets username.
     *
     * @param user the user
     */
    public void setUsername(String user) {
        this.user = user;
    }

    /**
     * Gets tracer username.
     *
     * @return the tracer username
     */
    public String getTracerUsername() {
        return tracerUsername;
    }

    /**
     * Sets tracer username.
     *
     * @param tracerUsername the tracer username
     */
    public void setTracerUsername(String tracerUsername) {
        this.tracerUsername = tracerUsername;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

