package com.mycompany.hotelmanagementsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit tests for the Manager class
 */
public class ManagerTest {

    private Manager manager;
    private final String TEST_NAME = "John";
    private final String TEST_SURNAME = "Smith";
    private final String TEST_LICENSE = "LIC123456";

    @BeforeEach
    public void setUp() {
        // Initialize a fresh Manager instance before each test
        manager = new Manager(TEST_NAME, TEST_SURNAME);
    }

    /**
     * Test that the constructor correctly initializes name and surname
     */
    @Test
    public void testConstructor() {
        // Verify that name and surname are correctly set by the constructor
        Assertions.assertEquals(TEST_NAME, manager.getName(), "Constructor should set the name correctly");
        Assertions.assertEquals(TEST_SURNAME, manager.getSurname(), "Constructor should set the surname correctly");

        // Verify that licenseNumber is null when not set
        Assertions.assertNull(manager.getLicenseNumber(), "License number should be null by default");
    }

    /**
     * Test the setter and getter for licenseNumber
     */
    @Test
    public void testSetAndGetLicenseNumber() {
        // Initially the license number should be null
        Assertions.assertNull(manager.getLicenseNumber(), "License number should start as null");

        // Set a license number
        manager.setLicenseNumber(TEST_LICENSE);

        // Verify that the license number was correctly set
        Assertions.assertEquals(TEST_LICENSE, manager.getLicenseNumber(), "License number should match what was set");

        // Set a different license number to verify it updates
        String newLicense = "NEW-LICENSE-789";
        manager.setLicenseNumber(newLicense);
        Assertions.assertEquals(newLicense, manager.getLicenseNumber(), "License number should update when changed");

        // Set to null to verify it handles null values
        manager.setLicenseNumber(null);
        Assertions.assertNull(manager.getLicenseNumber(), "License number should be able to be set to null");
    }
}
