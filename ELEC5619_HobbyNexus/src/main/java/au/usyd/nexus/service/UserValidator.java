package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import au.usyd.nexus.domain.User;

/**
 * This class validates form values
 *
 */
@Component
public class UserValidator implements Validator {

	@Autowired
    private UserAuthService userAuthService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * This function sends form errors if credentials are invalid
     * 1) email length > 40
     * 2) duplicate user email
     * 3) passwords don't match
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User duplicateUser = null;
        if(user.getEmail()!=null)
        	 duplicateUser = userAuthService.findByEmail(user.getEmail());
        else
        	duplicateUser = userAuthService.findById(user.getUser_id());
        if (user.getEmail()!=null && user.getEmail().length() > 40) {
        	System.out.println("Username got rejected");
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (duplicateUser != null && duplicateUser.getUser_id()!=user.getUser_id() ) {
        	System.out.println("Email got rejected");
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        if (user.getPassword()!=null && duplicateUser != null && (!user.getPassword().equals(duplicateUser.getPassword()))) {
        	System.out.println("Password got rejected ");
            errors.rejectValue("password", "Diff.userForm.password");
        }

    }
	
}
