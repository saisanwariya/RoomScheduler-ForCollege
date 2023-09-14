/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerssn5137;

import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author saisanwariya
 */
public class WaitlistEntry {
    private final String faculty_name;
    private final Date date;
    private final int seats;
    private final Timestamp timestamp;
    
    public WaitlistEntry(String faculty, Date date, int seats){
        this.faculty_name = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
    }
    public WaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp){
        this.faculty_name = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getFaculty(){
        return this.faculty_name;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public int getSeats(){
        return this.seats;
    }
    
    public Timestamp getTimestamp(){
        return this.timestamp;
    }  
}
