package managers;

import models.Event;
import data.Database;

public class EventManager implements Manageable<Event> {

    @Override
    public void create(Event event) {
        Database.events.add(event);
        System.out.println("Event created: " + event.getEventName());
    }

    @Override
    public Event read(String ID) {
        for(Event event : Database.events){
            if (event.getEventID().equals(ID)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public void update(String ID, Event updatedObj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
