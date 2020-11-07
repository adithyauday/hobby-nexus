package au.usyd.nexus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.usyd.nexus.dao.CommentDAO;
import au.usyd.nexus.domain.Comment;
import au.usyd.nexus.domain.User;

@Component
public class CommentService {
	@Autowired
	private CommentDAO commentDAO;
	
    /**
     * This function returns Comment object with the comment id
     * 
     * @param id 
     * 
     * @return : null if id does not exist
     * 		 : Comment object if id exists
     */
    public Comment getCommentById(Integer id) {
    	return commentDAO.getCommentById(id);
    }
    
    
    /**
     * This function inserts new Comment object to database
     * 
     * @param comment: New Comment object
     * 
     */
    public void addComment(Comment comment) {
    	commentDAO.addComment(comment);
    }
    
    
    /**
     * This function returns Comment objects for specific article with article id
     * 
     * @param id 
     * 
     * @return : null if id does not exist
     * 		 : Comment objects if id exists
     */
    public List<Comment> getCommentsByArticleId(Integer id){
    	return commentDAO.getCommentsByArticleId(id);
    }
    
    /**
     * This function returns user object with given user id
     * 
     * @param id 
     * 
     * @return : null if id does not exist
     * 		 : user object if id exists
     */
    public User getUserById(Integer id) {
    	return commentDAO.getUserById(id);
    }
    
}
