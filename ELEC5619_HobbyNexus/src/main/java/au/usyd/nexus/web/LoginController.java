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
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	@Autowired
	UserRegistrationService urs;
	
	@Autowired
    private UserAuthService authenticateService;         

    @Autowired
    private UserValidator userValidator;
	
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	
	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("loginForm", new User());
		return "register";
	}
	
	
	// Checks if the user credentials are valid or not.
    @RequestMapping(value = "/validate")
    public String validate(@ModelAttribute("loginForm") User loginForm, BindingResult bindingResult, @ModelAttribute("userForm") User userForm, Model model, HttpSession session) {
    	logger.info("User validating...");
        boolean isValid = authenticateService.checkLogin(loginForm.getEmail(), loginForm.getPassword());
 
        if(!isValid) {
            bindingResult.rejectValue("email", "invalidDetails");
            return "register";
        }
        
        User user = authenticateService.findByEmail(loginForm.getEmail());
        session.setAttribute("user" , user);
        logger.info(user.toString());
        return "redirect:home";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @ModelAttribute("loginForm") User loginForm, Model model, HttpSession session) {
    	logger.info("In registration");
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
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
    	logger.info("User has logged out");
    	session.setAttribute("user", null);
        return "redirect:home";
    }
    
	
	
}

