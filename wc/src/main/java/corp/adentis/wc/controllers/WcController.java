package corp.adentis.wc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import corp.adentis.wc.model.repositories.DoorRepository;
import corp.adentis.wc.model.repositories.LightRepository;

@Controller
@RequestMapping("/isopen")
public class WcController {
	
	
	@Autowired private DoorRepository doorRepository;
	@Autowired private LightRepository lightRepository;
	
	@RequestMapping("/totalregister")
	@ResponseBody
	public String totalEntrads(){
		return "Existem " +doorRepository.count()+" de registos na tabela \"Door\" de momento \n"
				+ "Existem " +lightRepository.count()+" de registos na tabela \"Light\" de momento";
		
	}
	
}
