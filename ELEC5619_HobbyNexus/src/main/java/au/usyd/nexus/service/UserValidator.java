package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import au.usyd.nexus.domain.User;

@Component
public class UserValidator implements Validator {

	@Autowired
    private UserAuthService userAuthService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User duplicateUser = userAuthService.findByEmail(user.getEmail());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() > 40) {
        	System.out.println("Username got rejected");
            errors.rejectValue("email", "Size.userForm.email");
        }
        if (duplicateUser != null && duplicateUser.getUser_id()!=user.getUser_id() ) {
        	System.out.println("Email got rejected");
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        
        if (user.getPassword()!=null && (user.getPassword().length() < 8 || user.getPassword().length() > 32)) {
        	System.out.println("Password got rejected");
            errors.rejectValue("password", "Size.userForm.password");
        }

    }
	
}
