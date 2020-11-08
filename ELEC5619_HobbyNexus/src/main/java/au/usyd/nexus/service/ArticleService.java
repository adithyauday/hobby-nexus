package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import au.usyd.nexus.dao.ArticleDAO;
import au.usyd.nexus.dao.CommentDAO;
import au.usyd.nexus.domain.Article;



@Component
public class ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	
	//For test purpose
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}
	
    /**
     * This function returns Article object with the article id
     * 
     * @param id 
     * 
     * @return : null if id does not exist
     * 		 : Article object if id exists
     */
	public Article getArticleById(Integer id) {
		return articleDAO.getArticleById(id);
	}
		

}
