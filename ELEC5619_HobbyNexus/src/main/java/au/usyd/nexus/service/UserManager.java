package au.usyd.nexus.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.domain.User;

@Service(value="userManager")
@Transactional
public class UserManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void updateUserDetails(User user) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User existingUser = (User) currentSession.get(User.class, user.getUser_id());
		existingUser.setUser_name(user.getUser_name());
		existingUser.setLocation(user.getLocation());
		existingUser.setEmail(user.getEmail());
		currentSession.merge(existingUser);
	}
	
}
