/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerssn5137;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author saisanwariya
 */
public class WaitlistQueries {
        private static Connection connection;
    private static ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement cancelWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static ResultSet resultSet;
    
    public static void addWaitlistEntry(WaitlistEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist (faculty,date,seats,timestamp) values (?,?,?,?)");
            addWaitlistEntry.setString(1, entry.getFaculty());
            addWaitlistEntry.setDate(2, entry.getDate());
            addWaitlistEntry.setInt(3, entry.getSeats());
            addWaitlistEntry.setTimestamp(4, entry.getTimestamp());
            addWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static WaitlistEntry getWaitlist(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        WaitlistEntry reservation = new WaitlistEntry("",date,0);
        try
        {
            getWaitlist = connection.prepareStatement("select faculty,date,seats,timestamp from waitlist where date = (?) AND faculty = (?) order by timestamp");
            getWaitlist.setDate(1,date);
            getWaitlist.setString(2, faculty);
            resultSet = getWaitlist.executeQuery();
            
            while(resultSet.next())
            {
                reservation = new WaitlistEntry(resultSet.getString("faculty"), resultSet.getDate("date"), resultSet.getInt("seats"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation; 
    }
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByDate = connection.prepareStatement("select faculty,date,seats,timestamp from waitlist where date = (?) order by timestamp");
            getWaitlistByDate.setDate(1,date);
            resultSet = getWaitlistByDate.executeQuery();
            
            while(resultSet.next())
            {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist; 
    }
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty)
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select faculty,date,seats,timestamp from waitlist where faculty = (?) order by timestamp");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist; 
    }
    public static ArrayList<WaitlistEntry> getAllWaitlist()
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select faculty,date,seats,timestamp from waitlist order by timestamp desc");
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {

                waitlist.add(new WaitlistEntry(resultSet.getString("faculty"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist; 
    }
    public static void cancelWaitlistEntry(String faculty, Date date){
        connection = DBConnection.getConnection();
        try
        {
            cancelWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = (?) AND date = (?)");
            cancelWaitlistEntry.setString(1, faculty);
            cancelWaitlistEntry.setDate(2, date);
            cancelWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void deleteWaitlistEntry(WaitlistEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            deleteWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = (?) AND date = (?)");
            deleteWaitlistEntry.setString(1, entry.getFaculty());
            deleteWaitlistEntry.setDate(2, entry.getDate());
            deleteWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}
