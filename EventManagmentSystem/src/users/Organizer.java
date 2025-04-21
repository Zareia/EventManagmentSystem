package users;

import data.IDGenerator;
import managers.Identifiable;
import models.Wallet;

public class Organizer extends User implements Identifiable {

    private Wallet wallet;
    // methods to add later (view: available rooms, their events, and their
    // attendees)

    public Organizer(String username, String password, String dateofbirth, Gender gender) {
        super(username, password, dateofbirth, gender);
    }

    // methods like viewAvailableRooms(), getEvents(), getAttendees() later

    @Override
    public String getIdPrefix() {
        return "ORG";
    }

    @Override
    public void generateID() {
        this.userID = IDGenerator.generate(this);
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public void setRole(UserRole role) {
        this.role = role;
    }

}
