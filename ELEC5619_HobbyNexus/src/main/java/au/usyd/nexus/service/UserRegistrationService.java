package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;

@Component
public class UserRegistrationService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	@Transactional
	public void addUser(User user) {
		System.out.println("in user service" + user);
		loginDAO.addUser(user);
	}
	
}
