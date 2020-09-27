package model;

public class Establishment {
    private String username = "Empty";
    private String estCode = "Empty";
    private String checkInDate = "Empty";
    private String checkInTime = "Empty";

    public Establishment() {

    }

    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
