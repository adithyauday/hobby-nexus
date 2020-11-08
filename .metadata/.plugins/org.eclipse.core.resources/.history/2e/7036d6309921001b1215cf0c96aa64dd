package au.usyd.nexus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.SearchDetDAO;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Hobby;

@Service("searchService")
public class SearchService {
	@Autowired
	private SearchDetDAO searchDetDAO;
	
	public void setSearchDetDAO(SearchDetDAO searchDetDAO) {
		this.searchDetDAO = searchDetDAO;
		this.searchDetDAO.setFullTextEntityManager();
	}
	
	@Transactional
	public List<User> search(String searchItem){
		return this.searchDetDAO.search(searchItem);
	}
	
	@Transactional
	public List<Hobby> searchHobby(String searchItem){
		return this.searchDetDAO.searchHobby(searchItem);
	}
	
}
