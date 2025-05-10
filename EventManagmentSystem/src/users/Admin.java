package users;


import data.IDGenerator;
import managers.Identifiable;



public class Admin extends User implements Identifiable {
    
    
    public Admin(String username, String password, String dateofbirth, Gender gender) {
        super(username, password, dateofbirth, gender);
        generateID();
    }

    public void setRole(UserRole role) {this.role = role;}
    public UserRole getRole() {return role;}
    public String getID(){return this.userID;}
    
    // ID generation
    public String getIdPrefix() {return "ADM";}
    public void generateID() {this.userID = IDGenerator.generate(this);}
}
