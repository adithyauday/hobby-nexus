package au.usyd.nexus.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Event;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.EventService;
import au.usyd.nexus.service.HobbyService;



@Controller
public class EventlistController {

	@Autowired
    private EventService eventService;
	
	@Autowired
	private HobbyService hobbyService;

	@RequestMapping(value="/eventlist")
//	, @RequestParam(value="event_id",required=false,defaultValue="1")int event_id
	public String eventlist(Model model) {
//		List<User> users=eventService.getMembers(event_id);
//		model.addAttribute("users", users);
		
		List events = eventService.getAllEvent();
		model.addAttribute("events",events);
//		System.out.println(events);
//		List<Event> events=eventService.getEvents(event_id);
//		model.addAttribute("event", events);
////		System.out.println(events);
////		System.out.println(users);
//		Event event=eventService.getEvent(event_id);
//		model.addAttribute("event", event);
		
		return "eventlist";
	}
	
	@RequestMapping(value="/addevent",method= RequestMethod.GET)
	public String eventlistshow(Model model) {	

		List hobbys=eventService.getAllHobby();
		model.addAttribute("hobbys", hobbys);
////		System.out.println(events);
////		System.out.println(users);
//		Event event=eventService.getEvent(event_id);
//		model.addAttribute("event", event);
//		System.out.println(hobbys);
		return "add";
	}
	
	
	@RequestMapping(value="/addevent",method= RequestMethod.POST)
	public String eventlistAdd(Event event) {
//		List<User> users=eventService.getMembers(event_id);
//		model.addAttribute("users", users);
//		System.out.println(event);
		eventService.save(event);
//		List<Event> events=eventService.getEvents(event_id);
//		model.addAttribute("event", events);
////		System.out.println(events);
////		System.out.println(users);
//		Event event=eventService.getEvent(event_id);
//		model.addAttribute("event", event);
		
		return "redirect:/eventlist";
	}
//	
	
}