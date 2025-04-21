package users;

public abstract class User {
    private String username;
    private String password;
    private String dateofbirth;
    protected String userID;
    public enum UserRole {Admin, Organizer, Attendee;}
    UserRole role;
    public enum Gender {Male, Female;}
    protected Gender gender;

    // Argument Constructor
    public User(String username, String password, String dateofbirth, Gender gender){
        this.username = username;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
    }

    // getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String  getDateOfBirth() { return dateofbirth; }
    public String getUserID(){ return userID; }
    public String getGender(){ return gender.toString();}
    public abstract UserRole getRole();

    // setters
    public void setPassword(String password) { this.password = password; }
    public void setDateofbirth(String dateofbirth) { this.dateofbirth = dateofbirth; }
    public void setUsername(String username) { this.username = username; }
    public abstract void setRole(UserRole role);

    
}
