package model;

/**
 * The type Account.
 */
public class Account {
    private String firstName = "Empty";
    private String middleName = "Empty";
    private String lastName = "Empty";

    private String homeAdd = "Empty";
    private String hCity = "Empty";
    private String workAdd = "Empty";

    private String phoneNumber = "Empty";
    private String emailAdd = "Empty";

    private String username = "Empty";
    private String password = "Empty";
    private String dateReported = "Empty";

    private int notifyUser = 0;
    private String warningCode = "Empty";
    private String warningDate = "Empty";
    private boolean isPositive = false;
    private int CaseNum;

    /**
     * Instantiates a new Account.
     *
     * @param firstName   the first name
     * @param middleName  the middle name
     * @param lastName    the last name
     * @param homeAdd     the home add
     * @param workAdd     the work add
     * @param phoneNumber the phone number
     * @param emailAdd    the email add
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
     * Instantiates a new Account.
     */
    public Account() {

    }

    /**
     * Gets warning code.
     *
     * @return the warning code
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * Sets warning code.
     *
     * @param warningCode the warning code
     */
    public void setWarningCode(String warningCode) {
        this.warningCode = warningCode;
    }

    /**
     * Gets warning date.
     *
     * @return the warning date
     */
    public String getWarningDate() {
        return warningDate;
    }

    /**
     * Sets warning date.
     *
     * @param warningDate the warning date
     */
    public void setWarningDate(String warningDate) {
        this.warningDate = warningDate;
    }

    /**
     * Gets notify user.
     *
     * @return the notify user
     */
    public int getNotifyUser() {
        return notifyUser;
    }

    /**
     * Sets notify user.
     *
     * @param notifyUser the notify user
     */
    public void setNotifyUser(int notifyUser) {
        this.notifyUser = notifyUser;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String gethCity() {
        return hCity;
    }

    /**
     * Sets city.
     *
     * @param hCity the h city
     */
    public void sethCity(String hCity) {
        this.hCity = hCity;
    }

    /**
     * Is positive boolean.
     *
     * @return the boolean
     */
    public boolean isPositive() {
        return isPositive;
    }

    /**
     * Sets positive.
     */
    public void setPositive() {
        isPositive = true;
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
     * @param dateReported the date reported
     */
    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets middle name.
     *
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets home add.
     *
     * @return the home add
     */
    public String getHomeAdd() {
        return homeAdd;
    }

    /**
     * Sets home add.
     *
     * @param homeAdd the home add
     */
    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    /**
     * Gets work add.
     *
     * @return the work add
     */
    public String getWorkAdd() {
        return workAdd;
    }

    /**
     * Sets work add.
     *
     * @param workAdd the work add
     */
    public void setWorkAdd(String workAdd) {
        this.workAdd = workAdd;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email add.
     *
     * @return the email add
     */
    public String getEmailAdd() {
        return emailAdd;
    }

    /**
     * Sets email add.
     *
     * @param emailAdd the email add
     */
    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
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

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
