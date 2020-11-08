package au.usyd.nexus.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.domain.User;

@Repository("userManagerDAO")
@Transactional
public class UserManagerDAO {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	public void updateUserDetails(User user) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User existingUser = (User) currentSession.get(User.class, user.getUser_id());
		existingUser.setUser_name(user.getUser_name());
		existingUser.setLocation(user.getLocation());
		existingUser.setEmail(user.getEmail());
		currentSession.merge(existingUser);
	}

	public void updateUserImage(User user) throws IOException, SQLException {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User existingUser = (User) currentSession.get(User.class, user.getUser_id());
		existingUser.setPhoto(user.getPhoto());
		currentSession.merge(existingUser);
	}

	public void updateUserPassword(User user) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User existingUser = (User) currentSession.get(User.class, user.getUser_id());
		existingUser.setPassword(user.hashPassword(user.getChangePassword()));
		existingUser.setChangePassword(null);
		currentSession.merge(existingUser);
	}

}
