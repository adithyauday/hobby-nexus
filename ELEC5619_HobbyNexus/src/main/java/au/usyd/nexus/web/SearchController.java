package au.usyd.nexus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.SearchService;

/**
 * Handles Search requests.
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	public void setSearchService(SearchService ps){
		this.searchService = ps;
	}
	
	/**
	 * This function takes the mapping "/search" and search users and hobbies given searchItem
	 *  
	 * @param searchText: Text needs to be searches
	 * 
	 * @return : ModelAndView containing the UserList and HobbyList
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@Param("searchText") String searchText) {
		ModelAndView mv = new ModelAndView("search");
		
		List<User> userList = this.searchService.search(searchText);
		List<Hobby> hobbyList = this.searchService.searchHobby(searchText);
		mv.addObject("userList", userList);
		mv.addObject("hobbyList", hobbyList);
		mv.addObject("searchedText", searchText);
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchText");
		return mv;
	}
	/**
	 * This function takes the mapping "/hobbyList" and search for all hobbies
	 *  
	 * @return : ModelAndView containing HobbyList
	 */
	@RequestMapping(value = "/hobbyList", method = RequestMethod.GET)
	public ModelAndView searchAllHobbies() {
		ModelAndView mv = new ModelAndView();
		
		List<Hobby> hobbyList = this.searchService.searchAllHobby();
		mv.addObject("hobbyList", hobbyList);
		return mv;
	}

}
