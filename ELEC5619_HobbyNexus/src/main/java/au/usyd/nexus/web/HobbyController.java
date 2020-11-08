package au.usyd.nexus.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.UserhobbyMay;
import au.usyd.nexus.service.HobbyService;


@Controller
public class HobbyController {

	@Autowired
    private HobbyService hobbyService;  
	
	@RequestMapping("/hobby")
	public String hobby(Model model,HttpServletRequest request,Integer hobby_id, HttpSession session) {
		List<User> users=hobbyService.getMembers(hobby_id);
		model.addAttribute("users", users);
		List<Article> articles=hobbyService.getArticles(hobby_id);
		model.addAttribute("articles", articles);

		Hobby hobby=hobbyService.getHobby(hobby_id);
		model.addAttribute("hobby", hobby);
		
		User user= (User) session.getAttribute("user");
		model.addAttribute("currentUser", user);
		return "hobby";
	}
	
	
	@RequestMapping("/imgDisplay")
	public void imgDisplay(HttpServletResponse response,HttpServletRequest request,Integer id,String type) throws IOException {
		

        Blob b=null;
        
        if("user".equals(type)){
        	User user=hobbyService.getUser(id);
        	try {
				b=user.getPhoto();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else{
        	Hobby hobby=hobbyService.getHobby(id);
        	b=hobby.getPhoto();
        }
        
		if(b!=null) {
			ServletOutputStream out;
	        try {
	        	out = response.getOutputStream();
				out.write(b.getBytes(1,(int) b.length()));
		        out.flush();
		        out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		
	
	}
	
	/**
	 * 
	* @Description: save hobby
	* @return   
	* @throws
	 */
	@RequestMapping("/savehobby")
	@ResponseBody
	public Map<String, Object> savehobby(@RequestParam("file") MultipartFile file,Hobby hobby,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		User user= (User) session.getAttribute("user");
		if(user==null){
			result.put("result", false);
			result.put("msg", "Please Sign in");
			return result;
		}
		if (file.isEmpty()) {
			result.put("result", false);
			result.put("msg", "Picture cannot be empty");
			return result;
		}
		try {
			InputStream in = file.getInputStream();
			int hobby_id=  (Integer) hobbyService.save(hobby,in);
			UserhobbyMay uhm=new UserhobbyMay();
			uhm.setHobby_id(hobby_id);
			uhm.setUser_id(user.getUser_id());
			uhm.setSkill_level(1);
			hobbyService.save(uhm);
			result.put("hobby_id", hobby_id);
			result.put("result", true);
			result.put("msg", "Success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * 
	* @Description: save  Article
	* @return   
	* @throws
	 */
	@RequestMapping("/saveArticle")
	@ResponseBody
	public Map<String, Object> saveArticle(Article  article,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		User user= (User) session.getAttribute("user");
		if(user==null){
			result.put("result", false);
			result.put("msg", "Please Sign in");
			return result;
		}
		article.setCreate_time(new Date());
		article.setUser(user);
		hobbyService.save(article);
		result.put("result", true);
		result.put("msg", "Success");
		return result;
	}
	
	@RequestMapping(value="/joinhobby/{hobby_id}", method = RequestMethod.POST)
	public String joinhobby(HttpSession session,@PathVariable("hobby_id") Integer hobby_id) {		
		User user= (User) session.getAttribute("user");
		List<User> users = hobbyService.getMembers(hobby_id);
		for (User u: users) {
			if(u.getUser_id() == user.getUser_id()) {
				return "redirect:/hobby?hobby_id="+ hobby_id;
				
			}
		}
		hobbyService.joinhobby(hobby_id,user.getUser_id());
		return "redirect:/hobby?hobby_id="+ hobby_id;
	}
	

}

