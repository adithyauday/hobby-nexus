package au.usyd.nexus.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.SearchDetDAO;
import au.usyd.nexus.domain.User;

public class SearchService {
	private SearchDetDAO searchDetDAO;
	
	public void setSearchDetDAO(SearchDetDAO searchDetDAO) {
		this.searchDetDAO = searchDetDAO;
	}
	
	@Transactional
	public List<User> search(String searchItem){
		return this.searchDetDAO.search(searchItem);
	}
	
}
