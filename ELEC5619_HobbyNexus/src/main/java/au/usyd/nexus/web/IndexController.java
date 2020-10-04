package au.usyd.nexus.web;

import java.text.DateFormat;    
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserRegistrationService;
import au.usyd.nexus.service.UserValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController {
	
	@Autowired
	UserRegistrationService urs;
	
	@Autowired
    private UserAuthService authenticateService;            // This will auto-inject the authentication service into the controller.
	

//    @Autowired
//    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
	
	User user;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("loginForm", new User());
		return "register";
	}
	
	//GET RID OF THIS
	// Checks if the user credentials are valid or not.
    @RequestMapping(value = "/validate")
    public String validate(@ModelAttribute("loginForm") User loginForm, BindingResult bindingResult, @ModelAttribute("userForm") User userForm) {
    	logger.info("User validating...");
        String msg = "";
        boolean isValid = authenticateService.checkLogin(loginForm.getEmail(), loginForm.getPassword());
 
        if(isValid) {
            msg = "Welcome "  + "!";
        } else {
            msg = "Invalid credentials";
            bindingResult.rejectValue("email", "invalidDetails");
            return "register";
        }
        logger.info(msg);
 
        return "redirect:home";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, @ModelAttribute("loginForm") User loginForm, Model model) {
    	logger.info("In registration");
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        urs.addUser(userForm);


        return "redirect:home";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("userForm") User userForm, Model model, String error, String logout) {
    	logger.info(model.toString());
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "home";
    }
	
	
}
