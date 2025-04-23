package users;

import data.IDGenerator;
import managers.Identifiable;
import models.Wallet;

public class Organizer extends User implements Identifiable {
    private Wallet wallet;
    
    
    public Organizer(String username, String password, String dateofbirth, Gender gender) {
        super(username, password, dateofbirth, gender);
    }

    //setters
    public UserRole getRole() {return role;}
    public void setRole(UserRole role) {this.role = role;}

    //ID generation
    public String getIdPrefix() {return "ORG";}
    public void generateID() {this.userID = IDGenerator.generate(this);}
}
