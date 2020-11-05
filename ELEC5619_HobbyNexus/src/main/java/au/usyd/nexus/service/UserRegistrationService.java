package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;

/**
 * This class acts as a connection between LoginDAO and controllers for registration
 *
 */
@Component
public class UserRegistrationService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	/**
	 * Sets loginDAO: Mainly for testing purposes
	 * 
	 * @param loginDAO
	 */
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	/**
	 * This function adds user to the DB
	 * 
	 * @param user
	 */
	@Transactional
	public void addUser(User user) {
		System.out.println("in user service" + user);
		loginDAO.addUser(user);
	}
	
}
