package model;

/**
 * The Case Class
 */
public class Case {
    /** Username **/
    private String user = "Empty";
    /** Case Number **/
    private int casenum;
    /** Date Reported **/
    private String dateReported = "Empty";
    /** Contact Tracer Username **/
    private String tracerUsername = "000";
    /** Status **/
    private String status = "P";

    /**
     * Instantiates a new Case
     */
    public Case() {

    }

    /**
     * Gets case number
     *
     * @return the case number
     */
    public int getCasenum() {
        return casenum;
    }

    /**
     * Sets case number
     *
     * @param casenum the case number
     */
    public void setCasenum(int casenum) {
        this.casenum = casenum;
    }

    /**
     * Gets date reported
     *
     * @return the date reported
     */
    public String getDateReported() {
        return dateReported;
    }

    /**
     * Sets date reported
     *
     * @param date the date
     */
    public void setDateReported(String date) {
        this.dateReported = date;
    }

    /**
     * Gets username
     *
     * @return the username
     */
    public String getUsername() {
        return user;
    }

    /**
     * Sets username
     *
     * @param user the user
     */
    public void setUsername(String user) {
        this.user = user;
    }

    /**
     * Gets tracer username
     *
     * @return the tracer username
     */
    public String getTracerUsername() {
        return tracerUsername;
    }

    /**
     * Sets tracer username
     *
     * @param tracerUsername the tracer username
     */
    public void setTracerUsername(String tracerUsername) {
        this.tracerUsername = tracerUsername;
    }

    /**
     * Gets status if the user has been traced or not
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status if the user has been traced or not
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

