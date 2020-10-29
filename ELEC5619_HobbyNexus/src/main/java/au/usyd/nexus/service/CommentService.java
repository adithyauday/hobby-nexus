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
	
    public Comment getCommentById(Integer id) {
    	return commentDAO.getCommentById(id);
    }
    
    public void addComment(Comment comment) {
    	commentDAO.addComment(comment);
    }
    
    public List<Comment> getCommentsByArticleId(Integer id){
    	return commentDAO.getCommentsByArticleId(id);
    }
    
    public User getUserById(Integer id) {
    	return commentDAO.getUserById(id);
    }
    
}
