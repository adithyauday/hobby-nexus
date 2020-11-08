package au.usyd.nexus.dao;
 
import org.springframework.stereotype.Repository;
import au.usyd.nexus.domain.Article;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;



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



