/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagementsystem;

/**
 *
 * @author b.villarini
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterHotelManager implements HotelManager {
    private ArrayList<HotelStaff> hotelStaffList;
    private ArrayList<Guest> hotelGuestList;
    private int staffLimit;

    public WestminsterHotelManager(int maxMembersNumber) {
        hotelStaffList = new ArrayList<>();
        hotelGuestList = new ArrayList<>();
        staffLimit = maxMembersNumber;
    }

    @Override
    public boolean runMenu() {
        boolean exit = false; // Exit flag
        System.out.println("\n-- HOTEL MANAGEMENT SYSTEM CONSOLE MENU--");
        System.out.println("To save and exit, press 0");
        System.out.println("To Add a new staff member, press 1");
        System.out.println("To Print the list of staff members press 2");
        System.out.println("To Delete a staff member, press 3");
        System.out.println("To Add a guest, press 4");
        System.out.println("To Open GUI, press 5");
        System.out.println("To Sort staff by surname, press 6");

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 0:
                exit = true;
                break;
            case 1:
                this.addHotelStaff();
                break;
            case 2:
                this.printHotelStaffList();
                break;
            case 3:
                this.deleteHotelStaff();
                break;
            case 4:
                this.addGuest();
                break;
            case 5:
                this.runGUI();
                break;
            case 6:
                this.sortStaffBySurname();
                break;
        }
        return exit;
    }

    @Override
    public void addHotelStaff() {
        Scanner s = new Scanner(System.in);
        if (hotelStaffList.size() < staffLimit) {
            System.out.println("Press 1 if you want to add a Manager");
            System.out.println("Press 2 if you want to add a Housekeeper");
            System.out.println("Press 3 if you want to add a Chef");

            int choiceStaff = s.nextInt();
            s.nextLine();

            // Common questions
            System.out.println("Enter the first name");
            String name = s.nextLine();

            System.out.println("Enter the last name");
            String surname = s.nextLine();

            System.out.println("Enter the staff ID");
            String staffID = s.nextLine();

            System.out.println("Enter the date of birth (dd/MM/yyyy format only!)");
            LocalDate date = null;
            String dob = null;
            boolean parsingSucceeds = false;
            while (!parsingSucceeds) {
                dob = s.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    date = LocalDate.parse(dob, formatter);
                    parsingSucceeds = true; // If parsing succeeds, the format is correct
                } catch (DateTimeParseException e) {
                    System.out.println("Enter the correct format. It should be dd/MM/yyyy!");
                    parsingSucceeds = false;
                }
            }


            // Check if the staff is a manager or a guest
            switch (choiceStaff) {
                case 1:
                    // It is a manager
                    System.out.println("Enter the license number");
                    String licenseNum = s.nextLine();

                    // Create a new Manager and add to the list
                    Manager manager = new Manager(name, surname);
                    manager.setLicenseNumber(licenseNum);
                    manager.setDateOfBirth(date);
                    manager.setStaffID(staffID);
                    this.addStaffToList(manager);
                    break;

                case 2:
                    // It is a HouseKeeper
                    System.out.println("Enter the years of experience");
                    int yearsOfExperience = s.nextInt();
                    s.nextLine();
                    
                    
                    HouseKeeper housekeeper = new HouseKeeper(name, surname);
                    housekeeper.setYearsOfExperience(yearsOfExperience);
                    housekeeper.setDateOfBirth(date);
                    housekeeper.setStaffID(staffID);
                    this.addStaffToList(housekeeper);
                    break;
                    
                case 3:
                    // It is a Chef
                    System.out.println("Enter the speciality");
                    String speciality = s.nextLine();

                    System.out.println("Enter the years of experience");
                    int chefExperience = s.nextInt();
                    s.nextLine();

                    Chef chef = new Chef(name, surname);
                    chef.setDateOfBirth(date);
                    chef.setStaffID(staffID);
                    this.addStaffToList(chef);
                    break;
            }
        } else {
            System.out.println("No more space in the system");
        }
    }

    public void addStaffToList(HotelStaff staff) {
        if (this.hotelStaffList.size() < staffLimit) {
            hotelStaffList.add(staff);
        } else {
            System.out.println("No more space in the list");
        }
    }

    @Override
    public void printHotelStaffList() {
        if (!hotelStaffList.isEmpty()) {
            // Sort the list by age (youngest to oldest) using the Comparable implementation
            Collections.sort(hotelStaffList);

            System.out.println("\nStaff List (Sorted by Age - Youngest to Oldest):");
            System.out.println("-----------------------------------------------");

            for (HotelStaff member : hotelStaffList) {
                System.out.println(member.toString());
            }
            System.out.println("\nTotal staff members: " + hotelStaffList.size());
        } else {
            System.out.println("There are no staff members in the system.");
        }
    }

    @Override
    public void deleteHotelStaff() {
        Scanner s = new Scanner(System.in);

        if (hotelStaffList.isEmpty()) {
            System.out.println("There are no staff members to delete.");
            return;
        }

        System.out.println("\n-- DELETE HOTEL STAFF --");
        System.out.println("Enter the staff ID of the staff member you want to delete:");
        String staffID = s.nextLine();

        boolean found = false;
        int indexToRemove = -1;

        for (int i = 0; i < hotelStaffList.size(); i++) {
            if (hotelStaffList.get(i).getStaffID().equals(staffID)) {
                found = true;
                indexToRemove = i;
                break;
            }
        }

        if (found) {
            HotelStaff removedStaff = hotelStaffList.remove(indexToRemove);

            // Display the staffID and surname of the deleted staff member
            System.out.println("\nDeleted Staff Information:");
            System.out.println("Staff ID: " + removedStaff.getStaffID());
            System.out.println("Surname: " + removedStaff.getSurname());

            // Display the role of the deleted staff member
            String role = "Unknown";
            if (removedStaff instanceof Manager) {
                role = "Manager";
            } else if (removedStaff instanceof HouseKeeper) {
                role = "Housekeeper";
            } else if (removedStaff instanceof Chef) {
                role = "Chef";
            }
            System.out.println("Role: " + role);

            // Display how many staff members are currently in the system
            System.out.println("\nStaff members currently in the system: " + hotelStaffList.size());

            System.out.println("\nStaff member deleted successfully.");
        } else {
            System.out.println("No staff member found with ID: " + staffID);
        }
    }
    
     @Override
    public void addGuest() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the first name");
        String name = s.nextLine();

        System.out.println("Enter the last name");
        String surname = s.nextLine();

        System.out.println("Enter the room number");
        int roomNum = s.nextInt();
        s.nextLine();

        System.out.println("Enter the number of nights stayed");
        int nightsStayed = s.nextInt();
         s.nextLine();
         
         Guest guest = new Guest(name, surname);
         guest.setRoomNumber(roomNum);
         guest.setNightsStayed(nightsStayed);
         
         hotelGuestList.add(guest);
    }

    @Override
    public void runGUI() {
        HotelTableGUI table = new HotelTableGUI(hotelStaffList);
        table.setVisible(true);
    }

    private void sortStaffBySurname() {
        Collections.sort(hotelStaffList, new Comparator<HotelStaff>() {
            @Override
            public int compare(HotelStaff staff1, HotelStaff staff2) {
                return staff1.getSurname().compareTo(staff2.getSurname());
            }
        });
        System.out.println("Staff list has been sorted by surname.");
        printHotelStaffList();
    }
}

