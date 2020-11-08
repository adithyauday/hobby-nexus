package au.usyd.nexus.web;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import au.usyd.nexus.domain.Event;

import au.usyd.nexus.service.EventService;



@Controller
public class EventlistController {

	@Autowired
    private EventService eventService;
	

	@RequestMapping(value="/eventlist")

	public String eventlist(Model model) {

		
		List events = eventService.getAllEvent();
		model.addAttribute("events",events);

		
		return "eventlist";
	}
	
	@RequestMapping(value="/addevent",method= RequestMethod.GET)
	public String eventlistshow(Model model) {	

		List hobbys=eventService.getAllHobby();
		model.addAttribute("hobbys", hobbys);

		return "add";
	}
	
	
	@RequestMapping(value="/addevent",method= RequestMethod.POST)
	public String eventlistAdd(Event event) {

		eventService.save(event);

		
		return "redirect:/eventlist";
	}

	
}