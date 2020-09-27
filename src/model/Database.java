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

    private static final ArrayList<Profile> profiles = new ArrayList<>();
    private static ArrayList<Visit> visits = new ArrayList<>();
    private static final ArrayList<Government> GOVERNMENTS = new ArrayList<>();
    private static final ArrayList<Case> CASES = new ArrayList<>();
    private static final ArrayList<Citizen> CITIZENS = new ArrayList<>();
    private static final ArrayList<Tracer> TRACERS = new ArrayList<>();
    private static int casenum = CASES.size();
    private final Citizen citizen = new Citizen();

    public void increment() {
        casenum += 1;
    }

    public void bootup() {
        System.out.println("@@@---BOOTING UP---@@@");
        opentxt();
        openVisitFile();
        openCases();
        printAllInfo();
        printAllVisits();
        caseprint();
        System.out.println("@@@---END---@@@");
    }

    public void shutdown() {
        System.out.println("@@@---SHUTTING DOWN---@@@");
        updatetext();
        updatevisits();
        saveCases();
        printAllInfo();
        printAllVisits();
        caseprint();
        System.out.println("@@@---END---@@@");
    }

    public void openVisitFile() {
        try {
            FileWriter text = new FileWriter("estvisitrec.txt", true);
        } catch (IOException e) {
            System.out.println("Error occurred in opening Visit");
        }

        String tmpstring, user = "";
        String[] temp;

        try {
            Scanner readtxt = new Scanner(new File("estvisitrec.txt"));

            while (readtxt.hasNextLine()) {
                tmpstring = readtxt.nextLine();

                if (tmpstring.equals("")) {
                    tmpstring = readtxt.nextLine();
                }

                temp = tmpstring.split(" ");

                Visit visit = new Visit();

                if (temp.length == 1) {
                    user = temp[0];
                } else {
                    visit.setUser(user);
                    visit.setCode(temp[0]);
                    visit.setDate(temp[1]);
                    visit.setTime(temp[2]);
                    visits.add(visit);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error occurred in reading visit");
        }

    }

    public void sortingVisit() {
        ArrayList<String> users = new ArrayList<>();
        ArrayList<Visit> visittemp = new ArrayList<>();

        for (int i = 0; i < visits.size(); i++) {
            if (i == 0) {
                users.add(visits.get(i).getUser());
            }
            for (int x = 0; x < users.size(); x++) {
                if (visits.get(i).getUser().equals(users.get(x))) {
                    break;
                }
                if (users.size() == x + 1) {
                    users.add(visits.get(i).getUser());
                }
            }
        }

        for (int z = 0; z < users.size(); z++) {
            for (int y = 0; y < visits.size(); y++) {
                if (users.get(z).equals(visits.get(y).getUser())) {
                    visittemp.add(visits.get(y));
                }
            }
        }

        visits = visittemp;

        System.out.println(Arrays.toString(users.toArray()));
    }

    public void updatevisits() {
        sortingVisit();

        try {
            FileWriter text = new FileWriter("estvisitrec.txt");
        } catch (IOException e) {
            System.out.println("Error occurred in opening VISIT");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < visits.size(); i++) {
            if (i == 0) {
                try {
                    FileWriter text = new FileWriter("estvisitrec.txt", true);

                    text.write(visits.get(i).getUser());
                    text.write("\n");

                    text.close();
                } catch (IOException e) {
                    System.out.println("Error occurred in writing Visit");
                }
            }

            if (i > 0) {
                temp = visits.get(i).getUser();
                temp1 = visits.get(i - 1).getUser();

                if (!(temp.equals(temp1))) {
                    try {
                        FileWriter text = new FileWriter("estvisitrec.txt", true);

                        text.write("\n");
                        text.write(visits.get(i).getUser());
                        text.write("\n");

                        text.close();
                    } catch (IOException e) {
                        System.out.println("Error occurred in writing Visit");
                    }
                }
            }

            try {
                FileWriter text = new FileWriter("estvisitrec.txt", true);

                text.write(visits.get(i).getCode());
                text.write(" ");
                text.write(visits.get(i).getDate());
                text.write(" ");
                text.write(visits.get(i).getTime());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error occurred in writing Visit");
            }
        }
    }

    public void openCases() {
        try {
            FileWriter text = new FileWriter("positivecases.txt", true);
        } catch (IOException e) {
            System.out.println("Error occurred in opening positivecases");
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

                for (int i = 0; i < profiles.size(); i++) {
                    if (profiles.get(i).getUsername().equals(cases.getUsername())) {
                        profiles.get(i).setPositive();
                        profiles.get(i).setDateReported(temparray[2]);
                    }
                }

                CASES.add(cases);
                casenum++;
            }
        } catch (IOException e) {
            System.out.println("Error occurred in writing positivecases");
        }
    }

    public void saveCases() {

        System.out.println("\n\n\nENTERED HERE\n\n\n");

        try {
            FileWriter text = new FileWriter("positivecases.txt");
        } catch (IOException e) {
            System.out.println("Error occurred in opening positivecases");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < CASES.size(); i++) {
            try {
                FileWriter text = new FileWriter("positivecases.txt", true);

                text.write(String.valueOf(CASES.get(i).getCasenum()));
                text.write(" ");
                text.write(CASES.get(i).getUsername());
                text.write(" ");
                text.write(CASES.get(i).getDateReported());
                text.write(" ");
                text.write(CASES.get(i).getTracerUsername());
                text.write(" ");
                text.write(CASES.get(i).getStatus());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error occurred in writing positivecases.txt");
            }
        }
    }

    public void opentxt() {
        try {
            FileWriter text = new FileWriter("masterlist.txt", true);
        } catch (IOException e) {
            System.out.println("Error occurred in opening MASTERLIST");
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

                    profiles.add(temp);
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

                    profiles.add(temp1);
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

                    profiles.add(temp2);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred in reading DATABASE");
        }
    }

    public void updatetext() {
        try {
            FileWriter text = new FileWriter("masterlist.txt");
        } catch (IOException e) {
            System.out.println("Error occurred in opening MASTERLIST");
        }

        for (int i = 0; i < profiles.size(); i++) {
            try {
                FileWriter text = new FileWriter("masterlist.txt", true);

                text.write(profiles.get(i).getUsername());
                text.write(" ");


                if (profiles.get(i) instanceof Government) {
                    text.write("official");
                    text.write("\n");
                } else if (profiles.get(i) instanceof Tracer) {
                    text.write("tracer");
                    text.write("\n");
                } else if (profiles.get(i) instanceof Citizen) {
                    text.write("citizen");
                    text.write("\n");
                }

                try {
                    String user = "accounts/" + profiles.get(i).getUsername() + ".act";
                    FileWriter text1 = new FileWriter(user);

                    text1.write(profiles.get(i).getPassword());
                    text1.write("\n");
                    text1.write(profiles.get(i).getFirstName() + "," + profiles.get(i).getMiddleName() + "," + profiles.get(i).getLastName());
                    text1.write("\n");
                    text1.write("HOME:" + profiles.get(i).getHomeAdd());
                    text1.write("\n");
                    text1.write("CITY:" + profiles.get(i).gethCity());
                    text1.write("\n");
                    text1.write("OFFICE:" + profiles.get(i).getWorkAdd());
                    text1.write("\n");
                    text1.write("PHONE:" + profiles.get(i).getPhoneNumber());
                    text1.write("\n");
                    text1.write("EMAIL:" + profiles.get(i).getEmailAdd());
                    text1.write("\n");

                    text1.close();
                } catch (IOException e) {
                    System.out.println("Error occurred in Creating Acct");
                }
                text.close();
            } catch (IOException e) {
                System.out.println("Error occurred in writing masterlist & acct");
            }
        }
    }

    public void printAllInfo() {
        for (int i = 0; i < profiles.size(); i++) {
            System.out.println("First name: " + profiles.get(i).getFirstName());
            System.out.println("Middle name: " + profiles.get(i).getMiddleName());
            System.out.println("Last name: " + profiles.get(i).getLastName());
            System.out.println("Username: " + profiles.get(i).getUsername());
            System.out.println("Password: " + profiles.get(i).getPassword());
            System.out.println("STATUS: " + profiles.get(i).isPositive());
            System.out.print("Role: ");

            if (profiles.get(i) instanceof Government) {
                System.out.println("Official");
            } else if (profiles.get(i) instanceof Tracer) {
                System.out.println("Tracer");
            } else if (profiles.get(i) instanceof Citizen) {
                System.out.println("Citizen");
            }
            System.out.println("-------------------ACCT--------------------------");
        }
    }

    public void printAllVisits() {
        for (int i = 0; i < visits.size(); i++) {
            System.out.println("Username: " + visits.get(i).getUser());
            System.out.println("Visit: " + visits.get(i).getCode());
            System.out.println("Date: " + visits.get(i).getDate());
            System.out.println("Time: " + visits.get(i).getTime());
            System.out.println("-------------------VISIT-------------------------");
        }

    }

    public boolean checkusername(String userinput) {
        for (int i = 0; i < profiles.size(); i++) {
            if (userinput.equals(profiles.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean confirmpass(String user, String pass) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equals(user)) {
                return profiles.get(i).getPassword().equals(pass);
            }
        }
        return false;
    }

    public boolean regusername(String userinput) {
        for (int i = 0; i < profiles.size(); i++) {
            if (userinput.equals(profiles.get(i).getUsername())) {
                return false;
            }
        }

        return true;
    }

    public boolean regpassword(String userinput, String userinput1) {
        return userinput.equals(userinput1);
    }

    public int checkRole(String username) {
        int role = -99;

        for (int i = 0; i < profiles.size(); i++) {
            if (username.equals(profiles.get(i).getUsername())) {

                if (profiles.get(i) instanceof Government) {
                    role = 1;
                    return role;

                } else if (profiles.get(i) instanceof Tracer) {
                    role = 2;
                    return role;
                } else if (profiles.get(i) instanceof Citizen || profiles.get(i) instanceof Profile) {
                    role = 0;
                    return role;
                }
            }
        }
        return role;
    }

    public void newVisit(Visit temp) {
        visits.add(temp);
        updatevisits();
    }

    public void newAcct(Citizen temp) {
        profiles.add(temp);
        updatetext();
    }

    public void newgov(Government temp) {
        for (int i = 0; i < profiles.size(); i++){
            if (temp.getUsername().equals(profiles.get(i).getUsername())){
                temp.setFirstName(profiles.get(i).getFirstName());
                temp.setMiddleName(profiles.get(i).getMiddleName());
                temp.setLastName(profiles.get(i).getLastName());
                temp.setHomeAdd(profiles.get(i).getHomeAdd());
                temp.sethCity(profiles.get(i).gethCity());
                temp.setWorkAdd(profiles.get(i).getWorkAdd());
                temp.setPhoneNumber(profiles.get(i).getPhoneNumber());
                temp.setEmailAdd(profiles.get(i).getEmailAdd());
                temp.setUsername(profiles.get(i).getUsername());
                temp.setPassword(profiles.get(i).getPassword());
                temp.setDateReported(profiles.get(i).getDateReported());
                temp.setNotifyUser(profiles.get(i).getNotifyUser());
                temp.setWarningCode(profiles.get(i).getWarningCode());
                temp.setWarningDate(profiles.get(i).getWarningDate());
                profiles.remove(i);
            }
        }

        profiles.add(temp);
        updatetext();
    }

    public void newtracer(Tracer temp) {
        for (int i = 0; i < profiles.size(); i++){
            if (temp.getUsername().equals(profiles.get(i).getUsername())){
                temp.setFirstName(profiles.get(i).getFirstName());
                temp.setMiddleName(profiles.get(i).getMiddleName());
                temp.setLastName(profiles.get(i).getLastName());
                temp.setHomeAdd(profiles.get(i).getHomeAdd());
                temp.sethCity(profiles.get(i).gethCity());
                temp.setWorkAdd(profiles.get(i).getWorkAdd());
                temp.setPhoneNumber(profiles.get(i).getPhoneNumber());
                temp.setEmailAdd(profiles.get(i).getEmailAdd());
                temp.setUsername(profiles.get(i).getUsername());
                temp.setPassword(profiles.get(i).getPassword());
                temp.setDateReported(profiles.get(i).getDateReported());
                temp.setNotifyUser(profiles.get(i).getNotifyUser());
                temp.setWarningCode(profiles.get(i).getWarningCode());
                temp.setWarningDate(profiles.get(i).getWarningDate());
                profiles.remove(i);
            }
        }

        profiles.add(temp);
        updatetext();
    }

    public void updateacct(String user, String pass, String first, String middle, String last, String home, String work, String phone, String email) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equals(user)) {
                profiles.get(i).setPassword(pass);
                profiles.get(i).setFirstName(first);
                profiles.get(i).setMiddleName(middle);
                profiles.get(i).setLastName(last);
                profiles.get(i).setHomeAdd(home);
                profiles.get(i).setWorkAdd(work);
                profiles.get(i).setPhoneNumber(phone);
                profiles.get(i).setEmailAdd(email);

                updatetext();
                break;
            }
        }
    }

    public boolean getPositive(String user) {
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equals(user)) {
                return profiles.get(i).isPositive();
            }
        }
        return false;
    }

    public void setPositive(String user) {
        increment();

        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equals(user)) {
                profiles.get(i).setPositive();
            }
        }

        for (int i = 0; i < CASES.size(); i++) {
            if (CASES.get(i).getUsername().equals(user)) {
                CASES.get(i).setCasenum(casenum);

            }
        }
    }

    public String getDateReported(String user) {
        String datereported = "empty";
        for (int i = 0; i < CASES.size(); i++) {
            if (CASES.get(i).getUsername().equals(user)) {
                datereported = CASES.get(i).getDateReported();
                return datereported;
            }
        }
        return datereported;
    }

    public int getCaseNum(String user) {

        int cNum = 0;
        for (int i = 0; i < CASES.size(); i++) {
            if (CASES.get(i).getUsername().equals(user)) {
                cNum = CASES.get(i).getCasenum();
                return cNum;
            }
        }
        return cNum;
    }

    public void caseprint() {
        for (int i = 0; i < CASES.size(); i++) {
            System.out.println("Username: " + CASES.get(i).getUsername());
            System.out.println("Case Num: " + CASES.get(i).getCasenum());
            System.out.println("Date: " + CASES.get(i).getDateReported());
            System.out.println("-------------------CASE-------------------------");
        }
    }

    public void newcase(Case temp) {
        CASES.add(temp);
        saveCases();
    }

    public ArrayList<Case> positivefromdaterange(DatePicker start, DatePicker end) {
        ArrayList<Case> cases = new ArrayList<>();
        printAllInfo();
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).isPositive()) {
                if (checkDuration(start, end, profiles.get(i).getDateReported())) {
                    for (int j = 0; j < CASES.size(); j++) {
                        if (profiles.get(i).getUsername().equals(CASES.get(j).getUsername())) {
                            System.out.println("\n\nREACHED HERE\n\n");
                            cases.add(CASES.get(j));
                        }
                    }
                }
            }
        }

        return cases;
    }

    public boolean checkDuration(DatePicker StartDate, DatePicker EndDate, String date) {
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < CASES.size(); i++) {
            if ((date.compareTo(startdate) >= 0) && (date.compareTo(enddate) <= 0)) {
                return true;
            }
        }
        return false;
    }

    public int durAndCity(String City, DatePicker StartDate, DatePicker EndDate) {
        int numCases = 0;
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < CASES.size(); j++) {
                    if (profiles.get(i).getUsername().equalsIgnoreCase(CASES.get(j).getUsername())) {
                        if ((CASES.get(j).getDateReported().compareTo(startdate) >= 0) && (CASES.get(j).getDateReported().compareTo(enddate) <= 0)) {
                            numCases++;
                        }
                    }
                }
            }
        }

        return numCases;
    }

    public int givenDuration(DatePicker StartDate, DatePicker EndDate) {
        int numCases = 0;
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < CASES.size(); i++) {
            if ((CASES.get(i).getDateReported().compareTo(startdate) >= 0) && (CASES.get(i).getDateReported().compareTo(enddate) <= 0)) {
                numCases++;
            }
        }
        return numCases;
    }

    public int CityCases(String City) {
        int numCases = 0;

        for (int i = 0; i < profiles.size(); i++) {

            System.out.println(profiles.get(i).gethCity());

            if (profiles.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < CASES.size(); j++) {

                    if (profiles.get(i).getUsername().equalsIgnoreCase(CASES.get(j).getUsername())) {
                        numCases += 1;

                    }
                }
            }

        }
        return numCases;
    }

    public int removeAccount(String username) {
        int check = 0, role = checkRole(username);
        Citizen temp = new Citizen();

        for (int i = 0; i < profiles.size(); i++){
            if (username.equals(profiles.get(i).getUsername())){
                if (role == 1 || role == 2){
                    temp.setFirstName(profiles.get(i).getFirstName());
                    temp.setMiddleName(profiles.get(i).getMiddleName());
                    temp.setLastName(profiles.get(i).getLastName());
                    temp.setHomeAdd(profiles.get(i).getHomeAdd());
                    temp.sethCity(profiles.get(i).gethCity());
                    temp.setWorkAdd(profiles.get(i).getWorkAdd());
                    temp.setPhoneNumber(profiles.get(i).getPhoneNumber());
                    temp.setEmailAdd(profiles.get(i).getEmailAdd());
                    temp.setUsername(profiles.get(i).getUsername());
                    temp.setPassword(profiles.get(i).getPassword());
                    temp.setDateReported(profiles.get(i).getDateReported());
                    temp.setNotifyUser(profiles.get(i).getNotifyUser());
                    temp.setWarningCode(profiles.get(i).getWarningCode());
                    temp.setWarningDate(profiles.get(i).getWarningDate());

                    profiles.remove(i);
                    profiles.add(temp);
                    return 1;
                }
            }
        }

        return check;
    }

    public void setTraced(String casenum) {
        for (int i = 0; i < CASES.size(); i++) {
            if (casenum.equals(Integer.toString(CASES.get(i).getCasenum()))) {
                CASES.get(i).setStatus("Traced");
            }
        }
    }

    public ArrayList<Case> getCasesAssignedToTracer(String tracerUN) {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < CASES.size(); i++) {
            if (CASES.get(i).getTracerUsername().equals(tracerUN)) {
                if (CASES.get(i).getStatus().equals("NotTraced")) {
                    cases.add(CASES.get(i));
                }
            }
        }

        return cases;
    }

    public ArrayList<Case> unassignedCases() {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < CASES.size(); i++) {
            if (CASES.get(i).getTracerUsername().equals("000")) {
                cases.add(CASES.get(i));
            }
        }

        return cases;
    }

    public int checkNotify(String username) {
        int notify = 0;
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equalsIgnoreCase(username)) {

                if (profiles.get(i).getDateReported() != "Empty") {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
                    String ndate = profiles.get(i).getDateReported();

                    LocalDate localDate = LocalDate.parse(ndate, formatter);

                    if (ChronoUnit.DAYS.between(localDate, LocalDateTime.now()) >= 14) {
                        profiles.get(i).setNotifyUser(2);
                    }
                }

                notify = profiles.get(i).getNotifyUser();
            }
        }
        return notify;
    }

    public ArrayList<Visit> traceUsers(String caseNum, int xNum) {

        Case positiveUser = new Case();

        // Get Reported Case of Positive Person
        for (int i = 0; i < CASES.size(); i++) {
            if (Integer.toString(CASES.get(i).getCasenum()).equals(caseNum)) {
                positiveUser = CASES.get(i);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
        LocalDate reportdate = LocalDate.parse(positiveUser.getDateReported(), formatter);

        ArrayList<Visit> positiveRecords = new ArrayList<>();

        // Store CheckIns of Positive Person that is within xNum Days
        for (int j = 0; j < visits.size(); j++) {
            LocalDate tempdate = LocalDate.parse(visits.get(j).getDate(), formatter);

            if (visits.get(j).getUser().equals(positiveUser.getUsername())) {
                if (reportdate.compareTo(tempdate) <= 0) {
                    positiveRecords.add(visits.get(j));
                }
            }
        }

        ArrayList<Visit> records = new ArrayList<>();

        // Compare All Visit Records to the Visit Records of the Positive
        for (int k = 0; k < visits.size(); k++) {
            if (!visits.get(k).getUser().equals(positiveUser.getUsername())) { // Check if Record Username is not Equal to the Positive User's Record Username
                LocalDate citizenDate = LocalDate.parse(visits.get(k).getDate(), formatter);
                for (int l = 0; l < positiveRecords.size(); l++) {
                    LocalDate positiveDate = LocalDate.parse(positiveRecords.get(l).getDate(), formatter);
                    if (positiveDate.compareTo(citizenDate) == 0) { // Check if Two Record Dates Match
                        if (Integer.parseInt(visits.get(k).getTime()) >= Integer.parseInt(positiveRecords.get(l).getTime())) { // Check if Two Times Overlap
                            if (positiveRecords.get(l).getCode().equals(visits.get(k).getCode())) { // Check if Two Establishment Codes Match
                                records.add(visits.get(k));
                            }
                        }
                    }
                }
            }
        }

        return records;
    }

    public void setNotify(String exposedName, String code, String date) {
        for (int i = 0; i < profiles.size(); i++) {
            if (exposedName.equals(profiles.get(i).getUsername())) {
                profiles.get(i).setNotifyUser(1);
                profiles.get(i).setWarningCode(code);
                profiles.get(i).setWarningDate(date);
            }
        }
    }

    public String getwarningDate(String username) {
        String date = "00/00/0000";
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equalsIgnoreCase(username)) {
                if (profiles.get(i).getWarningDate() != "Empty") {
                    date = profiles.get(i).getWarningDate();
                }

            }
        }
        return date;
    }

    public String getwarningEst(String username) {
        String est = "Empty";
        for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getUsername().equalsIgnoreCase(username)) {
                if (profiles.get(i).getWarningCode() != "Empty") {
                    est = profiles.get(i).getWarningCode();
                }
            }
        }
        return est;
    }

}