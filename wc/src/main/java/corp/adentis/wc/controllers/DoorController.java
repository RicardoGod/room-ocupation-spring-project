package corp.adentis.wc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import corp.adentis.wc.model.entities.Door;
import corp.adentis.wc.model.repositories.DoorRepository;

@Controller
@RequestMapping("/door")
public class DoorController {

	@Autowired private DoorRepository doorRepository; 
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String listDoorState(Model model){
		
		Iterable<Door> listEvents = doorRepository.findAll();
		
		//model.addAttribute("favicon","<link rel="icon" type="image/x-icon" href="http://localhost/images/favicongreen.ico" />");
		//model.addAttribute("favicon","<link rel="icon" type="image/x-icon" href="http://localhost/images/faviconred.ico" />");
		
		model.addAttribute("doorEvents", listEvents);
		
		return "state/door";
	}
	
	
}
