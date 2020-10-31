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

//	public void start() {
//		 
//		ApplicationContext cxt = new ClassPathXmlApplicationContext("persisence-context.xml");
//		eventService = (EventService) cxt.getBean("eventService");
//	}

	
//	@RequestMapping(value="/eventlist",method= {RequestMethod.GET})
//	public String eventlist(Model model, @RequestParam(value="event_id",required=false,defaultValue="2")int event_id) {
////		List<User> users=eventService.getMembers(event_id);
////		model.addAttribute("users", users);
//		
//		List<Event> events=eventService.getEvents(event_id);
//		model.addAttribute("event", events);
//////		System.out.println(events);
//////		System.out.println(users);
////		Event event=eventService.getEvent(event_id);
////		model.addAttribute("event", event);
//		
//		return "eventlist";
//	}
//	
	@RequestMapping(value="/eventlist",method= {RequestMethod.GET})
	public String eventlist(Model model, @RequestParam(value="event_id",required=false,defaultValue="2")int event_id) {
//		List<User> users=eventService.getMembers(event_id);
//		model.addAttribute("users", users);
		
		List events = eventService.getAllEvent();
		model.addAttribute("events",events);
		System.out.println(events);
//		List<Event> events=eventService.getEvents(event_id);
//		model.addAttribute("event", events);
////		System.out.println(events);
////		System.out.println(users);
//		Event event=eventService.getEvent(event_id);
//		model.addAttribute("event", event);
		
		return "eventlist";
	}
	
}