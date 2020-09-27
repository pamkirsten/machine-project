package model;

/**
 * The type Establishment.
 */
public class Establishment {
    private String username = "Empty";
    private String estCode = "Empty";
    private String checkInDate = "Empty";
    private String checkInTime = "Empty";

    /**
     * Instantiates a new Establishment.
     */
    public Establishment() {

    }

    /**
     * Gets est code.
     *
     * @return the est code
     */
    public String getEstCode() {
        return estCode;
    }

    /**
     * Sets est code.
     *
     * @param estCode the est code
     */
    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    /**
     * Gets check in date.
     *
     * @return the check in date
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets check in date.
     *
     * @param checkInDate the check in date
     */
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Gets check in time.
     *
     * @return the check in time
     */
    public String getCheckInTime() {
        return checkInTime;
    }

    /**
     * Sets check in time.
     *
     * @param checkInTime the check in time
     */
    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
