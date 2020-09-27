package model;

public class Profile {
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

    public String getWarningCode() { return warningCode; }

    public void setWarningCode(String warningCode) { this.warningCode = warningCode; }

    public String getWarningDate() { return warningDate; }

    public void setWarningDate(String warningDate) { this.warningDate = warningDate; }

    public int getNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(int notifyUser) {
        this.notifyUser = notifyUser;
    }



    public String gethCity() {
        return hCity;
    }

    public void sethCity(String hCity) {
        this.hCity = hCity;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive() {
        isPositive = true;
    }

    private int CaseNum;

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public int getCaseNum() {
        return CaseNum;
    }

    public void setCaseNum(int caseNum) {
        CaseNum = caseNum;
    }

    public Profile(String firstName, String middleName, String lastName, String homeAdd, String workAdd, String phoneNumber, String emailAdd, String username, String password) {
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

    public Profile(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getWorkAdd() {
        return workAdd;
    }

    public void setWorkAdd(String workAdd) {
        this.workAdd = workAdd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
