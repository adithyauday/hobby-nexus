package au.usyd.nexus.service;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.domain.User;

@Component
public class UserRegistrationService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void addUser(User user) {
		System.out.println("in user service" + user);
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
}
