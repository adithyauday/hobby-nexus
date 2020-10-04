	package au.usyd.nexus.dao;

import org.springframework.stereotype.Component; 
import org.springframework.stereotype.Repository;

import au.usyd.nexus.domain.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.util.List;

@Repository("loginDAO")
public class LoginDAO{
     
	private EntityManager em;
			 
       @Resource(name="sessionFactory")
       protected SessionFactory sessionFactory;

       public void setSessionFactory(SessionFactory sessionFactory) {
              this.sessionFactory = sessionFactory;
       }
      
       protected Session getSession(){
              return sessionFactory.openSession();
       }

       public boolean checkLogin(String email, String password){
			System.out.println("In Check login");
			Session session = sessionFactory.openSession();
			boolean userFound = false;
			//Query using Hibernate Query Language
			String SQL_QUERY =" from User as o where o.email=? and o.password=?";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,email);
			query.setParameter(1,password);
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				userFound= true;
			}

			session.close();
			return userFound;              
       }
       
       public User findByEmail(String email) {
			String SQL_QUERY =" from User as o where o.email=?";
			Session session = sessionFactory.openSession();
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,email);
			List list = query.list();

			if ((list != null) && (list.size() > 0)) {
				return (User)list.get(0);
			}
			return null;
       }
}
