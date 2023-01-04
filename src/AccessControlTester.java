//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Access Control System
// Course: CS 300 Spring 2022
//
// Author: Naman Parekh
// Email: ncparekh@wisc.edu
// Lecturer: Hobbes Legault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class deals with the Access Control testers
 */

public class AccessControlTester {

  /**
   * The main method that runs all the other tests using runAllTests()
   * 
   * @param args - input arguments if there are any
   */
  
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

  /**
   * Runs all the methods in this class
   *
   * @return true if all the tests pass and the code compiles
   */
  
  public static boolean runAllTests() {
    
	if (!(testAddRemoveUserWithAdminPowers() && testAccessControlIsValidLoginNotValidUser()
        && testAddUserWithNoAdminPowers() && testUserConstructorAndMethods())) {
      return false;
    }

    return true;
  }

  /** 
   * Tester method for the user constructor method 
   *
   * @return true if all the tests pass
   */
  
  public static boolean testUserConstructorAndMethods() {
    
	User naman = new User("naman", "password", false);
    
    if (!(naman.getUsername().equals("naman") && naman.getIsAdmin() == false
        && naman.isValidLogin("password") == true)) {
      
      return false;
    }

    // Checks if the set password method is working fine.
    
    naman.setPassword("himynameisNaman");
    if (!(naman.isValidLogin("himynameisNaman"))) {
      
      return false;
    }

    // Checks if setIsAdmin is working fine.
    
    naman.setIsAdmin(true);
    if (!(naman.getIsAdmin() == true)) {
      
      return false;
    }

    return true;
  }

  /**
   * Tester method for the AccessControl methods
   *
   * @return true if all tests pass
   */
  
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    
	AccessControl terminal = new AccessControl();
    
	terminal.setCurrentUser("Admin");
    terminal.addUser("monkey");
    terminal.addUser("Ark");
    terminal.addUser("baby hippo");

    terminal.setCurrentUser("Ark");
    terminal.changePassword("password");
    
    // Checks if it returns when passed in correct values
    
    if (AccessControl.isValidLogin("Ark", "password") != true) {
      System.out.println("ERROR: isValidLogin failed to return the correct value!");
      return false;
    }

    // Checks if it returns when passed in incorrect values
    
    if (AccessControl.isValidLogin("lol", "password") != false) {
      System.out.println("ERROR: isValidLogin failed to return the correct value!");
      return false;
    }

    // Checks if it returns when passed in null values
    
    if (AccessControl.isValidLogin(null, null) != false) {
      System.out.println("ERROR: isValidLogin failed to return the correct value!");
      return false;
    }

    terminal.setCurrentUser("baby hippo");
    terminal.changePassword("students");

    // Checks if the isValidLogin method returns the correct value if username is passed with spaces in it
    
    if (AccessControl.isValidLogin("baby hippo", "students") != true) {
      System.out.println("ERROR: isValidLogin failed to return the correct value!");
      return false;
    }

    terminal.setCurrentUser("monkey");
    terminal.changePassword("bananas");
    
    if (AccessControl.isValidLogin("monkey", "bananas") != true) {
      System.out.println("ERROR: isValidLogin failed to return the correct value!");
      return false;
    }


    return true;
  }

  /**
   * Tester for the addUser method
   *
   * @return true if all tests pass
   */
  
  public static boolean testAddUserWithNoAdminPowers() {
    
	AccessControl terminal = new AccessControl();
    
	terminal.setCurrentUser("Admin");
    terminal.addUser("Adam", false);
    terminal.setCurrentUser("Adam");

    if (terminal.addUser("Jennifer") != false) {
      return false;
    }
    if (AccessControl.isValidLogin("Alice", "CHANGEME") != false) {
      return false;
    }


    return true;
  }

  /**
   * Tester for the addUser and RemoveUser method
   *
   * @return true if all tests pass
   */
  
  public static boolean testAddRemoveUserWithAdminPowers() {
    
    AccessControl terminal = new AccessControl();
    
    terminal.setCurrentUser("Admin");
    terminal.addUser("Rice", true);
    terminal.setCurrentUser("Rice");
    
    if (terminal.addUser("Mikkel", false) != true) {
      return false;
    }

    if (terminal.removeUser("Mikkel") != true) {
      return false;
    }

    
    terminal.setCurrentUser("Admin");
    
    try {
      terminal.addUser("wow");
      return false;
    } 
    
    catch (IllegalArgumentException e) {

    } 
    
    catch (Exception e) {
      return false;
    }

    
    try {
      terminal.addUser("jesus");
      return false;
    } 
    
    catch (IllegalArgumentException e) {

    } 
    
    catch (Exception e) {
      return false;
    }

    
    try {
      terminal.removeUser("bildgins");
      return false;
    } 
   
    catch (NoSuchElementException e) {

    } 
    
    catch (Exception e) {
      return false;
    }

    return true;
  }
}
