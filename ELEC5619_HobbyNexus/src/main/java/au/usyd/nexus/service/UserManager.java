package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.nexus.dao.UserManagerDAO;
import au.usyd.nexus.domain.User;

/**
 *  This class acts as a connection between UserManagerDAO and Controllers
 *
 */
@Service(value="userManager")
public class UserManager {

	 @Autowired
	 private UserManagerDAO userManagerDAO;

	 /**
	  * Sets userManagerDAO: Mainly for testing purposes
	  * @param userManagerDAO
	  */
	 public void setUserManagerDAO(UserManagerDAO userManagerDAO) {         
		 this.userManagerDAO = userManagerDAO;
     }
	
	/**
	 * Updates user details in the DB
	 * 
	 * @param user
	 */
	public void updateUserDetails(User user) {
		userManagerDAO.updateUserDetails(user);
	}
	
	/**
	 * Updates user image in DB
	 * 
	 * @param user
	 */
	public void updateUserImage(User user) {
		userManagerDAO.updateUserImage(user);
	}
	
	/**
	 * Updates user password in DB
	 * 
	 * @param user
	 */
	public void updateUserPassword(User user) {
		userManagerDAO.updateUserPassword(user);
	}
	
}
