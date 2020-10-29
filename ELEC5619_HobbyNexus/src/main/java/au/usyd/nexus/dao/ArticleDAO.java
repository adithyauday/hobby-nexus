package au.usyd.nexus.dao;

import org.springframework.stereotype.Component; 
import org.springframework.stereotype.Repository;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.User;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.util.List;


@Repository
public class ArticleDAO {
   
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
           return sessionFactory.openSession();
    }
    
    
    public Article getArticleById(Integer artice_id) {
    	return (Article)this.getSession().createQuery("from Article where artice_id = ?").setParameter(0,artice_id).uniqueResult();
    }
    
    
}



