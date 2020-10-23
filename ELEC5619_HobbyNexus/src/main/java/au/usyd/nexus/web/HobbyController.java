package au.usyd.nexus.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
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
		
/*        InputStream in = new FileInputStream("D:/4399/read.jpg");        
        byte[] b = new byte[in.available()];
        in.read(b);
        in.close();
      
        Hobby h=hobbyService.getHobby(1);
        h.setPhoto( Hibernate.createBlob(b));
        hobbyService.update(h);*/
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
}
