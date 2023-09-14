/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerssn5137;

/**
 *
 * @author saisanwariya
 */
public class RoomEntry {
    private String room_name;
    private int seats;
    
    public RoomEntry(){
        this.room_name = "";
        this.seats = 0;
    }
    public RoomEntry(String room_name, int seats){
        this.room_name = room_name;
        this.seats = seats;
    }
    
    public String getName(){
        return this.room_name;
    }
    
    public int getSeats(){
        return this.seats;   
    }

}
