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

/**
 *
 * @author saisanwariya
 */
public class RoomQueries {
    private static Connection connection;
    private static ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
    private static PreparedStatement addRoom;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;
    
    public static void addRoom(RoomEntry room){
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (name,seats) values (?,?)");
            addRoom.setString(1, room.getName());
            addRoom.setInt(2, room.getSeats());
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void dropRoom(String room){
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("delete from rooms where name = (?)");
            dropRoom.setString(1, room);
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<RoomEntry> getAllPossibleRooms(){
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        try
        {
            getAllPossibleRooms = connection.prepareStatement("select name, seats from rooms order by seats");
            resultSet = getAllPossibleRooms.executeQuery();
            
            while(resultSet.next())
            {
                RoomEntry r = new RoomEntry(resultSet.getString("name"), resultSet.getInt("seats"));
                rooms.add(r);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
    }

}
