/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerssn5137;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author saisanwariya
 */
public class ReservationQueries {
    private static Connection connection;
    private static ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
    private static ArrayList<String> rooms = new ArrayList<String>();
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement cancelReservation;
    private static PreparedStatement deleteReservation;
    private static PreparedStatement getReservation;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationsByRoom;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement getRoomsAvailableByDate;
    private static PreparedStatement getReservationsByFacultyOnDate;
    private static boolean reservationPossible;
    private static ResultSet resultSet;
    
    public static void addReservationEntry(ReservationEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addReservationEntry = connection.prepareStatement("insert into reservations (faculty,room,date,seats,timestamp) values (?,?,?,?,?)");
            addReservationEntry.setString(1, entry.getFaculty());
            addReservationEntry.setString(2, entry.getRoom());
            addReservationEntry.setDate(3, entry.getDate());
            addReservationEntry.setInt(4, entry.getSeats());
            addReservationEntry.setTimestamp(5, entry.getTimestamp());
            System.out.print(entry.getRoom());
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void cancelReservationEntry(ReservationEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            cancelReservation = connection.prepareStatement("delete from reservations where faculty = (?) AND date = (?)");
            cancelReservation.setString(1, entry.getFaculty());
            cancelReservation.setDate(2, entry.getDate());
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void deleteReservation(ReservationEntry reservation){
        connection = DBConnection.getConnection();
        try
        {
            deleteReservation = connection.prepareStatement("delete from reservations where faculty = (?) AND room = (?) AND date = (?)");
            deleteReservation.setString(1, reservation.getFaculty());
            deleteReservation.setString(2, reservation.getRoom());
            deleteReservation.setDate(3, reservation.getDate());
            deleteReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static ReservationEntry getReservation(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        ReservationEntry reservation = new ReservationEntry("","",date,0);
        try
        {
            getReservation = connection.prepareStatement("select faculty,room,date,seats,timestamp from reservations where date = (?) AND faculty = (?) order by date");
            getReservation.setDate(1,date);
            getReservation.setString(2, faculty);
            resultSet = getReservation.executeQuery();
            
            while(resultSet.next())
            {
                reservation = new ReservationEntry(resultSet.getString("faculty"), resultSet.getString("room"), resultSet.getDate("date"), resultSet.getInt("seats"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservation; 
    }
    public static boolean reservationPossible(String faculty, Date date){
        connection = DBConnection.getConnection();
        boolean reservationPossible = true;
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getReservationsByFacultyOnDate = connection.prepareStatement("select faculty from reservations where faculty = (?) and date = (?)");
            getReservationsByFacultyOnDate.setString(1, faculty);
            getReservationsByFacultyOnDate.setDate(2, date);
            
            resultSet = getReservationsByFacultyOnDate.executeQuery();
            
            while(resultSet.next())
            {   
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        if(rooms.size()> 0){
            reservationPossible = false;
        }
        return reservationPossible;
    }
   
        public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        try
        {
            getReservationsByFaculty = connection.prepareStatement("select faculty,room,date,seats,timestamp from reservations where faculty = (?) order by date");
            getReservationsByFaculty.setString(1, faculty);
            resultSet = getReservationsByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), resultSet.getString("room"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
            System.out.print(reservations.get(0));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservations; 
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        try
        {
            getReservationsByRoom = connection.prepareStatement("select faculty,room,date,seats,timestamp from reservations where room = (?) order by date");
            getReservationsByRoom.setString(1, room);
            resultSet = getReservationsByRoom.executeQuery();
            
            while(resultSet.next())
            {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), resultSet.getString("room"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
            System.out.print(reservations.get(0));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservations; 
    }
    public static ArrayList<String> getRoomsAvailableByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getRoomsAvailableByDate = connection.prepareStatement("select name, seats from rooms where name not in (select room as name from reservations where date = (?)) order by seats desc");
            getRoomsAvailableByDate.setDate(1, date);
            resultSet = getRoomsAvailableByDate.executeQuery();
            
            while(resultSet.next())
            {   
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static ArrayList<String> getRoomsReservedByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getRoomsReservedByDate = connection.prepareStatement("select room from reservations where date = (?)");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(resultSet.getString("room"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms; 
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<ReservationEntry>();
        try
        {
            getReservationsByDate = connection.prepareStatement("select faculty,room,date,seats,timestamp from reservations where date = (?)");
            getReservationsByDate.setDate(1,date);
            resultSet = getReservationsByDate.executeQuery();
            
            while(resultSet.next())
            {
                reservations.add(new ReservationEntry(resultSet.getString("faculty"), resultSet.getString("room"),resultSet.getDate("date"), resultSet.getInt("seats"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservations; 
    }
   
}
