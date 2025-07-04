/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagementsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HotelTableGUI extends JFrame {

    JTable myTable;
    HotelTableModel tableModel;
    ArrayList<HotelStaff> list;

    public HotelTableGUI(ArrayList<HotelStaff> list) {
        this.setTitle("Staff in Hotel Management System");
        this.list = list;
        tableModel = new HotelTableModel(list);
        myTable = new JTable(tableModel);


        // Set the size of the frame
        setBounds(20, 20, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Enable sorting
        myTable.setAutoCreateRowSorter(true);

        // Add the table to the panel
        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setPreferredSize(new Dimension(380, 280));

        // Add a button at the bottom
        JButton button = new JButton("Info");

        // Add event handler to the Info button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStaffReportToFile();
            }
        });

        // Add the panel to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }

    /**
     * Saves a formatted report of all hotel staff members to a text file
     */
    private void saveStaffReportToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("HotelStaffReport.txt"))) {
            // Write header for the report
            writer.println("===============================================");
            writer.println("         HOTEL STAFF DETAILED REPORT         ");
            writer.println("===============================================\n");

            // Group staff by role for better organization
            writer.println("MANAGERS:");
            writer.println("-------------------------------------------");
            boolean hasManagers = false;
            for (HotelStaff staff : list) {
                if (staff instanceof Manager) {
                    hasManagers = true;
                    Manager manager = (Manager) staff;
                    writer.println("Staff ID: " + manager.getStaffID());
                    writer.println("Name: " + manager.getName() + " " + manager.getSurname());
                    writer.println("Date of Birth: " + manager.getStringDate());
                    writer.println("License Number: " + manager.getLicenseNumber());
                    writer.println("-------------------------------------------");
                }
            }
            if (!hasManagers) {
                writer.println("No managers currently employed.");
                writer.println("-------------------------------------------");
            }
            writer.println("\nCHEFS:");
            writer.println("-------------------------------------------");
            boolean hasChefs = false;
            for (HotelStaff staff : list) {
                if (staff instanceof Chef) {
                    hasChefs = true;
                    Chef chef = (Chef) staff;
                    writer.println("Staff ID: " + chef.getStaffID());
                    writer.println("Name: " + chef.getName() + " " + chef.getSurname());
                    writer.println("Date of Birth: " + chef.getStringDate());
                    writer.println("Years of Experience: " + chef.getYearsOfExperience());
                    writer.println("Speciality: " + chef.getSpeciality());
                    writer.println("-------------------------------------------");
                }
            }
            if (!hasChefs) {
                writer.println("No chefs currently employed.");
                writer.println("-------------------------------------------");
            }

            writer.println("\nHOUSEKEEPERS:");
            writer.println("-------------------------------------------");
            boolean hasHouseKeepers = false;
            for (HotelStaff staff : list) {
                if (staff instanceof HouseKeeper) {
                    hasHouseKeepers = true;
                    HouseKeeper houseKeeper = (HouseKeeper) staff;
                    writer.println("Staff ID: " + houseKeeper.getStaffID());
                    writer.println("Name: " + houseKeeper.getName() + " " + houseKeeper.getSurname());
                    writer.println("Date of Birth: " + houseKeeper.getStringDate());
                    writer.println("Years of Experience: " + houseKeeper.getYearsOfExperience());
                    writer.println("-------------------------------------------");
                }
            }
            if (!hasHouseKeepers) {
                writer.println("No housekeepers currently employed.");
                writer.println("-------------------------------------------");
            }

            writer.println("\nOTHER STAFF MEMBERS:");
            writer.println("-------------------------------------------");
            boolean hasOtherStaff = false;
            for (HotelStaff staff : list) {
                if (!(staff instanceof Manager) && !(staff instanceof Chef) && !(staff instanceof HouseKeeper)) {
                    hasOtherStaff = true;
                    writer.println("Staff ID: " + staff.getStaffID());
                    writer.println("Name: " + staff.getName() + " " + staff.getSurname());
                    writer.println("Date of Birth: " + staff.getStringDate());
                    writer.println("-------------------------------------------");
                }
            }
            if (!hasOtherStaff) {
                writer.println("No other staff members currently employed.");
                writer.println("-------------------------------------------");
            }

            writer.println("\nTotal number of staff members: " + list.size());
            writer.println("Report generated on: " + java.time.LocalDate.now());

            JOptionPane.showMessageDialog(this, 
                    "Staff report has been successfully saved to HotelStaffReport.txt", 
                    "Report Saved", 
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                    "Error saving staff report: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
