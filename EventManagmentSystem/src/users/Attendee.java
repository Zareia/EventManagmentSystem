package users;

import data.IDGenerator;
import managers.Identifiable;
import models.Wallet;

public class Attendee extends User implements Identifiable {
    private String interests;
    private String address;
    private Wallet wallet;
    private static int AttendeesCount = 0;

    
    public Attendee(String username, String password, String dateofbirth, Gender gender, String interests, String address, double balance) {
        super(username, password, dateofbirth, gender);
        this.interests = interests;
        this.address = address;
        this.wallet = new Wallet(balance); 
        generateID();
        AttendeesCount += 1;
    }

    // GETTERS
    public static int getAttendeesCount() {return AttendeesCount;}
    public String getGender() {return gender.toString();}
    public String getInterests() {return interests;}
    public String getAddress() {return address;}
    public Wallet getWallet() {return wallet;}
    public UserRole getRole() {return role;}
    
    
    // SETTERS
    public void setGender(Gender gender) {this.gender = gender;}
    public void setInterests(String interests) {this.interests = interests;}
    public void setAddress(String address) {this.address = address;}
    public void setRole(UserRole role) {this.role = role;}
    

    // ID generation
    public String getIdPrefix() {return "ATT";}
    public void generateID() {this.userID = IDGenerator.generate(this);}
}
