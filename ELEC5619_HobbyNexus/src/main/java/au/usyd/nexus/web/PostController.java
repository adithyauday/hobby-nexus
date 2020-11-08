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
	
	
	/**
	 * This function takes the mapping "/post" and display the content of the article and all comments
	 *  
	 * @param model: Current model
	 * @param request: Current request
	 * @param session: Current session
	 * @param article_id: article id used to query article
	 * 
	 * @return : post page with article content and comments
	 */
	@RequestMapping("/post")
	public String displayContent(Model model, HttpServletRequest request,HttpSession session, Integer article_id) {
		model.addAttribute("article",this.articleService.getArticleById(article_id));
		model.addAttribute("newComment", new Comment());

		return "post";
	}
	
	
	/**
	 * This function takes the mapping "/makeComment/{article_id}" and create comment for current article
	 *  
	 * @param newComment: New comment from user
	 * @param model: Current model
	 * @param session: Current session
	 * @param title: Title of the new comment
	 * @param content: Content of the new comment
	 * @param article_id: The article ID of current article
	 * 
	 * @return : post page with article content and comments
	 */
	@RequestMapping("/makeComment/{article_id}")
	 public String makeComment(@ModelAttribute("newComment") Comment newComment, Model model, HttpSession session, String title, String content, @PathVariable("article_id") Integer article_id) {
		//Create a comment for this post
		
		logger.info("User has requested to make new comment");
		//Verify the user's login status, if not login yet, redirect to the login page.
		if(session.getAttribute("user")==null) return "redirect:/register";
		User user = (User)session.getAttribute("user");

		newComment.setCreate_time(new Date());
		newComment.setArtice_id(article_id);
		newComment.setTitle(title);
		newComment.setContent(content);
		newComment.setUser(user);

		commentService.addComment(newComment);
		
		return "redirect:/post?article_id="+article_id;
	}
	

	
}
