package au.usyd.nexus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.nexus.dao.SearchDetDAO;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;

@Controller
public class SearchController {

	private SearchDetDAO searchDetDAO;
	
	@Autowired(required=true)
	@Qualifier(value="searchDetDAO")
	public void setSearchService(SearchDetDAO ps){
		this.searchDetDAO = ps;
		this.searchDetDAO.setFullTextEntityManager();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@Param("searchText") String searchText) {
		ModelAndView mv = new ModelAndView("search");
		
		List<User> userList = this.searchDetDAO.search(searchText);
		List<Hobby> hobbyList = this.searchDetDAO.searchHobby(searchText);
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

}
