package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


public class BackupVolunteerGUI extends JFrame implements ActionListener, MouseListener{

    private JLabel errorMessage;
    private JLabel backupRequired;

//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//            new BackupVolunteerGUI().setVisible(true);
//        });
//    }

    public BackupVolunteerGUI() {
        super("Backup Volunteer required");
        setupGUI();
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setupGUI(){
        errorMessage = new JLabel("**************************** ERROR ***************************************");
        backupRequired = new JLabel("Press OK to call for a backup volunteer");
        JButton submitInfo = new JButton("OK");
        JPanel clientPanel = new JPanel();
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        clientPanel.setLayout(new FlowLayout());
        clientPanel.add(errorMessage);
        clientPanel.add(backupRequired);
        submitPanel.add(submitInfo);
        submitInfo.addActionListener(e -> new GUIProject().setVisible(true));

        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);
        submitInfo.addActionListener(this);
//        JOptionPane.showMessageDialog(this, "A backup volunteer is required, please press OK to call one");
    }


    public void actionPerformed(ActionEvent e) {

    }


    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
}
