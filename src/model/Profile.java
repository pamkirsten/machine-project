package model;

public class Profile {
    private String firstname = "Empty";
    private String middlename = "Empty";
    private String lastname = "Empty";

    private String homeadress = "Empty";
    private String workadress = "Empty";

    private String phonenum = "Empty";
    private String email = "Empty";

    private String username = "Empty";
    private String password = "Empty";
    private String dateReported = "Empty";
    private boolean isPositive = false;

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
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

    public Profile(String firstname, String middlename, String lastname, String homeadress, String workadress, String phonenum, String email, String username, String password) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;

        this.homeadress = homeadress;
        this.workadress = workadress;

        this.phonenum = phonenum;
        this.email = email;

        this.username = username;
        this.password = password;
    }

    public Profile(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHomeadress() {
        return homeadress;
    }

    public void setHomeadress(String homeadress) {
        this.homeadress = homeadress;
    }

    public String getWorkadress() {
        return workadress;
    }

    public void setWorkadress(String workadress) {
        this.workadress = workadress;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
