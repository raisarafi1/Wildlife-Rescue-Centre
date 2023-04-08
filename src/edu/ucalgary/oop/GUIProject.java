package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIProject extends JFrame implements ActionListener, MouseListener{

    private JTextField fnInput;
    private JTextField lnInput;
    private JLabel instructions;
    private JLabel fnLabel;
    private JLabel lnLabel;

    private String conflict;


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            new GUIProject().setVisible(true);
        });
    }

    public GUIProject(){
        super("EWR Scheduler");
        setupGUI();
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void setupGUI(){

        instructions = new JLabel("Schedule Conflict.");
        fnLabel = new JLabel("L");
        fnInput = new JTextField("Please enter the conflict of the schedule");

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(fnInput);
        submitPanel.add(submitInfo);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);


    }

    public void mouseClicked(MouseEvent event){

        if(event.getSource().equals(fnInput))
            fnInput.setText("");

        if(event.getSource().equals(lnInput))
            lnInput.setText("");
    }


    public void actionPerformed(ActionEvent event){
        conflict = fnInput.getText();
        JOptionPane.showMessageDialog(this, conflict);
    }

    public void mouseEntered(MouseEvent event){

    }

    public void mouseExited(MouseEvent event){

    }

    public void mousePressed(MouseEvent event){

    }

    public void mouseReleased(MouseEvent event){

    }


}





