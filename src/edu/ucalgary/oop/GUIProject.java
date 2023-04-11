package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.HashMap;

public class GUIProject extends JFrame implements ActionListener, MouseListener{

    private JTextField treatmentInput;
    private JTextField hourInput;
    private JLabel instructions;
    private JLabel fnLabel;
    private JLabel lnLabel;
    private int inputID;
    private int count;
    private int[] treatmentIDs;
    private Statement update;
    private String sql;
    private int startHour;



    public static void main(String[] args) {
        DatabaseConnection database = new DatabaseConnection();
        database.createConnection();
        EventQueue.invokeLater(() -> {
            new GUIProject().setVisible(true);
        });
    }

    public GUIProject() {
        super("EWR Scheduler");
        JOptionPane.showMessageDialog(this, "A backup volunteer is required, please press OK to call one");
        setupGUI();
        setSize(900, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void setupGUI() {

        instructions = new JLabel("Please enter the treatment ID of the task which start hour you would like to change");

        treatmentInput = new JTextField("Enter the Treatment ID here", 25);

        hourInput = new JTextField("Enter the Start Hour here", 25);

        fnLabel = new JLabel("Treatment ID:");
        lnLabel = new JLabel("Start Hour:");

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());


        headerPanel.add(instructions);
        clientPanel.add(fnLabel);
        clientPanel.add(treatmentInput);
        clientPanel.add(lnLabel);
        clientPanel.add(hourInput);
        submitPanel.add(submitInfo);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);


        treatmentInput.addMouseListener(this);
        hourInput.addMouseListener(this);

    }

    public void mouseClicked(MouseEvent event) {

        if (event.getSource().equals(treatmentInput))
            treatmentInput.setText("");

        if(event.getSource().equals(hourInput))
            hourInput.setText("");
    }


    public void actionPerformed(ActionEvent event) {

        DatabaseConnection database = new DatabaseConnection();
        Connection dbConnect = database.createConnection();

        if (validateInput(database)) {
            // TODO UPDATE THE DATABASE
            try {
                update = dbConnect.createStatement();
                sql = "UPDATE treatments SET startHour = ? WHERE TreatmentID = ?";
                PreparedStatement preparedStatement = dbConnect.prepareStatement(sql);
                preparedStatement.setInt(1, startHour);
                preparedStatement.setInt(2, inputID);
                preparedStatement.executeUpdate();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private boolean validateInput(DatabaseConnection database) {
        //TODO this should fetch all of the treatments from the database and display them to the user
        // then ask them which one they would want to change and then allow them to enter the treatment
        //ID for whichever one the want to change, then it should update the start hour of that one, but
        //TODO validate will check if the ID even exists in the database at all
        boolean allInputValid = false;

        //TODO LOOP THROUGH EACH OF THE TASKID'S IN HASHMAP BY USING THE GET METHOD
        // THEN CHECK WHETHER OR NOT IT IS IN THE TREATMENT ID TABLE AND IF IT IS THEN VALIDATE PASSES


//        HashMap<Integer, Treatments> allTreatments = database.retrieveTreatmentsInfo();
//        System.out.println("Treatments Table: ");
//        System.out.println(allTreatments.toString());
        HashMap<Integer, Treatments> treatmentsList = database.retrieveTreatmentsInfo();
        System.out.println(treatmentsList.get(1));
//        treatmentIDs =
        count = 0;
        for (int i = 0; i <= treatmentsList.size(); i++) {
            count += 1;
        }

        System.out.println(count);
        startHour = Integer.parseInt(hourInput.getText());
        inputID = Integer.parseInt(treatmentInput.getText());
        System.out.println("Starthour: " + startHour);

//TODO CHECK IF USER INPUTS LETTERS

//        for (int i = 0; i < inputID.length(); i++) {
//            if (!Character.isDigit(inputID.charAt(i)))
//                allInputValid = false;
//                JOptionPane.showMessageDialog(this, inputID + " is not a valid treatmentID");
//        }
//TODO NULL CHECK NOT WORKING
//        if (startHour == ""){
//            JOptionPane.showMessageDialog(this, "Please enter the new start hour");
//        }
//
//        else if (inputID == ""){
//            JOptionPane.showMessageDialog(this, "Please enter the new start hour");
//        }
//        else{
            if (inputID > 0 && inputID < count) {
                allInputValid = true;
                JOptionPane.showMessageDialog(this, inputID + " is valid");
            } else {
                JOptionPane.showMessageDialog(this, inputID + " is not a valid treatmentID");
            }
//        }



        return allInputValid;
    }

    public void mouseEntered(MouseEvent event) {

    }

    public void mouseExited(MouseEvent event) {

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }

//    public void actionPerformed(ActionEvent e) {
//
//    }
}







