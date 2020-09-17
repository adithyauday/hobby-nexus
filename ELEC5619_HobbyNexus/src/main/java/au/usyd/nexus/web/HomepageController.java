package au.usyd.nexus.web;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HomepageController implements Controller {

	private  final Logger logger = LoggerFactory.getLogger(HomepageController.class);
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String now = (new Date()).toString();
		logger.info("Returning homepage view with " + now);
		return new ModelAndView("homepage", "now", now);
	}

}
