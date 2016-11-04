package corp.adentis.wc.controllers;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import corp.adentis.wc.model.entities.Door;
import corp.adentis.wc.model.entities.DoorAverage;
import corp.adentis.wc.model.entities.Light;
import corp.adentis.wc.model.enumerations.DoorStates;
import corp.adentis.wc.model.repositories.DoorAverageRepository;
import corp.adentis.wc.model.repositories.DoorRepository;
import corp.adentis.wc.model.repositories.LightRepository;

@Controller
@RequestMapping("/insertEvent")
public class InsertEventController {

	@Autowired private DoorRepository doorRepository;
	@Autowired private LightRepository lightRepository;
	@Autowired private DoorAverageRepository doorAverageRepository;

	
	
	@RequestMapping(value="/door", method=RequestMethod.POST)
	public synchronized String insertDoor(@Valid @ModelAttribute Door door, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()){
			System.out.println("Contem Erros");
			System.out.println(bindingResult.getFieldError());
		}
		else{	
			//Obter a lista de todos os eventos door e ordena-los para obter o ultimo 
			/********************************************************************************************/
			/********PROVAVELMENTE NECESSITA MELHORAMENTOS CASO NO FUTURO EXISTAM IMENSAS ENTRADAS*******/
			/********************************************************************************************/
			Door lastDoorEvent = null;
			long last_id = 0;
			Iterable<Door> doorRepoIterable = doorRepository.findAll();
			
			List<Door> list = Lists.newArrayList(doorRepoIterable);
			Collections.sort(list);
			
			if(!list.isEmpty()){
				lastDoorEvent = (Door) Iterables.getLast(doorRepoIterable);
				last_id = lastDoorEvent.getId();
			}
						
			//Se houver conteudo na BD analisar antes de adicionar
			if(last_id>0){
				
				long difference_in_seconds = (door.getDate().getTime()-lastDoorEvent.getDate().getTime())/1000;
				//Se o estado cumprir os requisistos adiciona a BD
				if(!lastDoorEvent.getState().equals(door.getState())){	
					if(difference_in_seconds>1){
						doorRepository.save(door);
						/******************************/
						/** Adicionar a outra tabela **/
						/******************************/
						
						insertDoorAverage(door);
						
					}
					//Senao não faz nada
					else {
						System.out.println("Trata-se de uma leitura demasiado rápida...Não vou adicionar!");
					}
				}
				//Senao não faz nada
				else{
					System.out.println("Dois estados consecutivos... Um pouco estranho não?");
				}
			}
			//Se for o primeiro registo adiciona
			else{
				doorRepository.save(door);
				/******************************/
				/** Adicionar a outra tabela **/
				/******************************/
				insertDoorAverage(door);
			}
		}

		return "redirect:insert";
	}
	
	
	public synchronized void insertDoorAverage(Door door){
		
			
			
			//Obter a lista de todos os eventos door e ordena-los para obter o ultimo 
			/********************************************************************************************/
			/********PROVAVELMENTE NECESSITA MELHORAMENTOS CASO NO FUTURO EXISTAM IMENSAS ENTRADAS*******/
			/********************************************************************************************/
			DoorAverage lastDoorAverageEvent = null;
			DoorAverage doorAverage = new DoorAverage();
			Iterable<DoorAverage> doorAverageRepoIterable = doorAverageRepository.findAll();
			
			List<DoorAverage> list = Lists.newArrayList(doorAverageRepoIterable);
			Collections.sort(list);
			
			if(!list.isEmpty()){
				lastDoorAverageEvent = (DoorAverage) Iterables.getLast(doorAverageRepoIterable);
				
				if(lastDoorAverageEvent.getExit_time()==null){
					System.out.println("Update da última entrada");
					long elapsed_time = (door.getDate().getTime()-lastDoorAverageEvent.getEntry_time().getTime())/1000;
					long average_time = 0;
					//Necessita desta comparacao devido a forma como iniciamos as entradas
					if(lastDoorAverageEvent.getAverage_time()!=0){
						average_time = (lastDoorAverageEvent.getAverage_time()+elapsed_time)/2;
					}
					else{
						average_time = elapsed_time;
					}
					int result = doorAverageRepository.setExitEvent(lastDoorAverageEvent.getId(), door.getDate(), elapsed_time, average_time);
					System.out.println("SUCESS? "+result);
				}
				else{
					System.out.println("Inserir uma entrada nova");
					//Copiamos para ja o tempo medio da ultima entrada e atualizamos quando formos inserir a data de saida 
					doorAverage.setEntry_time(door.getDate());
					doorAverage.setAverage_time(lastDoorAverageEvent.getAverage_time());
					doorAverageRepository.save(doorAverage);
					
				}
				
			}
			else{
				//ESPERAR POR UM EVENTO DE FECHO PARA INICIAR EVENTO
				if(door.getState().equals(DoorStates.CLOSE)){
					System.out.println("Estou a iniciar a recolha das médias");
					doorAverage.setEntry_time(door.getDate());
					doorAverageRepository.save(doorAverage);
				}
				
			}
			
	}
	
	
	@RequestMapping(value="/light", method=RequestMethod.POST)
	public String insertLight(@Valid @ModelAttribute Light door, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()){
			System.out.println("Contem Erros");
			System.out.println(bindingResult.getFieldError());
		}
		else{
			lightRepository.save(door);
		}
		
		System.out.println("Acabei");
		
		return "redirect:insert";
	}
	
	
	
}
