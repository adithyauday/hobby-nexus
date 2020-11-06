package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;

/**
 * 
 * This class acts as a connection between DAO and Controller
 *
 */
@Service("userAuthService")
public class UserAuthService {

	   @Autowired
	   private LoginDAO loginDAO;

	   /**
	    * Sets loginDAO: Main purpose is for testing
	    * 
	    * @param loginDAO
	    */
	   public void setLoginDAO(LoginDAO loginDAO) {
              this.loginDAO = loginDAO;
       }
      
	   /**
	    * This function checks if user credentials (email and password) are valid and match
	    * 
	    * @param email
	    * @param userPassword
	    * 
	    * @return	: true if credentials match
	    * 			: false if invalid credentials
	    */
       public boolean checkLogin(String email, String userPassword){
              System.out.println("In Service class...Check Login");
              boolean result = false;
              
              //if LoginDAO is null
              try {
            	  result= loginDAO.checkLogin(email, userPassword);
              } catch (NullPointerException e) {
                  System.out.print("LoginDAO not initialized");
              }
              
              return result;
       }
       
      /**
       * This function returns user object with the particular email
       * 
       * @param email
       * 
       * @return : null if email does not exist
       * 		 : user object if email exists
       */
      public User findByEmail(String email) {
			return loginDAO.findByEmail(email);
      }
       
      /**
       * This function returns user object with the particular id
       * 
       * @param id
       * 
       * @return : null if id does not exist
       * 		 : user object if id exists
       */
       public User findById(Integer id) {
			return loginDAO.findById(id);
     }
}