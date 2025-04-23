package users;


import data.IDGenerator;
import managers.Identifiable;



public class Admin extends User implements Identifiable {
    private static int adminCount = 0;

    
    public Admin(String username, String password, String dateofbirth, Gender gender) {
        super(username, password, dateofbirth, gender);
        adminCount += 1;
        generateID();
    }

    public void setRole(UserRole role) {this.role = role;}
    public UserRole getRole() {return role;}
    
    // ID generation
    public String getIdPrefix() {return "ADM";}
    public void generateID() {this.userID = IDGenerator.generate(this);}
}
