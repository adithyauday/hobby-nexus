package au.usyd.nexus.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserRegistrationService;
import au.usyd.nexus.service.UserValidator;

/**
 * Handles requests for the register  page.
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserRegistrationService urs;
	
	@Autowired
    private UserAuthService authenticateService;         

    @Autowired
    private UserValidator userValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	
	/**
     * Sets UserAuthService: Main purpose is for testing
     * 
     * @param name - instance of UserAuthService
     */
	public void setUserAuthService(UserAuthService uas) {
		this.authenticateService = uas;
	}
	
	/**
     * Sets UserValidator: Main purpose is for testing
     * 
     * @param name - instance of UserValidator
     */
	public void setUserValidator(UserValidator validator) {
		this.userValidator = validator;
	}
	
	/**
     * Sets UserManager: Main purpose if for testing
     * 
     * @param name - instance of UserManager
     */
	public void setUserRegistraionService(UserRegistrationService urs) {
		this.urs = urs;
	}
	
	/**
	 * This function takes the mapping "/register" and returns the view (register.jsp) 
	 * 
	 * @param model - Current model
	 * 
	 * @return: register page with two forms (sign-up(userForm) and sign-in(loginForm))
	 */
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("loginForm", new User());
		return "register";
	}
	
	

	/**
	 * This function takes the mapping "/validate" and checks if user credentials are valid and returns home page
	 *  
	 * @param loginForm: Form user uses to login
	 * @param bindingResult: binding result for loginForm
	 * @param userForm: Form user uses to sign up (ignored in this method, throws error if not included)
	 * @param model: Current model
	 * @param session: Current session
	 * 
	 * @return : home page if user credentials are valid
	 * 		   : updates register page with errors if credentials invalid
	 */
    @RequestMapping(value = "/validate")
    public String validate(@ModelAttribute("loginForm") User loginForm, BindingResult bindingResult, @ModelAttribute("userForm") User userForm, Model model, HttpSession session) {
    	logger.info("User validating...");
        boolean isValid = authenticateService.checkLogin(loginForm.getEmail(), loginForm.hashPassword(loginForm.getPassword()));
 
        if(!isValid) {
            bindingResult.rejectValue("email", "invalidDetails");
            return "register";
        }
        
        User user = authenticateService.findByEmail(loginForm.getEmail());
        
        session.setAttribute("user" , user);
        logger.info(user.toString());
        return "redirect:home";
    }
    
    /**
     * This function takes the mapping "/addUser" and checks if user register details are valid and returns home page
	 *  
	 * @param userForm: Form user uses to sign up
	 * @param bindingResult: binding result for userForm
	 * @param loginForm: Form user uses to sign in (ignored in this method, throws error if not included)
	 * @param model: Current model
	 * @param session: Current session
	 * 
	 * @return : home page if user details are valid
	 * 		   : updates register page with errors if details invalid
	 */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @ModelAttribute("loginForm") User loginForm, Model model, HttpSession session) {
    	logger.info("In registration");
    	userForm.setPassword(userForm.hashPassword(userForm.getPassword()));
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        urs.addUser(userForm);

        logger.info(model.toString());
        User user = authenticateService.findByEmail(userForm.getEmail());
        session.setAttribute("user" , user);
        return "redirect:home";
    }
    
    /**
     * This function takes the mapping "/logout" and logs user out and returns home page
	 * 
     * @param session- Current session
     * 
     * @return : home page without logged in user
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
    	logger.info("User has logged out");
    	session.setAttribute("user", null);
        return "redirect:home";
    }
    
	
	
}

