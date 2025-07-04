/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagementsystem;

/**
 *
 * @author b.villarini
 */
public class Chef extends HotelStaff {
    // Inherited attributes from HotelStaff: name, surname, dateOfBirth, and staffID
    private int yearsOfExperience;
    private String speciality;

    public Chef(String name, String surname) {
        super(name, surname);
    }

    // Setter and Getter methods
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    @Override
    public String toString() {
        return super.toString() + ", Years of Experience: " + yearsOfExperience + ", Speciality: " + speciality;
    }
}
