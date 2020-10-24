package au.usyd.nexus.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserManager;
import au.usyd.nexus.service.UserValidator;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ProfileController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired
	private UserAuthService authenticateService;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/viewProfile")
    public String viewProfile(Integer user_id, Model model, HttpSession session) {
		User selected_user = authenticateService.findById(user_id);
		if(selected_user == null) {
			return "redirect:/home";
		}
		model.addAttribute("selected_user", selected_user);
        return "/profile";
    }
	
    @RequestMapping(value = "/editProfile")
    public String profile(Model model, HttpSession session) {
    	logger.info("User has requested to edit profile page");
    	if(session.getAttribute("user")==null) return "redirect:/register";
    	model.addAttribute("detailsForm", new User());
        return "editProfile";
    }
	
    
    @RequestMapping(value = "/changeUserDetails/{user_id}", method = RequestMethod.POST)
    public String editUserDetails(@ModelAttribute("detailsForm") User user, BindingResult bindingResult, @PathVariable("user_id") Integer user_id, Model model, HttpSession session) {
    	logger.info("User has requested to edit their details: ", user.getUser_name());
    	userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
        	logger.info("Errors in updated user details");
            return "redirect:/editProfile";
        }
        
    	this.userManager.updateUserDetails(user);
    	session.setAttribute("user", user);
        return "redirect:/editProfile";
    }
	
}

