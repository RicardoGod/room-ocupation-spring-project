package corp.adentis.wc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/light")
public class LightController {
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView test() {
		int random = (int) Math.floor(Math.random()*10);
		return new ModelAndView("state/light","message",random);
		
		
	}

}
	
	

