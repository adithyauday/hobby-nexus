package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;


@Service("userAuthService")
public class UserAuthService {

	 @Autowired
	 private LoginDAO loginDAO;

	   public void setLoginDAO(LoginDAO loginDAO) {
              this.loginDAO = loginDAO;
       }
      
       public boolean checkLogin(String email, String userPassword){
              System.out.println("In Service class...Check Login");
              return loginDAO.checkLogin(email, userPassword);
       }
       
       public User findByEmail(String email) {
			return loginDAO.findByEmail(email);
      }
       
       public User findById(Integer id) {
			return loginDAO.findById(id);
     }
}