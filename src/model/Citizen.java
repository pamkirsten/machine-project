package model;

/**
 * The Citizen Class
 */
public class Citizen extends Account {

    /**
     * Instantiates a new Citizen Class
     *
     * @param firstname  the firstname
     * @param middlename the middlename
     * @param lastname   the lastname
     * @param homeadress the homeadress
     * @param workadress the workadress
     * @param phonenum   the phonenum
     * @param email      the email
     * @param username   the username
     * @param password   the password
     */
    public Citizen(String firstname, String middlename, String lastname, String homeadress, String workadress, String phonenum, String email, String username, String password) {
        super(firstname, middlename, lastname, homeadress, workadress, phonenum, email, username, password);
    }

    /**
     * Instantiates a new Citizen Class
     */
    public Citizen() {

    }
}
