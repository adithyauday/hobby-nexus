package au.usyd.nexus.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.search.FullTextQuery;
//import org.hibernate.search.FullTextSession;
//import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.apache.lucene.search.Query;

import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;


@Repository("searchDetDAO")
@Transactional
public class SearchDetDAO {
		
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;
	@PersistenceContext
	EntityManager entityManager;

	
    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
           return sessionFactory.openSession();
    }
    
    @SuppressWarnings("unchecked")
	public List<User> search(String searchItem){
    			
		FullTextEntityManager fullTextEntityManager2 = Search.getFullTextEntityManager(entityManager);
	  	try {
	  			fullTextEntityManager2.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  	FullTextEntityManager fullTextEntityManager =
	  		    org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
	  	
	  	QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
	  			  .buildQueryBuilder()
	  			  .forEntity(User.class)
	  			  .get();
	  	
	  	org.apache.lucene.search.Query query = queryBuilder
	  			  .keyword()
	  			  .fuzzy()
	  			  .onField("user_name")
	  			  .matching(searchItem)
	  			  .createQuery();
	  	
	  	org.hibernate.search.jpa.FullTextQuery jpaQuery
	  	  = fullTextEntityManager.createFullTextQuery(query, User.class);
	  	List<User> results = jpaQuery.getResultList();

		return results;
    }
    
    @SuppressWarnings("unchecked")
	public List<Hobby> searchHobby(String searchItem){
    			
		FullTextEntityManager fullTextEntityManager2 = Search.getFullTextEntityManager(entityManager);
	  	try {
	  			fullTextEntityManager2.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  	FullTextEntityManager fullTextEntityManager =
	  		    org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
	  	
	  	QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
	  			  .buildQueryBuilder()
	  			  .forEntity(Hobby.class)
	  			  .get();
	  	
	  	org.apache.lucene.search.Query query = queryBuilder
	  			  .keyword()
	  			  .fuzzy()
	  			  .onField("hobby_name")
	  			  .matching(searchItem)
	  			  .createQuery();
	  	
	  	org.hibernate.search.jpa.FullTextQuery jpaQuery
	  	  = fullTextEntityManager.createFullTextQuery(query, Hobby.class);
	  	List<Hobby> results = jpaQuery.getResultList();

		return results;
    }

}