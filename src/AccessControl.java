=//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class deals with the methods related to how the access control system will work.
 */

public class AccessControl {
  
  private static ArrayList<User> users;
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * The given constructor creates a new instance of the access control class and initializes all current
   * users and users in the ArrayList to null
   *
   */
  
  public AccessControl() {
    
	User admin = new User("admin", "root", true);
    if (users == null) {
      users = new ArrayList<User>();
      users.add(admin);
    }
    currentUser = null;
  }

  /**
   * Checks whether the given username and password are valid entries
   *
   * @param username username of the given user
   * @param password password of the given user
   * @return true if username and password can be found from the arraylist
   */
  
  public static boolean isValidLogin(String username, String password) {
    
	// if username or password is null it will return false.
	  
	if (username == null || password == null) {
      return false;
    }
    
	// for loop which iterates through the array and checks whether each user's username 
    // and password against the method's parameters.

    for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username) && users.get(a).isValidLogin(password)) {
        
        return true;
      }
    }
    return false;
  }

  /**
   * Sets a new password for the current user.
   *
   * @param newPassword - value of the new password for the current user
   */
  
  public void changePassword(String newPassword) {
    currentUser.setPassword(newPassword);
  }

  /**
   * Logs the current user out from the system by changing the user's value to null
   *
   */
  
  public void logout() {
    currentUser = null;
  }

  /**
   * Sets the current user to the user from the users list whose username matches the
   * string provided as input
   * 
   * @param username - username of the user that needs to be changed to current user 
   */
  
  public void setCurrentUser(String username) {
    
	// for loop which iterates through the array and checks for any duplicate usernames
	  
	for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        
        currentUser = users.get(a);
      }
    }
  }

  /**
   * Creates a new instance of a user and saves it to the arraylist
   * 
   * @param username - username of the new user created
   * @return true if a new instance of the user was created
   * @throws IllegalArgumentException if username is null or if its length is less than 5.
   */

  public boolean addUser(String username) throws IllegalArgumentException {
    
	// returns false if current user in null or does not have admin power.
	  
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    
	// throws an IllegalArgumentException if username is null or if its length is less than 5.

    if (username == null || username.length() < 5) {
      throw new IllegalArgumentException("ERROR: username is null or its length is less than 5");
    }
    
    // uses a for loop to check if there are any duplicate usernames

    for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        throw new IllegalArgumentException("ERROR: Duplicate username");
        
      }
    }

    User new_user = new User(username, DEFAULT_PASSWORD, false);
    users.add(new_user);
    return true;
  }


  /**
   * Adds a new instance of user to the arraylist.
   * 
   * @param username - username of the new user created
   * @param isAdmin - checks if the given user has admin privileges
   * @return true if a new instance of the user was created
   * @throws IllegalArgumentException if username is null or if its length is less than 5.
   */
  
  public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {
    
	// returns false if current user in null or does not have admin power.
	  
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    
	// throws IllegalArgumentException if username is null or its length is less than 5.
	
    if (username == null || username.length() < 5) {
      throw new IllegalArgumentException("ERROR: username is null or its length is less than 5");
    }
    

    // uses a for loop to check if there are any duplicate usernames
    
    for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        throw new IllegalArgumentException("ERROR: Duplicate username");
        
      }
    }

    User new_user = new User(username, DEFAULT_PASSWORD, isAdmin);
    users.add(new_user);
    return true;
  }

  /**
   * Removes a particular user from the arraylist
   * 
   * @param username - username of the user that will be removed
   * @return true if the user was successfully removed from the arraylist
   * @throws NoSuchElementException if username does not exist.
   */

  public boolean removeUser(String username) throws NoSuchElementException {
    
	// returns false if currentuser is null or does not have admin power.
	
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
      
    }

	// uses a for loop to iterate through the arraylist and remove the username
	
	for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        users.remove(a);
        return true;
        
      }
    }
    
	 // throws NoSuchElementException if given username does not exist
    
	throw new NoSuchElementException("ERROR: No such username exists in the arraylist.");
  }


  /**
   * Gives a user admin power.
   * 
   * @param username - username of the user that will be given admin power
   * @return true if the user was successfully given admin power
   * @throws NoSuchElementException if username does not exist.
   */
  
  public boolean giveAdmin(String username) throws NoSuchElementException {
    
	// returns false if currentuser is null or does not have admin power.
	  
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    
	// uses a for loop to iterate through the arraylist and given the username admin power
	
    for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        users.get(a).setIsAdmin(true);
        return true;
       
      }
    }

    // throws NoSuchElementException if given username does not exist
    
    throw new NoSuchElementException("ERROR: No such username exists in the arraylist.");

  }

  /**
   * Removes the admin power of a username given its username
   * 
   * @param username - username of the user whose admin power will be taken
   * @return true if user's admin power was successfully taken 
   * @throws NoSuchElementException if username does not exist.
   */
  
  public boolean takeAdmin(String username) {
    
	// returns false if current user is null or does not have admin power.
	
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

	// uses a for loop to iterate through the arraylist and remove the given username's admin power
	
	for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        users.get(a).setIsAdmin(false);
        return true;
       
      }
    }

	// throws NoSuchElementException if given username does not exist
    
	throw new NoSuchElementException("ERROR: No such username exists in the arraylist");
  }

  /**
   * Resets the password of a user given their username
   * 
   * @param username - username of the user whose password will be rest
   * @return true if user's password was reset 
   * @throws NoSuchElementException if username does not exist.
   */
 
  public boolean resetPassword(String username) throws NoSuchElementException {
    
	// returns false if current user is null or does not have admin power.
	  
	if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

	// uses a for loop to iterate through the arraylist and resets the password of the given username
	
	for (int a = 0; a < users.size(); ++a) {
      if (users.get(a).getUsername().equals(username)) {
        users.get(a).setPassword(DEFAULT_PASSWORD);;
        return true;
      }
      
    }

	// throws NoSuchElementException if given username does not exist
    
	throw new NoSuchElementException("ERROR: No such username exists in the arraylist.");
  }
  
  public static void main(String[] args) {  
    
  }

}

