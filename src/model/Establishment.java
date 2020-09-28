package model;

/**
 * The Establishment Class
 */
public class Establishment {
    /** Username **/
    private String username = "Empty";
    /** Establishment Code **/
    private String estCode = "Empty";
    /** Check In Date **/
    private String checkInDate = "Empty";
    /** Check In Time **/
    private String checkInTime = "Empty";

    /**
     * Instantiates a new Establishment Class
     */
    public Establishment() {

    }

    /**
     * Gets the Establishment Code
     *
     * @return the establishment code
     */
    public String getEstCode() {
        return estCode;
    }

    /**
     * Sets the Establishment Code
     *
     * @param estCode the establishment code
     */
    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    /**
     * Gets the Check-In Date
     *
     * @return the check in date
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the Check-In Date
     *
     * @param checkInDate the check in date
     */
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Gets Check-In Time
     *
     * @return the check in time
     */
    public String getCheckInTime() {
        return checkInTime;
    }

    /**
     * Sets the Check-In Date
     *
     * @param checkInTime the check in time
     */
    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    /**
     * Gets the username of the user
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
