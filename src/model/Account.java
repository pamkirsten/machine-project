package model;

/**
 * The Account Class
 */
public class Account {
    /** First Name **/
    private String firstName = "Empty";
    /** Middle Name **/
    private String middleName = "Empty";
    /** Last Name **/
    private String lastName = "Empty";

    /** Home Address **/
    private String homeAdd = "Empty";
    /** Home City Address **/
    private String hCity = "Empty";
    /** Work Address **/
    private String workAdd = "Empty";

    /** Phone Number **/
    private String phoneNumber = "Empty";
    /** Email Address **/
    private String emailAdd = "Empty";

    /** Username **/
    private String username = "Empty";
    /** Password **/
    private String password = "Empty";
    /** Date Reported **/
    private String dateReported = "Empty";

    /** Notify Value **/
    private int notifyUser = 0;
    /** Warning Establishment Code **/
    private String warningCode = "Empty";
    /** Warning Date **/
    private String warningDate = "Empty";
    /** Positive Value **/
    private boolean isPositive = false;
    /** Case Number **/
    private int CaseNum;

    /**
     * Instantiates a new Account
     *
     * @param firstName   the first name
     * @param middleName  the middle name
     * @param lastName    the last name
     * @param homeAdd     the home add
     * @param workAdd     the work add
     * @param phoneNumber the phone number
     * @param emailAdd    the email address
     * @param username    the username
     * @param password    the password
     */
    public Account(String firstName, String middleName, String lastName, String homeAdd, String workAdd, String phoneNumber, String emailAdd, String username, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.homeAdd = homeAdd;
        this.workAdd = workAdd;

        this.phoneNumber = phoneNumber;
        this.emailAdd = emailAdd;

        this.username = username;
        this.password = password;
    }

    /**
     * Instantiates a new Account
     */
    public Account() {

    }

    /**
     * Gets warning code
     *
     * @return the warning code
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * Sets warning code
     *
     * @param warningCode the warning code
     */
    public void setWarningCode(String warningCode) {
        this.warningCode = warningCode;
    }

    /**
     * Gets warning date
     *
     * @return the warning date
     */
    public String getWarningDate() {
        return warningDate;
    }

    /**
     * Sets warning date
     *
     * @param warningDate the warning date
     */
    public void setWarningDate(String warningDate) {
        this.warningDate = warningDate;
    }

    /**
     * Gets notify user value
     *
     * @return the notify user value
     */
    public int getNotifyUser() {
        return notifyUser;
    }

    /**
     * Sets notify user value
     *
     * @param notifyUser the notify user value
     */
    public void setNotifyUser(int notifyUser) {
        this.notifyUser = notifyUser;
    }

    /**
     * Gets home city of the user
     *
     * @return the city
     */
    public String gethCity() {
        return hCity;
    }

    /**
     * Sets home city of the user
     *
     * @param hCity the home city
     */
    public void sethCity(String hCity) {
        this.hCity = hCity;
    }

    /**
     * Checks if the user is Positive
     *
     * @return the result
     */
    public boolean isPositive() {
        return isPositive;
    }

    /**
     * Sets the user's isPositive to true
     */
    public void setPositive() {
        isPositive = true;
    }

    /**
     * Returns the date when the user reported positive
     *
     * @return the date reported
     */
    public String getDateReported() {
        return dateReported;
    }

    /**
     * Sets the date when the user reported positive
     *
     * @param dateReported the date reported
     */
    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    /**
     * Gets first name
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets middle name
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets middle name
     *
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets home address
     *
     * @return the home add
     */
    public String getHomeAdd() {
        return homeAdd;
    }

    /**
     * Sets home address
     *
     * @param homeAdd the home add
     */
    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    /**
     * Gets work address
     *
     * @return the work add
     */
    public String getWorkAdd() {
        return workAdd;
    }

    /**
     * Sets work address
     *
     * @param workAdd the work add
     */
    public void setWorkAdd(String workAdd) {
        this.workAdd = workAdd;
    }

    /**
     * Gets phone number
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email address
     *
     * @return the email add
     */
    public String getEmailAdd() {
        return emailAdd;
    }

    /**
     * Sets email address
     *
     * @param emailAdd the email add
     */
    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    /**
     * Gets username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
