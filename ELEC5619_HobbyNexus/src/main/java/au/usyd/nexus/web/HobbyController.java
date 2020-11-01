package au.usyd.nexus.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String hobby(Model model,HttpServletRequest request,Integer hobby_id) {
		List<User> users=hobbyService.getMembers(hobby_id);
		model.addAttribute("users", users);
		List<Article> articles=hobbyService.getArticles(hobby_id);
		model.addAttribute("articles", articles);

		Hobby hobby=hobbyService.getHobby(hobby_id);
		model.addAttribute("hobby", hobby);
		return "hobby";
	}
	
	
	@RequestMapping("/imgDisplay")
	public void imgDisplay(HttpServletResponse response,HttpServletRequest request,Integer id,String type) throws IOException {
		

        Blob b=null;
        if("user".equals(type)){
        	User user=hobbyService.getUser(id);
        	b=user.getPhoto();
        	
        }else{
        	Hobby hobby=hobbyService.getHobby(id);
        	b=hobby.getPhoto();
        }
        
		
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
			result.put("result", true);
			result.put("msg", "Success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}
	

}

