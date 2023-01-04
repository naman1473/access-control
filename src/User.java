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


/**
 * This class deals with the user part of the Access Control System Project.
 */

public class User {
  
  private final String USERNAME; // Username ID of the user used to login.
  private String password; // Password ID of the user used to login.
  private boolean isAdmin; // Boolean value for admin privileges.

  /**
   * The given constructor creates a new instance of the user class from the username, password and admin.
   *
   * @param username id of the user
   * @param password password of the user 
   * @param isAdmin  returns true if user is an admin
   */
  
  public User(String username, String password, boolean isAdmin) {
    
	USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Checks if the password entered is correct or not.
   *
   * @param password password of the user
   * @return true if the password is the same as the password data field
   */

  public boolean isValidLogin(String password) {
    
	 if (password.equals(this.password)) {
      return true;
    }
    return false;
  }

  /**
   * Uses the Java Getter method on username
   *
   * @return USERNAME of the given user
   */
  
  public String getUsername() {
    return USERNAME;
  }

  /**
   * Uses the Java Getter method on isAdmin
   *
   * @return isAdmin - a boolean value to check if user has admin privileges or not
   */
  
  public boolean getIsAdmin() {
    return isAdmin;
  }


  /**
   * Uses the Java Setter method on password
   *
   * @param password - new password change from default password
   */
  
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Uses the Java Setter method on isAdmin
   *
   * @param isAdmin - new isAdmin boolean value to be set
   */
  
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }


}

