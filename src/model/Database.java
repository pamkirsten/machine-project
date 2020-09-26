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

    private static ArrayList<Profile> db = new ArrayList<>();
    private static ArrayList<Visit> dbv = new ArrayList<>();
    private static ArrayList<Government> dgov = new ArrayList<>();
    private static ArrayList<Case> dcase = new ArrayList<>();
    private static ArrayList<Citizen> dcitizen = new ArrayList<>();
    private static ArrayList<Tracer> dtracer = new ArrayList<>();

    private Citizen newuser = new Citizen();

    private static int casenum = dcase.size();

    public void increment() {
        casenum += 1;
    }

    public void bootup() {
        System.out.println("@@@---BOOTING UP---@@@");
        opentextfile();
        openvisitfile();
        opencases();
        infoprint();
        visitprint();
        caseprint();
        System.out.println("@@@---END---@@@");
    }

    public void shutdown() {
        System.out.println("@@@---SHUTTING DOWN---@@@");
        updatetext();
        updatevisitfile();
        //updatecasefile();
        infoprint();
        visitprint();
        caseprint();
        System.out.println("@@@---END---@@@");
    }

    public void openvisitfile() {
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
                    dbv.add(visit);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error occurred in reading visit");
        }

    }

    public void sortvisitdb() {
        ArrayList<String> users = new ArrayList<>();
        ArrayList<Visit> dbvtemp = new ArrayList<>();

        for (int i = 0; i < dbv.size(); i++) {
            if (i == 0) {
                users.add(dbv.get(i).getUser());
            }
            for (int x = 0; x < users.size(); x++) {
                if (dbv.get(i).getUser().equals(users.get(x))) {
                    break;
                }
                if (users.size() == x + 1) {
                    users.add(dbv.get(i).getUser());
                }
            }
        }

        for (int z = 0; z < users.size(); z++) {
            for (int y = 0; y < dbv.size(); y++) {
                if (users.get(z).equals(dbv.get(y).getUser())) {
                    dbvtemp.add(dbv.get(y));
                }
            }
        }

        dbv = dbvtemp;

        System.out.println(Arrays.toString(users.toArray()));
    }

    public void updatevisitfile() {
        sortvisitdb();

        try {
            FileWriter text = new FileWriter("estvisitrec.txt");
        } catch (IOException e) {
            System.out.println("Error occurred in opening VISIT");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < dbv.size(); i++) {
            if (i == 0) {
                try {
                    FileWriter text = new FileWriter("estvisitrec.txt", true);

                    text.write(dbv.get(i).getUser());
                    text.write("\n");

                    text.close();
                } catch (IOException e) {
                    System.out.println("Error occurred in writing Visit");
                }
            }

            if (i > 0) {
                temp = dbv.get(i).getUser();
                temp1 = dbv.get(i - 1).getUser();

                if (!(temp.equals(temp1))) {
                    try {
                        FileWriter text = new FileWriter("estvisitrec.txt", true);

                        text.write("\n");
                        text.write(dbv.get(i).getUser());
                        text.write("\n");

                        text.close();
                    } catch (IOException e) {
                        System.out.println("Error occurred in writing Visit");
                    }
                }
            }

            try {
                FileWriter text = new FileWriter("estvisitrec.txt", true);

                text.write(dbv.get(i).getCode());
                text.write(" ");
                text.write(dbv.get(i).getDate());
                text.write(" ");
                text.write(dbv.get(i).getTime());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error occurred in writing Visit");
            }
        }
    }

    public void opencases() {
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

                for (int i = 0; i < db.size(); i++) {
                    if (db.get(i).getUsername().equals(cases.getUsername())) {
                        db.get(i).setPositive();
                        db.get(i).setDateReported(temparray[2]);
                    }
                }

                dcase.add(cases);
                casenum++;
            }
        } catch (IOException e) {
            System.out.println("Error occurred in writing positivecases");
        }
    }

    public void savecases() {
        try {
            FileWriter text = new FileWriter("positivecases.txt");
        } catch (IOException e) {
            System.out.println("Error occurred in opening positivecases");
        }

        String temp = "", temp1 = "";

        for (int i = 0; i < dcase.size(); i++) {
            try {
                FileWriter text = new FileWriter("positivecases.txt", true);

                text.write(String.valueOf(dcase.get(i).getCasenum()));
                text.write(" ");
                text.write(dcase.get(i).getUsername());
                text.write(" ");
                text.write(dcase.get(i).getDateReported());
                text.write(" ");
                text.write(dcase.get(i).getTracerUsername());
                text.write(" ");
                text.write(dcase.get(i).getStatus());
                text.write("\n");

                text.close();
            } catch (IOException e) {
                System.out.println("Error occurred in writing positivecases.txt");
            }
        }
    }

    public void opentextfile() {
        try {
            FileWriter text = new FileWriter("masterlist.txt", true);
        } catch (IOException e) {
            System.out.println("Error occurred in opening MASTERLIST");
        }

        String tmpstring, user, line;
        String[] account;

        try {
            Scanner readtxt = new Scanner(new File("masterlist.txt"));

            while (readtxt.hasNextLine()) {
                tmpstring = readtxt.nextLine();
                account = tmpstring.trim().split("\\s+");

                if (account[1].equals("citizen")) {
                    Citizen temp = new Citizen();

                    temp.setUsername(account[0]);

                    user = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(user));

                    line = readuser.nextLine();
                    temp.setPassword(line);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(",");
                    temp.setFirstname(account[0]);
                    temp.setMiddlename(account[1]);
                    temp.setLastname(account[2]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp.setHomeadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp.sethCity(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp.setWorkadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp.setPhonenum(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp.setEmail(account[1]);

                    db.add(temp);
                } else if (account[1].equals("official")) {
                    Government temp1 = new Government();

                    temp1.setUsername(account[0]);

                    user = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(user));

                    line = readuser.nextLine();
                    temp1.setPassword(line);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(",");
                    temp1.setFirstname(account[0]);
                    temp1.setMiddlename(account[1]);
                    temp1.setLastname(account[2]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp1.setHomeadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp1.setWorkadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp1.setPhonenum(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp1.setEmail(account[1]);

                    db.add(temp1);
                } else if (account[1].equals("tracer")) {
                    Tracer temp2 = new Tracer();

                    temp2.setUsername(account[0]);

                    user = "accounts/" + account[0] + ".act";
                    Scanner readuser = new Scanner(new File(user));

                    line = readuser.nextLine();
                    temp2.setPassword(line);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(",");
                    temp2.setFirstname(account[0]);
                    temp2.setMiddlename(account[1]);
                    temp2.setLastname(account[2]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp2.setHomeadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp2.setWorkadress(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp2.setPhonenum(account[1]);

                    tmpstring = readuser.nextLine();
                    account = tmpstring.split(":");
                    temp2.setEmail(account[1]);

                    db.add(temp2);
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

        for (int i = 0; i < db.size(); i++) {
            try {
                FileWriter text = new FileWriter("masterlist.txt", true);

                text.write(db.get(i).getUsername());
                text.write(" ");


                if (db.get(i) instanceof Government) {
                    text.write("official");
                    text.write("\n");
                } else if (db.get(i) instanceof Tracer) {
                    text.write("tracer");
                    text.write("\n");
                } else if (db.get(i) instanceof Citizen) {
                    text.write("citizen");
                    text.write("\n");
                }

                try {
                    String user = "accounts/" + db.get(i).getUsername() + ".act";
                    FileWriter text1 = new FileWriter(user);

                    text1.write(db.get(i).getPassword());
                    text1.write("\n");
                    text1.write(db.get(i).getFirstname() + "," + db.get(i).getMiddlename() + "," + db.get(i).getLastname());
                    text1.write("\n");
                    text1.write("HOME:" + db.get(i).getHomeadress());
                    text1.write("\n");
                    text1.write("CITY:" + db.get(i).gethCity());
                    text1.write("\n");
                    text1.write("OFFICE:" + db.get(i).getWorkadress());
                    text1.write("\n");
                    text1.write("PHONE:" + db.get(i).getPhonenum());
                    text1.write("\n");
                    text1.write("EMAIL:" + db.get(i).getEmail());
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

    public void infoprint() {
        for (int i = 0; i < db.size(); i++) {
            System.out.println("First name: " + db.get(i).getFirstname());
            System.out.println("Middle name: " + db.get(i).getMiddlename());
            System.out.println("Last name: " + db.get(i).getLastname());
            System.out.println("Username: " + db.get(i).getUsername());
            System.out.println("Password: " + db.get(i).getPassword());
            System.out.println("STATUS: " + db.get(i).isPositive());
            System.out.print("Role: ");

            if (db.get(i) instanceof Government) {
                System.out.println("Official");
            } else if (db.get(i) instanceof Tracer) {
                System.out.println("Tracer");
            } else if (db.get(i) instanceof Citizen) {
                System.out.println("Citizen");
            }
            System.out.println("-------------------ACCT--------------------------");
        }
    }

    public void visitprint() {
        for (int i = 0; i < dbv.size(); i++) {
            System.out.println("Username: " + dbv.get(i).getUser());
            System.out.println("Visit: " + dbv.get(i).getCode());
            System.out.println("Date: " + dbv.get(i).getDate());
            System.out.println("Time: " + dbv.get(i).getTime());
            System.out.println("-------------------VISIT-------------------------");
        }

    }

    public boolean checkusername(String userinput) {
        for (int i = 0; i < db.size(); i++) {
            if (userinput.equals(db.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean confirmpass(String user, String pass) {
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUsername().equals(user)) {
                if (db.get(i).getPassword().equals(pass)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean regusername(String userinput) {
        for (int i = 0; i < db.size(); i++) {
            if (userinput.equals(db.get(i).getUsername())) {
                return false;
            }
        }

        return true;
    }

    public boolean regpassword(String userinput, String userinput1) {
        if (userinput.equals(userinput1)) {
            return true;
        }
        return false;
    }

    public int checkRole(String username) {

        int role = -99;

        for (int i = 0; i < db.size(); i++) {
            if (username.equals(db.get(i).getUsername())) {

                if (db.get(i) instanceof Government) {
                    role = 1;
                    return role;

                } else if (db.get(i) instanceof Tracer) {
                    role = 2;
                    return role;
                } else if (db.get(i) instanceof Citizen || db.get(i) instanceof Profile) {
                    role = 0;
                    return role;
                }
            }
        }
        return role;
    }

    public void newvisit(Visit temp) {
        dbv.add(temp);
        updatevisitfile();
    }

    public void newacct(Citizen temp) {
        //Citizen temp = new Citizen("Fonzy", "Gay", "Lima", "Home", "Work", "123456", "fonzygay@gmail.com", "fonzyugly", "pretty");
        db.add(temp);
        updatetext();

    }

    public void updateacct(String user, String pass, String first, String middle, String last, String home, String work, String phone, String email) {
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUsername().equals(user)) {
                db.get(i).setPassword(pass);
                db.get(i).setFirstname(first);
                db.get(i).setMiddlename(middle);
                db.get(i).setLastname(last);
                db.get(i).setHomeadress(home);
                db.get(i).setWorkadress(work);
                db.get(i).setPhonenum(phone);
                db.get(i).setEmail(email);

                updatetext();
                break;
            }
        }
    }

    public boolean getPositive(String user) {
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUsername().equals(user)) {
                return db.get(i).isPositive();
            }
        }
        return false;
    }

    public void setPositive(String user) {
        increment();

        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUsername().equals(user)) {
                db.get(i).setPositive();
            }
        }

        for (int i = 0; i < dcase.size(); i++) {
            if (dcase.get(i).getUsername().equals(user)) {
                dcase.get(i).setCasenum(casenum);

            }
        }
    }

    public String getDateReported(String user) {
        String datereported = "empty";
        for (int i = 0; i < dcase.size(); i++) {
            if (dcase.get(i).getUsername().equals(user)) {
                datereported = dcase.get(i).getDateReported();
                return datereported;
            }
        }
        return datereported;
    }

    public int getCaseNum(String user) {

        int cNum = 0;
        for (int i = 0; i < dcase.size(); i++) {
            if (dcase.get(i).getUsername().equals(user)) {
                cNum = dcase.get(i).getCasenum();
                return cNum;
            }
        }
        return cNum;
    }

    public void caseprint() {
        for (int i = 0; i < dcase.size(); i++) {
            System.out.println("Username: " + dcase.get(i).getUsername());
            System.out.println("Case Num: " + dcase.get(i).getCasenum());
            System.out.println("Date: " + dcase.get(i).getDateReported());
            System.out.println("-------------------CASE-------------------------");
        }
    }

    public void newcase(Case temp) {
        dcase.add(temp);
        savecases();
    }

    public ArrayList<Case> positivefromdaterange(DatePicker start, DatePicker end) {
        ArrayList<Case> cases = new ArrayList<>();
        infoprint();
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).isPositive()) {
                if (checkDuration(start, end, db.get(i).getDateReported())) {
                    for (int j = 0; j < dcase.size(); j++) {
                        if (db.get(i).getUsername().equals(dcase.get(j).getUsername())) {
                            System.out.println("\n\nREACHED HERE\n\n");
                            cases.add(dcase.get(j));
                        }
                    }
                }
            }
        }

        return cases;
    }

    public void newgov(Government temp) {
        dgov.add(temp);
        //updatevisitfile(); //put update casefile function here
    }

    public boolean checkDuration(DatePicker StartDate, DatePicker EndDate, String date) {
        String startdate = StartDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));
        String enddate = EndDate.getValue().format(DateTimeFormatter.ofPattern("MM,dd,YYYY"));

        for (int i = 0; i < dcase.size(); i++) {
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

        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < dcase.size(); j++) {
                    if (db.get(i).getUsername().equalsIgnoreCase(dcase.get(j).getUsername())) {
                        if ((dcase.get(j).getDateReported().compareTo(startdate) >= 0) && (dcase.get(j).getDateReported().compareTo(enddate) <= 0)) {
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

        for (int i = 0; i < dcase.size(); i++) {
            if ((dcase.get(i).getDateReported().compareTo(startdate) >= 0) && (dcase.get(i).getDateReported().compareTo(enddate) <= 0)) {
                numCases++;
            }
        }
        return numCases;
    }

    public int CityCases(String City) {
        int numCases = 0;

        for (int i = 0; i < db.size(); i++) {

            System.out.println(db.get(i).gethCity());

            if (db.get(i).gethCity().equalsIgnoreCase(City)) {
                for (int j = 0; j < dcase.size(); j++) {

                    if (db.get(i).getUsername().equalsIgnoreCase(dcase.get(j).getUsername())) {
                        numCases += 1;

                    }
                }
            }

        }
        return numCases;
    }

    public int removeAccount(String name) {
        int check = 0;

        for (int i = 0; i < db.size(); i++) {
            if (checkRole(name) == 1) {

                newuser.setFirstname(db.get(i).getFirstname());
                newuser.setUsername(db.get(i).getUsername());
                newuser.setPassword(db.get(i).getPassword());
                newuser.setMiddlename(db.get(i).getMiddlename());
                newuser.setLastname(db.get(i).getLastname());
                newuser.setHomeadress(db.get(i).getHomeadress());
                newuser.setWorkadress(db.get(i).getWorkadress());
                newuser.setPhonenum(db.get(i).getPhonenum());
                newuser.setEmail(db.get(i).getEmail());
                newacct(newuser);
                db.remove(i);
                check = 1;
            }
        }
        for (int i = 0; i < db.size(); i++) {
            if (checkRole(name) == 2) {
                newuser.setFirstname(db.get(i).getFirstname());
                newuser.setUsername(db.get(i).getUsername());
                newuser.setPassword(db.get(i).getPassword());
                newuser.setMiddlename(db.get(i).getMiddlename());
                newuser.setLastname(db.get(i).getLastname());
                newuser.setHomeadress(db.get(i).getHomeadress());
                newuser.setWorkadress(db.get(i).getWorkadress());
                newuser.setPhonenum(db.get(i).getPhonenum());
                newuser.setEmail(db.get(i).getEmail());
                newacct(newuser);
                db.remove(i);
                check = 1;
            }
        }

        return check;

        //turns into citizen first

        //update file

    }

    public void setTraced(String casenum){
        for (int i = 0; i < dcase.size(); i++){
            if (casenum.equals(Integer.toString(dcase.get(i).getCasenum()))){
                dcase.get(i).setStatus("Traced");
            }
        }
    }

    public ArrayList<Case> getCasesAssignedToTracer(String tracerUN) {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < dcase.size(); i++){
            if (dcase.get(i).getTracerUsername().equals(tracerUN)){
                if (dcase.get(i).getStatus().equals("NotTraced")){
                    cases.add(dcase.get(i));
                }
            }
        }

        return cases;
    }

    public ArrayList<Case> unassignedCases() {
        ArrayList<Case> cases = new ArrayList<>();

        for (int i = 0; i < dcase.size(); i++){
            if (dcase.get(i).getTracerUsername().equals("000")){
                    cases.add(dcase.get(i));
            }
        }

        return cases;
    }

    public int checkNotify(String username) {
        int notify = 0;
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getUsername().equalsIgnoreCase(username)) {

                if (db.get(i).getDateReported() != "Empty") {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
                    String ndate = db.get(i).getDateReported();

                    LocalDate localDate = LocalDate.parse(ndate, formatter);

                    if (ChronoUnit.DAYS.between(localDate, LocalDateTime.now()) >= 14) {
                        db.get(i).setNotifyUser(2);
                    }
                }

                notify = db.get(i).getNotifyUser();
            }
        }
        return notify;
    }

    public ArrayList<Visit> traceUsers (String caseNum, int xNum) {

        Case positiveUser = new Case();

        // Get Reported Case of Positive Person
        for (int i = 0; i < dcase.size(); i++){
            if (Integer.toString(dcase.get(i).getCasenum()).equals(caseNum)){
                positiveUser = dcase.get(i);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM,dd,yyyy");
        LocalDate reportdate = LocalDate.parse(positiveUser.getDateReported(), formatter);

        ArrayList<Visit> positiveRecords = new ArrayList<>();

        // Store CheckIns of Positive Person that is within xNum Days
        for (int j = 0; j < dbv.size(); j++){
            LocalDate tempdate = LocalDate.parse(dbv.get(j).getDate(), formatter);

            if (dbv.get(j).getUser().equals(positiveUser.getUsername())){
                if (reportdate.compareTo(tempdate) <= 0){
                    positiveRecords.add(dbv.get(j));
                }
            }
        }

        ArrayList<Visit> records = new ArrayList<>();

        // Compare All Visit Records to the Visit Records of the Positive
        for (int k = 0; k < dbv.size(); k++){
            if (!dbv.get(k).getUser().equals(positiveUser.getUsername())){ // Check if Record Username is not Equal to the Positive User's Record Username
                LocalDate citizenDate = LocalDate.parse(dbv.get(k).getDate() ,formatter);
                for (int l = 0; l < positiveRecords.size(); l++){
                    LocalDate positiveDate = LocalDate.parse(positiveRecords.get(l).getDate(), formatter);
                    if (positiveDate.compareTo(citizenDate) == 0){ // Check if Two Record Dates Match
                        if (Integer.parseInt(dbv.get(k).getTime()) >= Integer.parseInt(positiveRecords.get(l).getTime())){ // Check if Two Times Overlap
                            if (positiveRecords.get(l).getCode().equals(dbv.get(k).getCode())){ // Check if Two Establishment Codes Match
                                records.add(dbv.get(k));
                            }
                        }
                    }
                }
            }
        }

        return records;
    }
}