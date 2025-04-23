package models;

import data.IDGenerator;
import managers.Identifiable;

public class Room implements Identifiable {
    private int RoomNum;
    private String AvailableHrs;
    private String roomID;
    private static int RoomCount = 0;

    
    public Room(int RoomNum, String AvailableHrs){
        this.RoomNum=RoomNum;
        this.AvailableHrs=AvailableHrs;
        generateID();
        RoomCount += 1;
    }
    

    // SETTERS
    public void setRoomNum(int roomNum) {RoomNum = roomNum;}
    public void setAvailableHrs(String availableHrs) {AvailableHrs = availableHrs;}

    // GETTERS
    public static  int getRoomCount() {return RoomCount;}
    public int getRoomNum() {return RoomNum;}
    public String getAvailableHrs() {return AvailableHrs;}
    public String getRoomID() {return roomID;}

    //ID generation
    public void generateID() {this.roomID = IDGenerator.generate(this);}
    public String getIdPrefix() {return "RMM";}

    public String toString(){
            return "Room Number : " + RoomNum + " Available Hours = "+ AvailableHrs;
        }

}
