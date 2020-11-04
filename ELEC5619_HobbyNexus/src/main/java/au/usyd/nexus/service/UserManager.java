package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.nexus.dao.UserManagerDAO;
import au.usyd.nexus.domain.User;

@Service(value="userManager")
public class UserManager {

	 @Autowired
	 private UserManagerDAO userManagerDAO;

	 public void setUserManagerDAO(UserManagerDAO userManagerDAO) {         
		 this.userManagerDAO = userManagerDAO;
     }
	
	
	public void updateUserDetails(User user) {
		userManagerDAO.updateUserDetails(user);
	}
	
}
