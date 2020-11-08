package au.usyd.nexus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.SearchDetDAO;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Hobby;

/**
 *  This service class connects SearchDetDAO with other Controllers
 *
 */
@Service("searchService")
public class SearchService {
	@Autowired
	private SearchDetDAO searchDetDAO;
	
	public void setSearchDetDAO(SearchDetDAO searchDetDAO) {
		this.searchDetDAO = searchDetDAO;
		this.searchDetDAO.setFullTextEntityManager();
	}
	/**
	* This function searches user given (searchItem)
	* @param searchItem
	* @return	: List of Users whose name are similar to searchItem
	*/
	@Transactional
	public List<User> search(String searchItem){
		return this.searchDetDAO.search(searchItem);
	}
	/**
	* This function searches hobbies given (searchItem)
	* @param searchItem
	* @return	: List of Hobbies whose name are similar to searchItem
	*/
	@Transactional
	public List<Hobby> searchHobby(String searchItem){
		return this.searchDetDAO.searchHobby(searchItem);
	}
	/**
	* This function searches all hobbies in database
	* @return	: List of all Hobbies
	*/
	@Transactional
	public List<Hobby> searchAllHobby(){
		return this.searchDetDAO.searchAllHobby();
	}
}
