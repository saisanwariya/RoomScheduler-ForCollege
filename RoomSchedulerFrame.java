package roomschedulerssn5137;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saisanwariya
 */
public class RoomSchedulerFrame extends javax.swing.JFrame
{

    
    /**
     * Creates new form RoomSchedulerFrame
     */
    public RoomSchedulerFrame()
    {
        initComponents();
        
        // Load the combo boxes with data.
        rebuildFacultyComboBoxes();
        rebuildDateComboBoxes();
        rebuildRoomComboBoxes();
        rebuildDateSpinner();
        statusDateUpdate();
    }
    
    public void rebuildFacultyComboBoxes()
    {
        //Uopdate faculty comboboxes with data
        reserveFacultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(Faculty.getAllFaculty().toArray()));
        statusWaitlistFacultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(Faculty.getAllFaculty().toArray()));
        statusFacultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(Faculty.getAllFaculty().toArray()));   
        cancelFacultyComboBox.setModel(new javax.swing.DefaultComboBoxModel(Faculty.getAllFaculty().toArray()));   
    }
    
    public void rebuildRoomComboBoxes()
    {
        //Uopdate room comboboxes with data
        ArrayList<RoomEntry> roomEntries= RoomQueries.getAllPossibleRooms();
        ArrayList<String> roomList = new ArrayList<String>();
        for(RoomEntry room: roomEntries){
            roomList.add(room.getName());
        }
        roomsComboBox.setModel(new javax.swing.DefaultComboBoxModel(roomList.toArray()));  
    }
    public void rebuildDateComboBoxes()
    {
        // Update date comboBoxes with data
        reserveDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(Dates.getAllDates().toArray()));
        statusDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(Dates.getAllDates().toArray()));
        statusWaitlistDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(Dates.getAllDates().toArray()));
        cancelDateComboBox.setModel(new javax.swing.DefaultComboBoxModel(Dates.getAllDates().toArray()));
    }
    public void rebuildDateSpinner(){
        for(int i =1; i<= 31; i++){
            ddCombo.addItem(""+i);
        }
        for(int i = 1; i<= 12;i++){
            mmCombo.addItem(""+i);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = year; i<= year+10; i++){
            yyyyCombo.addItem(""+i);
        }
    }
    public void doReservation(String faculty,Date date, int seats){
        ArrayList<String> possibleRooms = ReservationQueries.getRoomsAvailableByDate(date);
        // Create a RoomEntry object to assign
        if(ReservationQueries.reservationPossible(faculty, date)){
            ArrayList<RoomEntry> allRooms = RoomQueries.getAllPossibleRooms();
            RoomEntry suitableRoom = new RoomEntry();
            for(String room : possibleRooms){
                for(RoomEntry entry: allRooms){
                    if(entry.getName().equals(room) && entry.getSeats()>= seats){
                        suitableRoom = entry;
                        break;
                    }
                }
            }

            // Create a ReservationEntry and Book reservation
            if(suitableRoom.getName() != ""){
                ReservationEntry newReservation = new ReservationEntry(faculty, suitableRoom.getName(),date, seats);
                ReservationQueries.addReservationEntry(newReservation);
                reserveStatusLabel.append("" + faculty + " reserves room " +  suitableRoom.getName() + ".\n");
                addDropStatusLabel.append("" + faculty + " reserves room " +  suitableRoom.getName() + ".\n");
            }
            else{
                WaitlistEntry newWaitlist = new WaitlistEntry(faculty, date,seats);
                WaitlistQueries.addWaitlistEntry(newWaitlist);
                reserveStatusLabel.append("" + faculty + " gets waitlist.\n");
                addDropStatusLabel.append("" + faculty + " gets waitlist.\n");
            }
        }
        else{
            reserveStatusLabel.append("Reservation for "+ faculty +" already exists\n");
        } 
    }
    public void updateWaitlist(Date date){
        ArrayList<WaitlistEntry> waitlist= WaitlistQueries.getWaitlistByDate(date);
        for(WaitlistEntry waitEntry : waitlist){
            String faculty = waitEntry.getFaculty();
            int seats = waitEntry.getSeats();
            ArrayList<String> possibleRooms = ReservationQueries.getRoomsAvailableByDate(date);
            // Create a RoomEntry object to assign
            if(ReservationQueries.reservationPossible(faculty, date)){
                ArrayList<RoomEntry> allRooms = RoomQueries.getAllPossibleRooms();
                RoomEntry suitableRoom = new RoomEntry();
                for(String room : possibleRooms){
                    for(RoomEntry entry: allRooms){
                        if(entry.getName().equals(room) && entry.getSeats()>= seats){
                            suitableRoom = entry;
                            break;
                        }
                    }
                }

                // Create a ReservationEntry and Book reservation
                if(suitableRoom.getName() != ""){
                    ReservationEntry newReservation = new ReservationEntry(faculty, suitableRoom.getName(),date, seats);
                    ReservationQueries.addReservationEntry(newReservation);
                    reserveStatusLabel.append("" + faculty + " reserves room " +  suitableRoom.getName() + " on " + date + ".\n");
                    WaitlistEntry newWaitlist = new WaitlistEntry(faculty,date,seats);
                    WaitlistQueries.deleteWaitlistEntry(newWaitlist);
                    reserveStatusLabel.append("" + faculty + " removed from waitlist.\n");
                }
            }
            else{
                reserveStatusLabel.setText("Reservation for "+ faculty +" already exists");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        waitlistByButtonGroup = new javax.swing.ButtonGroup();
        statusButtonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        waitlistPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addFacultyTextField = new javax.swing.JTextField();
        addFacultyButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addRoomTextField = new javax.swing.JTextField();
        addRoomButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        roomSeatsTextField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        dropRoomButton = new javax.swing.JButton();
        roomsComboBox = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        addDateButton = new javax.swing.JButton();
        ddCombo = new javax.swing.JComboBox<>();
        mmCombo = new javax.swing.JComboBox<>();
        yyyyCombo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        addDropStatusLabel = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        reserveSubmitButton = new javax.swing.JButton();
        reserveSeatsTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reserveDateComboBox = new javax.swing.JComboBox<>();
        reserveFacultyComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cancelDateComboBox = new javax.swing.JComboBox<>();
        cancelFacultyComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        reserveStatusLabel = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        statusDateComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        statusDateTextArea = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        statusFacultyComboBox = new javax.swing.JComboBox<>();
        facultyStatusRadio = new javax.swing.JRadioButton();
        dateStatusRadio = new javax.swing.JRadioButton();
        statusWaitlistButton1 = new javax.swing.JButton();
        statusWaitlistPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        statusWaitlistTextArea = new javax.swing.JTextArea();
        statusWaitlistButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        statusWaitlistDateComboBox = new javax.swing.JComboBox<>();
        allWaitlistRadio = new javax.swing.JRadioButton();
        facultyWaitlistRadio = new javax.swing.JRadioButton();
        dateWaitlistRadio = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        statusWaitlistFacultyComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Room Scheduler");

        waitlistPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                waitlistPanelFocusLost(evt);
            }
        });

        jLabel7.setText("   ");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Faculty"));

        jLabel2.setText("Faculty Name: ");

        addFacultyTextField.setColumns(20);
        addFacultyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacultyTextFieldActionPerformed(evt);
            }
        });

        addFacultyButton.setText("Submit");
        addFacultyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacultyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addFacultyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFacultyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(addFacultyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addFacultyButton)
                .addGap(20, 20, 20))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Room"));

        jLabel3.setText("Room Name: ");

        addRoomTextField.setColumns(20);
        addRoomTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomTextFieldActionPerformed(evt);
            }
        });

        addRoomButton.setText("Submit");
        addRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomButtonActionPerformed(evt);
            }
        });

        jLabel18.setText("Seats:");

        roomSeatsTextField.setColumns(20);
        roomSeatsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomSeatsTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(roomSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(addRoomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addRoomButton)
                .addGap(33, 33, 33))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Drop Room"));
        jPanel7.setPreferredSize(new java.awt.Dimension(168, 125));

        jLabel11.setText("Room Name: ");

        dropRoomButton.setText("Submit");
        dropRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropRoomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dropRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(roomsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dropRoomButton)
                .addGap(33, 33, 33))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Date"));

        jLabel12.setText("Date:");

        addDateButton.setText("Submit");
        addDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDateButtonActionPerformed(evt);
            }
        });

        mmCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmComboActionPerformed(evt);
            }
        });

        jLabel13.setText("-");

        jLabel17.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yyyyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mmCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ddCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(ddCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mmCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yyyyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addDateButton)
                .addGap(33, 33, 33))
        );

        addDropStatusLabel.setEditable(false);
        addDropStatusLabel.setBackground(new java.awt.Color(204, 204, 204));
        addDropStatusLabel.setColumns(20);
        addDropStatusLabel.setRows(5);
        jScrollPane4.setViewportView(addDropStatusLabel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(290, 290, 290))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        waitlistPanel.addTab("Add Drop", jPanel1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("New Reservation"));

        reserveSubmitButton.setText("Submit");
        reserveSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveSubmitButtonActionPerformed(evt);
            }
        });

        reserveSeatsTextField.setColumns(3);

        jLabel6.setText("Seats Required: ");

        jLabel5.setText("Date: ");

        reserveDateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveDateComboBoxActionPerformed(evt);
            }
        });

        reserveFacultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveFacultyComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Faculty: ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(reserveSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(reserveFacultyComboBox, 0, 100, Short.MAX_VALUE)
                                    .addComponent(reserveDateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(reserveSubmitButton)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(reserveFacultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(reserveDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(reserveSeatsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reserveSubmitButton))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancel Reservation"));

        cancelButton.setText("Submit");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel14.setText("Date: ");

        cancelDateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelDateComboBoxActionPerformed(evt);
            }
        });

        cancelFacultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelFacultyComboBoxActionPerformed(evt);
            }
        });

        jLabel15.setText("Faculty: ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cancelFacultyComboBox, 0, 100, Short.MAX_VALUE)
                            .addComponent(cancelDateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cancelFacultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(cancelDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addGap(66, 66, 66))
        );

        reserveStatusLabel.setEditable(false);
        reserveStatusLabel.setBackground(new java.awt.Color(204, 204, 204));
        reserveStatusLabel.setColumns(20);
        reserveStatusLabel.setRows(5);
        jScrollPane3.setViewportView(reserveStatusLabel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        waitlistPanel.addTab("Reservations", jPanel2);

        statusDateComboBox.setToolTipText("");
        statusDateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusDateComboBoxActionPerformed(evt);
            }
        });

        jLabel8.setText("Date: ");

        statusDateTextArea.setEditable(false);
        statusDateTextArea.setBackground(new java.awt.Color(204, 204, 204));
        statusDateTextArea.setColumns(20);
        statusDateTextArea.setRows(5);
        statusDateTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(statusDateTextArea);

        jLabel16.setText("Faculty: ");

        statusFacultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusFacultyComboBoxActionPerformed(evt);
            }
        });

        statusButtonGroup.add(facultyStatusRadio);
        facultyStatusRadio.setText("Status by faculty");

        statusButtonGroup.add(dateStatusRadio);
        dateStatusRadio.setSelected(true);
        dateStatusRadio.setText("Status by Date");

        statusWaitlistButton1.setText("Get Status");
        statusWaitlistButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusWaitlistButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(facultyStatusRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateStatusRadio))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(statusFacultyComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
                                .addComponent(statusDateComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(statusWaitlistButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(statusFacultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultyStatusRadio)
                    .addComponent(dateStatusRadio))
                .addGap(5, 5, 5)
                .addComponent(statusWaitlistButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        waitlistPanel.addTab("Status", jPanel3);

        statusWaitlistTextArea.setEditable(false);
        statusWaitlistTextArea.setBackground(new java.awt.Color(204, 204, 204));
        statusWaitlistTextArea.setColumns(20);
        statusWaitlistTextArea.setRows(5);
        statusWaitlistTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(statusWaitlistTextArea);

        statusWaitlistButton.setText("Get Waitlist");
        statusWaitlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusWaitlistButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Date: ");

        statusWaitlistDateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusWaitlistDateComboBoxActionPerformed(evt);
            }
        });

        waitlistByButtonGroup.add(allWaitlistRadio);
        allWaitlistRadio.setText("All Waitlist");

        waitlistByButtonGroup.add(facultyWaitlistRadio);
        facultyWaitlistRadio.setText("Waitlist by faculty");

        waitlistByButtonGroup.add(dateWaitlistRadio);
        dateWaitlistRadio.setText("Waitlist by Date");

        jLabel10.setText("Faculty: ");

        statusWaitlistFacultyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusWaitlistFacultyComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusWaitlistPanelLayout = new javax.swing.GroupLayout(statusWaitlistPanel);
        statusWaitlistPanel.setLayout(statusWaitlistPanelLayout);
        statusWaitlistPanelLayout.setHorizontalGroup(
            statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusWaitlistPanelLayout.createSequentialGroup()
                .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusWaitlistPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(statusWaitlistPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(statusWaitlistButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(statusWaitlistPanelLayout.createSequentialGroup()
                                .addComponent(allWaitlistRadio)
                                .addGap(56, 56, 56)
                                .addComponent(facultyWaitlistRadio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(dateWaitlistRadio))
                    .addGroup(statusWaitlistPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(46, 46, 46)
                        .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(statusWaitlistFacultyComboBox, 0, 100, Short.MAX_VALUE)
                            .addComponent(statusWaitlistDateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        statusWaitlistPanelLayout.setVerticalGroup(
            statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusWaitlistPanelLayout.createSequentialGroup()
                .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(statusWaitlistFacultyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(statusWaitlistDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusWaitlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allWaitlistRadio)
                    .addComponent(facultyWaitlistRadio)
                    .addComponent(dateWaitlistRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusWaitlistButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        waitlistPanel.addTab("Status Waitlist", statusWaitlistPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(waitlistPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(waitlistPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reserveSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveSubmitButtonActionPerformed
        // Get data from fields
        String faculty = (String) reserveFacultyComboBox.getSelectedItem();
        Date date = (Date) reserveDateComboBox.getSelectedItem();
        int seats = Integer.parseInt(reserveSeatsTextField.getText());
        reserveStatusLabel.setText("");
        doReservation(faculty,date,seats);
    }//GEN-LAST:event_reserveSubmitButtonActionPerformed

    private void reserveDateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveDateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reserveDateComboBoxActionPerformed

    private void reserveFacultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveFacultyComboBoxActionPerformed
        // Fill data into the comboBox
    }//GEN-LAST:event_reserveFacultyComboBoxActionPerformed

    private void statusDateUpdate(){
        //Check status of by date
        Date date = (Date) statusDateComboBox.getSelectedItem();
        ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByDate(date);
        String output = "";
        for(ReservationEntry reservation : reservations){
            output = output + reservation.getFaculty() + " reserved room " +  reservation.getRoom() + " on "+ date  + ".\n";
        }
        if(output.equals("")){
            statusDateTextArea.setText("No reservations.");
        }
        else{
            statusDateTextArea.setText(output);
        }
    }
    
    public String dateString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(date);
        return text;
    }
    private void statusDateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusDateComboBoxActionPerformed
        // 
        statusDateUpdate();
   }//GEN-LAST:event_statusDateComboBoxActionPerformed

    private void waitlistPanelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_waitlistPanelFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_waitlistPanelFocusLost
    private String generateWaitlistOutput(ArrayList<WaitlistEntry> waitlist){
        String output = "Faculty\tDate\tSeats\n";
        for(WaitlistEntry entry : waitlist){
            output = output + entry.getFaculty() +"\t"+ entry.getDate() +"\t"+ entry.getSeats()+"\n";
        }
        return output;
    }
    private void statusWaitlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusWaitlistButtonActionPerformed
        //Status of waitlist
        ArrayList<WaitlistEntry> waitlist;
        if(facultyWaitlistRadio.isSelected()){
            String faculty = statusWaitlistFacultyComboBox.getSelectedItem().toString();
            waitlist = WaitlistQueries.getWaitlistByFaculty(faculty);
        }
        else if(dateWaitlistRadio.isSelected()){
            Date date = Date.valueOf(statusWaitlistDateComboBox.getSelectedItem().toString());
            waitlist = WaitlistQueries.getWaitlistByDate(date);
        }
        else{
            waitlist = WaitlistQueries.getAllWaitlist();
        }
        statusWaitlistTextArea.setText(generateWaitlistOutput(waitlist));
    }//GEN-LAST:event_statusWaitlistButtonActionPerformed

    private void statusWaitlistDateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusWaitlistDateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusWaitlistDateComboBoxActionPerformed

    private void statusWaitlistFacultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusWaitlistFacultyComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusWaitlistFacultyComboBoxActionPerformed

    private void addDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDateButtonActionPerformed
        // TODO add your handling code here:
        String date = (String) yyyyCombo.getSelectedItem() +"-"+ (String) mmCombo.getSelectedItem() +"-"+ (String) ddCombo.getSelectedItem();
        Dates.addDate(date);
        addDropStatusLabel.setText(date + "added.");
        
        rebuildDateComboBoxes();
    }//GEN-LAST:event_addDateButtonActionPerformed

    private void dropRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropRoomButtonActionPerformed
        // TODO add your handling code here:
        String room = (String) roomsComboBox.getSelectedItem();
        ArrayList<ReservationEntry> reservations = ReservationQueries.getReservationsByRoom(room);
        RoomQueries.dropRoom(room);
        addDropStatusLabel.setText(room + " has been dropped from Rooms\n");
        for(ReservationEntry entry:reservations){
            ReservationQueries.deleteReservation(entry);
            addDropStatusLabel.append("Reservation for " + entry.getFaculty() + " on " + dateString(entry.getDate()) + "deleted.\n");
            doReservation(entry.getFaculty(),entry.getDate(),entry.getSeats());
        }
        rebuildRoomComboBoxes();
    }//GEN-LAST:event_dropRoomButtonActionPerformed

    private void addRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomButtonActionPerformed
        // TODO add your handling code here:
        String name = addRoomTextField.getText();
        int seats = Integer.parseInt(roomSeatsTextField.getText());
        RoomEntry room = new RoomEntry(name,seats);
        RoomQueries.addRoom(room);
        addDropStatusLabel.setText(name +" has been added to Rooms.\n");
        
        ArrayList<WaitlistEntry> waitlist = WaitlistQueries.getAllWaitlist();
        for(WaitlistEntry entry : waitlist){
            if(entry.getSeats() <= seats){
                ReservationEntry newReservation = new ReservationEntry(entry.getFaculty(), name ,entry.getDate(), entry.getSeats());
                ReservationQueries.addReservationEntry(newReservation);
                addDropStatusLabel.append("" + entry.getFaculty() + " reserves room " + name + " on "+ entry.getDate() +".\n");
                WaitlistQueries.deleteWaitlistEntry(entry);
                addDropStatusLabel.append("" + entry.getFaculty() + " deleted from  " + name + ".\n");
            }
        }
        rebuildRoomComboBoxes();
    }//GEN-LAST:event_addRoomButtonActionPerformed

    private void addRoomTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addRoomTextFieldActionPerformed

    private void addFacultyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacultyButtonActionPerformed
        // Add the faculty to the faculty table.
        String name = addFacultyTextField.getText();
        Faculty.addFaculty(name);
        addDropStatusLabel.setText(name+" has been added to the Faculty.");

        // rebuild the reservation faculty combo box.
        rebuildFacultyComboBoxes();
    }//GEN-LAST:event_addFacultyButtonActionPerformed

    private void addFacultyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacultyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFacultyTextFieldActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        String name = (String) cancelFacultyComboBox.getSelectedItem();
        Date date = (Date) cancelDateComboBox.getSelectedItem();
        ReservationEntry reservation = ReservationQueries.getReservation(name,date);
        WaitlistEntry waitEntry = WaitlistQueries.getWaitlist(name, date);
        if(reservation.getFaculty().equals("") && !waitEntry.getFaculty().equals("")){
            WaitlistQueries.deleteWaitlistEntry(waitEntry);
            reserveStatusLabel.append("" + waitEntry.getFaculty() + " removed from waitlist.\n");
        }
        else{
            ReservationQueries.cancelReservationEntry(reservation);
            reserveStatusLabel.append("" + reservation.getFaculty() + " on " + reservation.getDate() + " gets cancelled.");
            updateWaitlist(date);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void cancelDateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelDateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelDateComboBoxActionPerformed

    private void cancelFacultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelFacultyComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelFacultyComboBoxActionPerformed

    private void statusFacultyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusFacultyComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusFacultyComboBoxActionPerformed

    private void statusWaitlistButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusWaitlistButton1ActionPerformed
        // TODO add your handling code here:
        if(facultyStatusRadio.isSelected()){
            String faculty = (String) statusFacultyComboBox.getSelectedItem();
            ArrayList<ReservationEntry> reserved = ReservationQueries.getReservationsByFaculty(faculty);
            ArrayList<WaitlistEntry> waited = WaitlistQueries.getWaitlistByFaculty(faculty);
            statusDateTextArea.setText("Status for " + faculty+"\n");
            for(ReservationEntry entry : reserved){
                statusDateTextArea.append("" + faculty + " reserved " + entry.getRoom()+ " on " +  entry.getDate().toString() +"."+"\n");
            }
            /*for(WaitlistEntry entry : waited){
                statusDateTextArea.append("" + faculty + " gets waitlist on" +  entry.getDate().toString() +"."+"\n");
            }*/
        }
        else{
            Date date = (Date)statusDateComboBox.getSelectedItem();
            ArrayList<ReservationEntry> reserved = ReservationQueries.getReservationsByDate(date);
            ArrayList<WaitlistEntry> waited = WaitlistQueries.getWaitlistByDate(date);
            statusDateTextArea.setText("Status for " + date.toString()+"\n");
            for(ReservationEntry entry : reserved){
                statusDateTextArea.append("" + entry.getFaculty() + " reserved " + entry.getRoom()+ " on " +  entry.getDate().toString() +"."+"\n");
            }
            /*for(WaitlistEntry entry : waited){
                statusDateTextArea.append("" + entry.getFaculty() + " gets waitlist on" +  entry.getDate().toString() +"."+"\n");
            }*/
        }
    }//GEN-LAST:event_statusWaitlistButton1ActionPerformed

    private void mmComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mmComboActionPerformed

    private void roomSeatsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomSeatsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomSeatsTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RoomSchedulerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new RoomSchedulerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDateButton;
    private javax.swing.JTextArea addDropStatusLabel;
    private javax.swing.JButton addFacultyButton;
    private javax.swing.JTextField addFacultyTextField;
    private javax.swing.JButton addRoomButton;
    private javax.swing.JTextField addRoomTextField;
    private javax.swing.JRadioButton allWaitlistRadio;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> cancelDateComboBox;
    private javax.swing.JComboBox<String> cancelFacultyComboBox;
    private javax.swing.JRadioButton dateStatusRadio;
    private javax.swing.JRadioButton dateWaitlistRadio;
    private javax.swing.JComboBox<String> ddCombo;
    private javax.swing.JButton dropRoomButton;
    private javax.swing.JRadioButton facultyStatusRadio;
    private javax.swing.JRadioButton facultyWaitlistRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> mmCombo;
    private javax.swing.JComboBox<String> reserveDateComboBox;
    private javax.swing.JComboBox<String> reserveFacultyComboBox;
    private javax.swing.JTextField reserveSeatsTextField;
    private javax.swing.JTextArea reserveStatusLabel;
    private javax.swing.JButton reserveSubmitButton;
    private javax.swing.JTextField roomSeatsTextField;
    private javax.swing.JComboBox<String> roomsComboBox;
    private javax.swing.ButtonGroup statusButtonGroup;
    private javax.swing.JComboBox<String> statusDateComboBox;
    private javax.swing.JTextArea statusDateTextArea;
    private javax.swing.JComboBox<String> statusFacultyComboBox;
    private javax.swing.JButton statusWaitlistButton;
    private javax.swing.JButton statusWaitlistButton1;
    private javax.swing.JComboBox<String> statusWaitlistDateComboBox;
    private javax.swing.JComboBox<String> statusWaitlistFacultyComboBox;
    private javax.swing.JPanel statusWaitlistPanel;
    private javax.swing.JTextArea statusWaitlistTextArea;
    private javax.swing.ButtonGroup waitlistByButtonGroup;
    private javax.swing.JTabbedPane waitlistPanel;
    private javax.swing.JComboBox<String> yyyyCombo;
    // End of variables declaration//GEN-END:variables
}
