package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.usyd.nexus.dao.ArticleDAO;
import au.usyd.nexus.domain.Article;



@Component
public class ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	
	public Article getArticleById(Integer id) {
		return articleDAO.getArticleById(id);
	}
		

}
