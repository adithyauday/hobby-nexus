package au.usyd.nexus.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.usyd.nexus.domain.Event;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Usereventmap;
import au.usyd.nexus.service.EventService;
import au.usyd.nexus.service.UserAuthService;



@Controller
public class EventController {

	@Autowired
    private EventService eventService;  

	@Autowired
	private UserAuthService uas;
	
	@RequestMapping(value="/event")
	public String event(Model model,HttpServletRequest request,Integer event_id) {
		List<User> users=eventService.getMembers(event_id);
		model.addAttribute("users", users);
		List<Event> events=eventService.getEvents(event_id);
		model.addAttribute("event", events);
//		System.out.println(events);
//		System.out.println(users);
		Event event=eventService.getEvent(event_id);
		model.addAttribute("event", event);
	
		return "event";
	}
	
	@RequestMapping(value = "/joinEvent/{event_id}/{user_id}", method = RequestMethod.POST)
	public String joinEvent(@PathVariable("event_id") Integer event_id, @PathVariable("user_id") Integer user_id,
			Model model, HttpSession session) {
		List<User> users=eventService.getMembers(event_id);
		User curr_user = uas.findById(user_id);
		for (User u: users) {
			if(u.getUser_id() == curr_user.getUser_id()) {
				return "redirect:/event?event_id={event_id}";
				
			}
		}
		Usereventmap uem = new Usereventmap();
		uem.setEvent_id(event_id);
		uem.setUser_id(user_id);
		eventService.saveUserEventMap(uem);
		
		return "redirect:/event?event_id={event_id}";
		
	}
	
}