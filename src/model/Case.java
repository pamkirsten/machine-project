package model;

public class Case {
    private String user = "Empty";
    private int casenum;
    private String dateReported = "Empty";


    public Case(){

    }


    public int getCasenum() {
        return casenum;
    }

    public void setCasenum(int casenum) {
        this.casenum = casenum;

    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String date) {
        this.dateReported = date;
    }

    public String getUsername() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }

}

