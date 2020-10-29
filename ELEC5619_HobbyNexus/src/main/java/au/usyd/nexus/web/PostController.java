package au.usyd.nexus.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Comment;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.service.ArticleService;
import au.usyd.nexus.service.CommentService;
import au.usyd.nexus.service.UserAuthService;

@Controller
public class PostController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;


	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/post")
	public String displayContent(Model model, HttpServletRequest request,HttpSession session, Integer article_id) {
		//List<Comment> comments=commentService.getCommentsByArticleId(article_id);
		//model.addAttribute("comments", comments);
		model.addAttribute("article",this.articleService.getArticleById(article_id));
		model.addAttribute("newComment", new Comment());
		//if(session.getAttribute("user")==null) return "redirect:/register";
		//model.addAttribute("user",session.getAttribute("user"));
		return "post";
	}
	
	

	@RequestMapping("/makeComment/{article_id}")
	 public String makeComment(@ModelAttribute("newComment") Comment newComment, Model model, HttpSession session, String title, String content, @PathVariable("article_id") Integer article_id) {
		//
		
		logger.info("User has requested to make new comment");
		if(session.getAttribute("user")==null) return "redirect:/register";
		User user = (User)session.getAttribute("user");
		//Comment comment = (Comment)session.getAttribute("comment");
		//String content = comment.getContent();
		//String title = comment.getTitle();
		//System.out.println(article_id);
		//System.out.println(user.getUser_id());

		//Comment newComment = new Comment();
		//System.out.print(newComment.getComment_id());
		
		//Integer id = 7;
		//newComment.setComment_id(id);
		//System.out.println("id="+newComment.getComment_id());
		newComment.setCreate_time(new Date());
		newComment.setArtice_id(article_id);
		newComment.setTitle(title);
		newComment.setContent(content);
		newComment.setUser(user);
		//System.out.println(newComment.getTitle()+newComment.getContent()+newComment.getArtice_id()+newComment.getUser().getUser_id());
		commentService.addComment(newComment);

		return "redirect:/post?article_id="+article_id;
	}
	
	
/*	
	public Article getArticleById(Integer id) {
		return articleService.getArticleById(id);
	}
	
	public Comment getCommentById(Integer id) {
		return commentService.getCommentById(id);
	}
	
	public void addComment(Comment comment) {
		commentService.addComment(comment);
	}
	
	public List<Comment> getCommentsByArticleId(Integer id){
    	return commentService.getCommentsByArticleId(id);
    }
	
	public User getUserById(Integer id) {
		return commentService.getUserById(id);
    }
	
*/	
	
}
