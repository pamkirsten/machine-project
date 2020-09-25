package model;

public class Case {
    private String user = "Empty";
    private int casenum;
    private String dateReported = "Empty";
    private String tracerUsername = "Empty";
    private String status = "NotTraced";

    public Case() {

    }

    public int getCasenum() {
        return casenum;
    }

    public void setCasenum(int casenum) { this.casenum = casenum; }

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

    public String getTracerUsername() { return tracerUsername; }

    public void setTracerUsername(String tracerUsername) { this.tracerUsername = tracerUsername; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}

