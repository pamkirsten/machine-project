package model;

import javafx.scene.control.DatePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Database {

    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static final ArrayList<Government> governments = new ArrayList<>();
    private static final ArrayList<Case> cases = new ArrayList<>();
    private static final ArrayList<Citizen> citizens = new ArrayList<>();
    private static final ArrayList<Tracer> tracers = new ArrayList<>();
    private static ArrayList<Establishment> establishments = new ArrayList<>();
    private static int casenum = cases.size();
    private final Citizen citizen = new Citizen();

    public void startProgram() {
        openAccountsFile();
        openEstablishmentsFile();
        openCasesFile();
    }

    public void openAccountsFile() {
        try {
            FileWriter text = new FileWriter("masterlist.txt", true);
        } catch (IOException e) {
            System.out.println("Error in opening masterlist.txt");
        }

        String tempS, users, lines;
        String[] account;

        try {
            Scanner readtxt = new Scanner(new File("masterlist.txt"));

            while (readtxt.hasNextLine()) {
                tempS = readtxt.nextLine();
                account = tempS.trim().split("\\s+");

                if (account[1].equals("citizen")) {
                    Citizen temp = new Citizen();

                    temp.setUsername(account[0]);

                    users = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(users));

                    lines = readuser.nextLine();
                    temp.setPassword(lines);

                    tempS = readuser.nextLine();
                    account = tempS.split(",");
                    temp.setFirstName(account[0]);
                    temp.setMiddleName(account[1]);
                    temp.setLastName(account[2]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp.setHomeAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp.sethCity(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp.setWorkAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp.setPhoneNumber(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp.setEmailAdd(account[1]);

                    accounts.add(temp);
                } else if (account[1].equals("official")) {
                    Government temp1 = new Government();

                    temp1.setUsername(account[0]);

                    users = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(users));

                    lines = readuser.nextLine();
                    temp1.setPassword(lines);

                    tempS = readuser.nextLine();
                    account = tempS.split(",");
                    temp1.setFirstName(account[0]);
                    temp1.setMiddleName(account[1]);
                    temp1.setLastName(account[2]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp1.setHomeAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp1.setWorkAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp1.setPhoneNumber(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp1.setEmailAdd(account[1]);

                    accounts.add(temp1);
                } else if (account[1].equals("tracer")) {
                    Tracer temp2 = new Tracer();

                    temp2.setUsername(account[0]);

                    users = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(users));

                    lines = readuser.nextLine();
                    temp2.setPassword(lines);

                    tempS = readuser.nextLine();
                    account = tempS.split(",");
                    temp2.setFirstName(account[0]);
                    temp2.setMiddleName(account[1]);
                    temp2.setLastName(account[2]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp2.setHomeAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp2.setWorkAdd(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp2.setPhoneNumber(account[1]);

                    tempS = readuser.nextLine();
                    account = tempS.split(":");
                    temp2.setEmailAdd(account[1]);

                    accounts.add(temp2);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error in reading masterlist.txt");
        }
    }

    public void openCasesFile() {
        try {
            FileWriter text = new FileWriter("positivecases.txt", true);
        } catch (IOException e) {
            System.out.println("Error in opening positivecases.txt");
        }

        String tempstring;
        String[] temparray;

        try {
            Scanner sc = new Scanner(new File("positivecases.txt"));

            while (sc.hasNextLine()) {
                tempstring = sc.nextLine();
                temparray = tempstring.split(" ");

                Case cases = new Case();

                cases.setCasenum(Integer.parseInt(temparray[0]));
                cases.setUsername(temparray[1]);
                cases.setDateReported(temparray[2]);
                cases.setTracerUsername(temparray[3]);
                cases.setStatus(temparray[4]);

                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).getUsername().equals(cases.getUsername())) {
                        accounts.get(i).setPositive();
                        accounts.get(i).setDateReported(temparray[2]);
                    }
                }

                Database.cases.add(cases);
                casenum++;
            }
        } catch (IOException e) {
            System.out.println("Error in writing positivecases.txt");
        }
    }

    public void openEstablishmentsFile() {
        try {
            FileWriter text = new FileWriter("establishmentdata.txt", true);
        } catch (IOException e) {
            System.out.println("Error in opening establishmentdata.txt");
        }

        String tmpstring, user = "";
        String[] temp;

        try {
            Scanner readtxt = new Scanner(new File("establishmentdata.txt"));

            while (readtxt.hasNextLine()) {
                tmpstring = readtxt.nextLine();

                if (tmpstring.equals("")) {
                    tmpstring = readtxt.nextLine();
                }

                temp = tmpstring.split(" ");

                Establishment establishment = new Establishment();

                if (temp.length == 1) {
                    user = temp[0];
                } else {
                    establishment.setUsername(user);
                    establishment.setEstCode(temp[0]);
                    establishment.setCheckInDate(temp[1]);
                    establishment.setCheckInTime(temp[2]);
                    establishments.add(establishment);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in reading establishmentdata.txt");
        }

    }

    public void createAccount(Citizen temp) {
        accounts.add(temp);
        updateAccountsFile();
    }

    public void createCase(Case temp) {
        cases.add(temp);
        updateCasesFile();
    }

    public void createEstablishment(Establishment temp) {
        establishments.add(temp);
        updateEstablishmentsFile();
    }

    public void createOfficial(Government temp) {
        for (int i = 0; i < accounts.size(); i++) {
            if (temp.getUsername().equals(accounts.get(i).getUsername())) {
                temp.setFirstName(accounts.get(i).getFirstName());
                temp.setMiddleName(accounts.get(i).getMiddleName());
                temp.setLastName(accounts.get(i).getLastName());
                temp.setHomeAdd(accounts.get(i).getHomeAdd());
                temp.sethCity(accounts.get(i).gethCity());
                temp.setWorkAdd(accounts.get(i).getWorkAdd());
                temp.setPhoneNumber(accounts.get(i).getPhoneNumber());
                temp.setEmailAdd(accounts.get(i).getEmailAdd());
                temp.setUsername(accounts.get(i).getUsername());
                temp.setPassword(accounts.get(i).getPassword());
                temp.setDateReported(accounts.get(i).getDateReported());
                temp.setNotifyUser(accounts.get(i).getNotifyUser());
                temp.setWarningCode(accounts.get(i).getWarningCode());
                temp.setWarningDate(accounts.get(i).getWarningDate());
                accounts.remove(i);
            }
        }

        accounts.add(temp);
        updateAccountsFile();
    }

    public void createTracer(Tracer temp) {
        for (int i = 0; i < accounts.size(); i++) {
            if (temp.getUsername().equals(accounts.get(i).getUsername())) {
                temp.setFirstName(accounts.get(i).getFirstName());
                temp.setMiddleName(accounts.get(i).getMiddleName());
                temp.setLastName(accounts.get(i).getLastName());
                temp.setHomeAdd(accounts.get(i).getHomeAdd());
                temp.sethCity(accounts.get(i).gethCity());
                temp.setWorkAdd(accounts.get(i).getWorkAdd());
                temp.setPhoneNumber(accounts.get(i).getPhoneNumber());
                temp.setEmailAdd(accounts.get(i).getEmailAdd());
                temp.setUsername(accounts.get(i).getUsername());
                temp.setPassword(accounts.get(i).getPassword());
                temp.setDateReported(accounts.get(i).getDateReported());
                temp.setNotifyUser(accounts.get(i).getNotifyUser());
                temp.setWarningCode(accounts.get(i).getWarningCode());
                temp.setWarningDate(accounts.get(i).getWarningDate());
                accounts.remove(i);
            }
        }

        accounts.add(temp);
        updateAccountsFile();
    }

    public void updateAccountsFile() {
        try {
            FileWriter text = new FileWriter("masterlist.txt");
        } catch (IOException e) {
            System.out.println("Error in opening masterlist.txt");
        }

        for (int i = 0; i < accounts.size(); i++) {
            try {
                FileWriter text = new FileWriter("masterlist.txt", true);

                text.write(accounts.get(i).getUsername());
                text.write(" ");


                if (accounts.get(i) instanceof Government) {
                    text.write("official");
                    text.write("\n");
                } else if (accounts.get(i) instanceof Tracer) {
                    text.write("tracer");
                    text.write("\n");
                } else if (accounts.get(i) instanceof Citizen) {
                    text.write("citizen");
                    text.write("\n");
                }

                try {
                    String user = "accounts/" + accounts.get(i).getUsername() + ".act";
                    FileWriter text1 = new FileWriter(user);

                    text1.write(accounts.get(i).getPassword());
                    text1.write("\n");
                    text1.write(accounts.get(i).getFirstName() + "," + accounts.get(i).getMiddleName() + "," + accounts.get(i).getLastName());
                    text1.write("\n");
                    text1.write("HOME:" + accounts.get(i).getHomeAdd());
                    text1.write("\n");
                    text1.write("CITY:" + accounts.get(i).gethCity());
                    text1.write("\n");
                    text1.write("OFFICE:" + accounts.get(i).getWorkAdd());
                    text1.write("\n");
                    text1.write("PHONE:" + accounts.get(i).getPhoneNumber());
                    text1.write("\n");
                    text1.write("EMAIL:" + accounts.get(i).getEmailAdd());
                    text1.write("\n");

                    text1.close();
                } catch (IOException e) {
                    System.out.println("Error in Creating Account");
                }
                text.close();
            } catch (IOException e) {
                System.out.println("Error in writing masterlist.txt");
            }
        }
    }

    public void updateAccountsData(String user, String pass, String first, String middle, String last, String home, String work, String phone, String email) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(user)) {
                accounts.get(i).setPassword(pass);
                accounts.get(i).setFirstName(first);
                accounts.get(i).setMiddleName(middle);
                accounts.get(i).setLastName(last);
                accounts.get(i).setHomeAdd(home);
                accounts.get(i).setWorkAdd(work);
                accounts.get(i).setPhoneNumber(phone);
                accounts.get(i).setEmailAdd(email);

                updateAccountsFile();
                break;
            }
        }
    }

    public void updateCasesFile() {
        try {
            FileWriter text = new FileWriter("positivecases.txt");
        } catch (IOException e) {
            System.out.println("Error in opening positivecases.txt");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < cases.size(); i++) {
            try {
                FileWriter text = new FileWriter("positivecases.txt", true);

                text.write(String.valueOf(cases.get(i).getCasenum()));
                text.write(" ");
                text.write(cases.get(i).getUsername());
                text.write(" ");
                text.write(cases.get(i).getDateReported());
                text.write(" ");
                text.write(cases.get(i).getTracerUsername());
                text.write(" ");
                text.write(cases.get(i).getStatus());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error in writing positivecases.txt");
            }
        }
    }

    public void updateEstablishmentsFile() {
        arrangeEstablishments();

        try {
            FileWriter text = new FileWriter("establishmentdata.txt");
        } catch (IOException e) {
            System.out.println("Error in opening establishmentdata.txt");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < establishments.size(); i++) {
            if (i == 0) {
                try {
                    FileWriter text = new FileWriter("establishmentdata.txt", true);

                    text.write(establishments.get(i).getUsername());
                    text.write("\n");

                    text.close();
                } catch (IOException e) {
                    System.out.println("Error in writing establishmentdata.txt");
                }
            }

            if (i > 0) {
                temp = establishments.get(i).getUsername();
                temp1 = establishments.get(i - 1).getUsername();

                if (!(temp.equals(temp1))) {
                    try {
                        FileWriter text = new FileWriter("establishmentdata.txt", true);

                        text.write("\n");
                        text.write(establishments.get(i).getUsername());
                        text.write("\n");

                        text.close();
                    } catch (IOException e) {
                        System.out.println("Error in writing establishmentdata.txt");
                    }
                }
            }

            try {
                FileWriter text = new FileWriter("establishmentdata.txt", true);

                text.write(establishments.get(i).getEstCode());
                text.write(" ");
                text.write(establishments.get(i).getCheckInDate());
                text.write(" ");
                text.write(establishments.get(i).getCheckInTime());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error in writing establishmentdata.txt");
            }
        }
    }

    public boolean checkUsername(String userinput) {
        for (int i = 0; i < accounts.size(); i++) {
            if (userinput.equals(accounts.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String user, String pass) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(user)) {
                return accounts.get(i).getPassword().equals(pass);
            }
        }
        return false;
    }

    public boolean checkUsernameEqual(String userinput) {
        for (int i = 0; i < accounts.size(); i++) {
            if (userinput.equals(accounts.get(i).getUsername())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPasswordEqual(String userinput, String userinput1) {
        return userinput.equals(userinput1);
    }

    public int checkRole(String username) {
        int role = -99;

        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {

                if (accounts.get(i) instanceof Government) {
                    role = 1;
                    return role;

                } else if (accounts.get(i) instanceof Tracer) {
                    role = 2;
                    return role;
                } else if (accounts.get(i) instanceof Citizen || accounts.get(i) instanceof Account) {
                    role = 0;
                    return role;
                }
            }
        }
        return role;
    }

    public boolean checkDuration(DatePicker StartDate, DatePicker EndDate, String date) {
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < cases.size(); i++) {
            if ((date.compareTo(startdate) >= 0) && (date.compareTo(enddate) <= 0)) {
                return true;
            }
        }
        return false;
    }

    public int checkDurAndCity(String City, DatePicker StartDate, DatePicker EndDate) {
        int numCases = 0;
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < cases.size(); j++) {
                    if (accounts.get(i).getUsername().equalsIgnoreCase(cases.get(j).getUsername())) {
                        if ((cases.get(j).getDateReported().compareTo(startdate) >= 0) && (cases.get(j).getDateReported().compareTo(enddate) <= 0)) {
                            numCases++;
                        }
                    }
                }
            }
        }

        return numCases;
    }

    public int checkIfReported(String user) {
        int report = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equalsIgnoreCase(user)) {
                if (accounts.get(i).isPositive()) {
                    report = 1;
                }
            }
        }

        return report;
    }

    public int checkNotify(String username) {
        int notify = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equalsIgnoreCase(username)) {
                if (accounts.get(i).getNotifyUser() != 1) {
                    if (accounts.get(i).getDateReported() != "Empty") {
                        for (int j = accounts.size(); i < 0; i--) {

                            System.out.println("dateReported = " + accounts.get(i).getDateReported());

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
                            String ndate = accounts.get(j).getDateReported();

                            LocalDate localDate = LocalDate.parse(ndate, formatter);

                            if (ChronoUnit.DAYS.between(localDate, LocalDateTime.now()) >= 14) {
                                System.out.println("days = " + ChronoUnit.DAYS.between(localDate, LocalDateTime.now()));
                                accounts.get(i).setNotifyUser(2);
                            }
                        }
                    }
                }
                notify = accounts.get(i).getNotifyUser();
            }
        }

        return notify;
    }

    public boolean checkTraced(int casenum) {
        for (int i = 0; i < cases.size(); i++) {
            if (casenum == cases.get(i).getCasenum()) {
                if (cases.get(i).getStatus().equals("T")) {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkCityCases(String City) {
        int numCases = 0;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < cases.size(); j++) {
                    if (accounts.get(i).getUsername().equalsIgnoreCase(cases.get(j).getUsername())) {
                        numCases += 1;
                    }
                }
            }

        }
        return numCases;
    }

    public boolean getPositive(String user) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(user)) {
                return accounts.get(i).isPositive();
            }
        }
        return false;
    }

    public String getDateReported(String user) {
        String datereported = "empty";
        for (int i = 0; i < cases.size(); i++) {
            if (cases.get(i).getUsername().equals(user)) {
                datereported = cases.get(i).getDateReported();
                return datereported;
            }
        }
        return datereported;
    }

    public int getCaseNum(String user) {
        int cNum = 0;
        for (int i = 0; i < cases.size(); i++) {
            if (cases.get(i).getUsername().equals(user)) {
                cNum = cases.get(i).getCasenum();
                return cNum;
            }
        }
        return cNum;
    }

    public String getWarningDate(String username) {
        String date = "00/00/0000";
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equalsIgnoreCase(username)) {
                if (accounts.get(i).getWarningDate() != "Empty") {
                    date = accounts.get(i).getWarningDate();
                }
            }
        }
        return date;
    }

    public String getWarningEst(String username) {
        String est = "Empty";
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equalsIgnoreCase(username)) {
                if (accounts.get(i).getWarningCode() != "Empty") {
                    est = accounts.get(i).getWarningCode();
                }
            }
        }
        return est;
    }

    public void setPositive(String user) {
        increment();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(user)) {
                accounts.get(i).setPositive();
                accounts.get(i).setNotifyUser(0);
            }
        }

        for (int i = 0; i < cases.size(); i++) {
            if (cases.get(i).getUsername().equals(user)) {
                cases.get(i).setCasenum(casenum);

            }
        }
    }

    public void setNotify(String exposedName, String code, String date) {
        for (int i = 0; i < accounts.size(); i++) {
            if (exposedName.equals(accounts.get(i).getUsername())) {
                accounts.get(i).setNotifyUser(1);
                accounts.get(i).setWarningCode(code);
                accounts.get(i).setWarningDate(date);
            }
        }
    }

    public void setTraced(String casenum) {
        for (int i = 0; i < cases.size(); i++) {
            if (casenum.equals(Integer.toString(cases.get(i).getCasenum()))) {
                cases.get(i).setStatus("T");
            }
        }
    }

    public ArrayList<Case> getCasesAssignedToTracer(String tracerUN) {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < Database.cases.size(); i++) {
            if (Database.cases.get(i).getTracerUsername().equals(tracerUN)) {
                if (Database.cases.get(i).getStatus().equals("P")) {
                    cases.add(Database.cases.get(i));
                }
            }
        }

        return cases;
    }

    public ArrayList<Case> getUnassignedCases() {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < Database.cases.size(); i++) {
            if (Database.cases.get(i).getTracerUsername().equals("000")) {
                cases.add(Database.cases.get(i));
            }
        }

        return cases;
    }

    public ArrayList<Case> getPositiveFromDateRange(DatePicker start, DatePicker end) {
        ArrayList<Case> cases = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).isPositive()) {
                if (checkDuration(start, end, accounts.get(i).getDateReported())) {
                    for (int j = 0; j < Database.cases.size(); j++) {
                        if (accounts.get(i).getUsername().equals(Database.cases.get(j).getUsername())) {
                            cases.add(Database.cases.get(j));
                        }
                    }
                }
            }
        }

        return cases;
    }

    public ArrayList<Establishment> traceAccounts(String caseNum, int xNum) {

        Case positiveUser = new Case();

        // Get Reported Case of Positive Person
        for (int i = 0; i < cases.size(); i++) {
            if (Integer.toString(cases.get(i).getCasenum()).equals(caseNum)) {
                positiveUser = cases.get(i);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
        LocalDate reportdate = LocalDate.parse(positiveUser.getDateReported(), formatter);

        ArrayList<Establishment> positiveRecords = new ArrayList<>();

        // Store CheckIns of Positive Person that is within xNum Days
        for (int j = 0; j < establishments.size(); j++) {
            LocalDate tempdate = LocalDate.parse(establishments.get(j).getCheckInDate(), formatter);

            if (establishments.get(j).getUsername().equals(positiveUser.getUsername())) {
                if (reportdate.compareTo(tempdate) <= 0) {
                    positiveRecords.add(establishments.get(j));
                }
            }
        }

        ArrayList<Establishment> records = new ArrayList<>();

        // Compare All Establishment Records to the Establishment Records of the Positive
        for (int k = 0; k < establishments.size(); k++) {
            if (!establishments.get(k).getUsername().equals(positiveUser.getUsername())) { // Check if Record Username is not Equal to the Positive User's Record Username
                LocalDate citizenDate = LocalDate.parse(establishments.get(k).getCheckInDate(), formatter);
                for (int l = 0; l < positiveRecords.size(); l++) {
                    LocalDate positiveDate = LocalDate.parse(positiveRecords.get(l).getCheckInDate(), formatter);
                    if (positiveDate.compareTo(citizenDate) == 0) { // Check if Two Record Dates Match
                        if (Integer.parseInt(establishments.get(k).getCheckInTime()) >= Integer.parseInt(positiveRecords.get(l).getCheckInTime())) { // Check if Two Times Overlap
                            if (positiveRecords.get(l).getEstCode().equals(establishments.get(k).getEstCode())) { // Check if Two Establishment Codes Match
                                records.add(establishments.get(k));
                            }
                        }
                    }
                }
            }
        }

        return records;
    }

    public void arrangeEstablishments() {
        ArrayList<String> users = new ArrayList<>();
        ArrayList<Establishment> visittemp = new ArrayList<>();

        for (int i = 0; i < establishments.size(); i++) {
            if (i == 0) {
                users.add(establishments.get(i).getUsername());
            }
            for (int x = 0; x < users.size(); x++) {
                if (establishments.get(i).getUsername().equals(users.get(x))) {
                    break;
                }
                if (users.size() == x + 1) {
                    users.add(establishments.get(i).getUsername());
                }
            }
        }

        for (int z = 0; z < users.size(); z++) {
            for (int y = 0; y < establishments.size(); y++) {
                if (users.get(z).equals(establishments.get(y).getUsername())) {
                    visittemp.add(establishments.get(y));
                }
            }
        }

        establishments = visittemp;

        System.out.println(Arrays.toString(users.toArray()));
    }

    public int givenDuration(DatePicker StartDate, DatePicker EndDate) {
        int numCases = 0;
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < cases.size(); i++) {
            if ((cases.get(i).getDateReported().compareTo(startdate) >= 0) && (cases.get(i).getDateReported().compareTo(enddate) <= 0)) {
                numCases++;
            }
        }
        return numCases;
    }

    public void increment() {
        casenum += 1;
    }

    public int removeAccount(String username) {
        int check = 0, role = checkRole(username);
        Citizen temp = new Citizen();

        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {
                if (role == 1 || role == 2) {
                    temp.setFirstName(accounts.get(i).getFirstName());
                    temp.setMiddleName(accounts.get(i).getMiddleName());
                    temp.setLastName(accounts.get(i).getLastName());
                    temp.setHomeAdd(accounts.get(i).getHomeAdd());
                    temp.sethCity(accounts.get(i).gethCity());
                    temp.setWorkAdd(accounts.get(i).getWorkAdd());
                    temp.setPhoneNumber(accounts.get(i).getPhoneNumber());
                    temp.setEmailAdd(accounts.get(i).getEmailAdd());
                    temp.setUsername(accounts.get(i).getUsername());
                    temp.setPassword(accounts.get(i).getPassword());
                    temp.setDateReported(accounts.get(i).getDateReported());
                    temp.setNotifyUser(accounts.get(i).getNotifyUser());
                    temp.setWarningCode(accounts.get(i).getWarningCode());
                    temp.setWarningDate(accounts.get(i).getWarningDate());

                    accounts.remove(i);
                    accounts.add(temp);
                    return 1;
                }
            }
        }

        return check;
    }

    public void endProgram() {
        updateAccountsFile();
        updateEstablishmentsFile();
        updateCasesFile();
    }
}