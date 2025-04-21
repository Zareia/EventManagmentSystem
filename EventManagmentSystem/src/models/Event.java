package models;

import data.IDGenerator;
import managers.Identifiable;
import users.Attendee;
import users.Organizer;

public class Event implements Identifiable {
// attributes
    private String EventName;
    private String EventDate;
    private Category category;
    private Room room;
    private Organizer organizer;
    private Attendee attendee;
    private double price;
    private String eventID;
    private  static int eventcount = 0;

    // ARGUMENT CONSTRUCTOR
    public Event(String EventName, String EventDate, Category category, Room room, Organizer organizer, Attendee attendee, double price){
        this.EventName=EventName;
        this.EventDate=EventDate;
        this.category=category;
        this.room=room;
        this.organizer=organizer;
        this.attendee=attendee;
        this.price=price;
        generateID();
        
    }

    public void  Event(){
        Event.eventcount = eventcount + 1;
    }
    
    // SETTERS
    public void setEventName(String eventName) {EventName = eventName;}
    public void setEventDate(String eventDate) {EventDate = eventDate;}
    public void setCategory(Category category) {this.category = category;}
    public void setRoom(Room room) {this.room = room;}
    public void setOrganizer(Organizer organizer) {this.organizer = organizer;}
    public void setAttendee(Attendee attendee) {this.attendee = attendee;}
    public void setPrice(double price) {this.price = price;}

    // GETTERS
    public static int getEventcount() {return eventcount;}
    public String getEventName() {return EventName;}
    public String getEventDate() {return EventDate;}
    public Category getCategory() {return category;}
    public Organizer getOrganizer() {return organizer;}
    public Room getRoom() {return room;}
    public Attendee getAttendee() {return attendee;}
    public double getPrice() {return price;}
    public String getEventID(){return eventID;}

    public String toString(){
        return "Event Info - Event Name : " + EventName + " Event Date: "+  EventDate + " Category: " + category + " Organizer: " + organizer.getUsername() + " Room: " + room + " Attendee: "+ Attendee.getAttendeesCount() + " Price: " + price;

    }

    @Override
    public String getIdPrefix() {
       return "EVT";
    }

    @Override
    public void generateID() {
        this.eventID = IDGenerator.generate(this);
    }
}
