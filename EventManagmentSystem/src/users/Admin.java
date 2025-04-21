package users;

import data.Database;
import data.IDGenerator;
import managers.Identifiable;
import models.Event;
import models.Room;

public class Admin extends User implements Identifiable {
    

    // ARGUMENT CONSTRUCTOR
    public Admin(String username, String password, String dateofbirth, Gender gender) {
        super(username, password, dateofbirth, gender);
        
        generateID();
    }

    // SETTERS
    

    // GETTERS


    // DASHBOARD METHODS (still yet to be written ) **
    public void viewallrooms() {
        System.out.println("<<< ALL ROOMS >>>");
        for (int i = 0; i < Room.getRoomCount(); i++) {
            System.out.println(Database.rooms.get(i).getRoomNum());
        }
    }

    public void viewallevents() {
        System.out.println("<<< ALL EVENTS >>>");
        for (int i = 0; i < Event.getEventcount(); i++) {
            System.out.println(Database.events.get(i).getEventName());
        }
    }

    public void viewallattendees() {
        System.out.println("<<< ALL ATTENDEES >>>");
        for (int i = 0; i < Attendee.getAttendeesCount(); i++) {
            System.out.print(Database.attendees.get(i).getUsername());
        }
    }

    @Override
    public String getIdPrefix() {return "ADM";}
    @Override
    public void generateID() {this.userID = IDGenerator.generate(this);}
    @Override
    public void setRole(UserRole role) {this.role = role;}
    @Override
    public UserRole getRole() {return role;}

}
