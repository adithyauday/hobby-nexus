package au.usyd.nexus.web;

import java.sql.Blob;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String editUserDetails(@ModelAttribute("detailsForm") User user, BindingResult bindingResult,  @PathVariable("user_id") Integer user_id, Model model, HttpSession session) {
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
    
    //inspired from https://stackoverflow.com/questions/27101931/required-multipartfile-parameter-file-is-not-present-in-spring-mvc
    @RequestMapping(value = "/uploadImage/{user_id}", headers=("content-type=multipart/*"), method = RequestMethod.POST)
    public String editUserImage(@PathVariable("user_id") Integer user_id, @RequestParam("file") MultipartFile file, Model model, HttpSession session) {
    	logger.info("User has requested to change their picture.");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Blob blob = new SerialBlob(bytes );
                User u =(User) session.getAttribute("user");
                u.setPhoto(blob);
                this.userManager.updateUserImage(u);
            } catch (Exception e) {
                logger.info( "Could not read image");
            }
        } else {
            logger.info("No file uploaded");
        }
        return "redirect:/editProfile";
    }
	
}

