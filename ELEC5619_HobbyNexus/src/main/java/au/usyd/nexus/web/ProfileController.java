package au.usyd.nexus.web;

import java.sql.Blob;
import java.util.List;

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

import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.HobbyService;
import au.usyd.nexus.service.UserAuthService;
import au.usyd.nexus.service.UserManager;
import au.usyd.nexus.service.UserValidator;
import au.usyd.nexus.service.HobbyService;

/**
 * Handles requests for the user profile and edit pages.
 */

@Controller
@RequestMapping("/")
public class ProfileController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserAuthService authenticateService;
	
	@Autowired
	private HobbyService hobbyService;
	
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
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * This function takes the mapping "/viewProfile" and returns the view
	 * (profile.jsp) of user profile
	 * 
	 * @param user_id  - The id of the user for the required user profile page
	 * @param model    - Current model
	 * @param session- Current session
	 * 
	 * @return : home page view if user for user_id does not exist : User profile
	 *         page if user for user_id not null
	 */
	@RequestMapping(value = "/viewProfile")
	public String viewProfile(Integer user_id, Model model, HttpSession session) {
		User selected_user = authenticateService.findById(user_id);
		if (selected_user == null) {
			return "redirect:/home";
		}
		List<Hobby> hobbies = hobbyService.getHobbies(user_id);
		model.addAttribute("selected_user", selected_user);
		model.addAttribute("hobbies", hobbies);
		return "/profile";
	}

	/**
	 * This function takes the mapping "/editProfile" and returns the view
	 * (editProfile.jsp) for logged in user
	 * 
	 * @param model
	 * @param session
	 * @return : Profile editing page if user is logged in : Register page if user
	 *         not logged in
	 */
	@RequestMapping(value = "/editProfile")
	public String profile(Model model, HttpSession session) {
		logger.info("User has requested to edit profile page");
		if (session.getAttribute("user") == null)
			return "redirect:/register";
		model.addAttribute("detailsForm", new User());
		model.addAttribute("passForm", new User());
		return "editProfile";
	}

	/**
	 * This function takes the mapping "/changeUserDetails" and changes user details
	 * and refreshes current the page with updated details
	 * 
	 * 
	 * @param user          - Form where user updates their detail
	 * @param bindingResult - binding result for user
	 * @param userPass      - Form where user passes their password changes (ignored
	 *                      in this method, throws error if not included)
	 * @param user_id       - user_id of current user
	 * @param model         - Current model
	 * @param session-      Current session
	 * 
	 * @return : updates current page displaying errors if not invalid/duplicate
	 *         email : updates current page with changed user details
	 */
	@RequestMapping(value = "/changeUserDetails", method = RequestMethod.POST)
	public String editUserDetails(@ModelAttribute("detailsForm") User user, BindingResult bindingResult,
			@ModelAttribute("passForm") User userPass, Integer user_id, Model model, HttpSession session) {
		logger.info("User has requested to edit their details: ");
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.info("Errors in updated user details");
			return "editProfile";
		}

		logger.info("User has successfully changes their details: ");
		this.userManager.updateUserDetails(user);
		session.setAttribute("user", user);
		return "redirect:/editProfile";
	}

	/**
	 * This function takes the mapping "/changePass" and changes user password and
	 * refreshes current the page with updated details
	 * 
	 * @param user          - Form where user changes their password
	 * @param bindingResult - binding result for user
	 * @param userDetails   - Form where user changes their details (ignored in this
	 *                      method, throws error if not included)
	 * @param user_id       - user_id of current user
	 * @param model         - Current model
	 * @param session-      Current session
	 * 
	 * @return : updates current page displaying errors if not old password does not
	 *         match DB password : refreshes current page
	 */
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public String changeUserPass(@ModelAttribute("passForm") User user, BindingResult bindingResult,
			@ModelAttribute("detailsForm") User userDetails, Integer user_id, Model model, HttpSession session) {
		logger.info("User has requested to change their password ");
		user.setPassword(user.hashPassword(user.getPassword()));
		user.setUser_id(user_id);
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.info("Errors in password change");
			return "editProfile";
		} else {
			logger.info("Passwords match");
			this.userManager.updateUserPassword(user);
		}

		User curr_user = (User) session.getAttribute("user");
		curr_user.setPassword(user.getChangePassword());
		session.setAttribute("user", curr_user);
		return "redirect:/editProfile";
	}

	// inspired from
	// https://stackoverflow.com/questions/27101931/required-multipartfile-parameter-file-is-not-present-in-spring-mvc
	/**
	 * This function takes the mapping "/uploadImage/{user_id}" and uploads user
	 * image and updates page with changed picture
	 * 
	 * @param user_id  - user_id of logged in user
	 * @param file     - File used for image change
	 * @param model    - Current model
	 * @param session- Current session
	 * 
	 * @return : updates profile page with current image if successful
	 */
	@RequestMapping(value = "/uploadImage/{user_id}", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public String editUserImage(@PathVariable("user_id") Integer user_id, @RequestParam("file") MultipartFile file,
			Model model, HttpSession session) {
		logger.info("User has requested to change their picture.");
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Blob blob = new SerialBlob(bytes);
				User u = (User) session.getAttribute("user");
				u.setPhoto(blob);
				this.userManager.updateUserImage(u);
			} catch (Exception e) {
				logger.info("Could not read image");
			}
		} else {
			logger.info("No file uploaded");
		}
		return "redirect:/editProfile";
	}

}
