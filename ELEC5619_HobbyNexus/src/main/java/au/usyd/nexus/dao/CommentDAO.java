package au.usyd.nexus.dao;

import java.util.List;

import javax.annotation.Resource;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.domain.Comment;
import au.usyd.nexus.domain.User;

@Repository
public class CommentDAO {
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
   
    protected Session getSession(){
           return sessionFactory.openSession();
    }
    
    private Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
    }
    
    @SuppressWarnings("unchecked")
	public List<Comment> getCommentsByArticleId(Integer id){
    	
    	return (List<Comment>)this.getSession().createQuery("from Comment where artice_id = ?").setParameter(0,id).list();
    }
    
    public Comment getCommentById(Integer id) {
    	return (Comment)this.getSession().createQuery("from Comment where comment_id = ?").setParameter(0,id).uniqueResult();
    }
    @Transactional
    public void addComment(Comment comment) {
    	this.getCurrentSession().save(comment);
    }
    
    public User getUserById(Integer id) {
    	return (User)this.getSession().createQuery("from user where user_id = ?").setParameter(0,id).uniqueResult();
    }
}
