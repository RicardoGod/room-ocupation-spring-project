package corp.adentis.wc.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import corp.adentis.wc.model.entities.Door;
import corp.adentis.wc.model.entities.DoorAverage;
import corp.adentis.wc.model.enumerations.DoorStates;
import corp.adentis.wc.model.repositories.DoorAverageRepository;
import corp.adentis.wc.model.repositories.DoorRepository;

@Controller
@RequestMapping("/door")
public class DoorController {

	@Autowired private DoorRepository doorRepository; 
	@Autowired private DoorAverageRepository doorAverageRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listDoorState(Model model){
		
		Iterable<Door> listEvents = doorRepository.findAll();
		List<Door> list = Lists.newArrayList(listEvents);
		Collections.sort(list);
		
		if(!list.isEmpty()){
			Door current_door = Iterables.getLast(list);
			DoorStates current_state = current_door.getState();
			switch(current_state){
				case OPEN: 
					model.addAttribute("favicon","favicongreen");
					model.addAttribute("message","A Porta está aberta.");
					model.addAttribute("estimated","");
					break;
				case CLOSE: 
					model.addAttribute("favicon","faviconred"); 
					model.addAttribute("message","A Porta está fechada! Tente mais tarde...");
					model.addAttribute("estimated","A última pessoa fechou a porta às "+current_door.getDate().getHours()+"h"+current_door.getDate().getMinutes()+"min "+current_door.getDate().getSeconds()+"s");
					break;
				default: break;
			}
		}
		
		model.addAttribute("doorEvents", listEvents);
		
		return "state/door";
	}
	
	@RequestMapping(value="/log",method=RequestMethod.GET)
	public String listDoorLogs(Model model){
		
		Iterable<DoorAverage> eventos = doorAverageRepository.findAll();
		
		model.addAttribute("eventos",eventos);
		return "log/door_log";
	}
	
	
	
	
}
