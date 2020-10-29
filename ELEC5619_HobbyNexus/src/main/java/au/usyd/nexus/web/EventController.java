package au.usyd.nexus.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Event;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.EventService;
import au.usyd.nexus.service.HobbyService;



@Controller
public class EventController {

	@Autowired
    private EventService eventService;  

	
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
	
}