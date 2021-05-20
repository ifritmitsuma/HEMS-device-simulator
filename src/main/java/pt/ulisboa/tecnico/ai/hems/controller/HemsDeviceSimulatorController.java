package pt.ulisboa.tecnico.ai.hems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pt.ulisboa.tecnico.ai.hems.controller.forms.PowerSourceForm;
import pt.ulisboa.tecnico.ai.hems.controller.forms.ProductForm;
import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.enums.PowerSourceType;
import pt.ulisboa.tecnico.ai.hems.model.Action;
import pt.ulisboa.tecnico.ai.hems.model.Product;
import pt.ulisboa.tecnico.ai.hems.repository.StateRepository;
import pt.ulisboa.tecnico.ai.hems.service.ActionService;
import pt.ulisboa.tecnico.ai.hems.service.PowerSourceService;
import pt.ulisboa.tecnico.ai.hems.service.ProductService;

@Controller
public class HemsDeviceSimulatorController {

	@Autowired
	private PowerSourceService psService;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("powerSourceList", psService.getPowerSources(null));
		
		model.addAttribute("productList", prodService.getProducts());
		
		model.addAttribute("states", stateRepository.findAll());
		
		model.addAttribute("actions", actionService.getActions());
		
		model.addAttribute("psTypes", PowerSourceType.values());
		
		model.addAttribute("psDirections", InputOutput.values());
		
		model.addAttribute("form", new ProductForm());
		
		model.addAttribute("psForm", new PowerSourceForm());
		
		return "home";
	
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute ProductForm form, Model model) throws Exception {
		
		prodService.addProduct(form.getName(), form.getType(), form.getDescription(), form.getConsumption(), form.getCode(), form.getState());

		return "redirect:/";
		
	}
	
	@PostMapping("/changeState")
	public String changeState(@ModelAttribute ProductForm form, Model model) throws Exception {
		
		Action action = actionService.getActions().stream().filter(a -> { return a.getCode().equals(form.getAction()); } ).findFirst().get();
		
		Product product = prodService.getProduct(form.getId());
		
		prodService.sendActionToProduct(product, action);

		return "redirect:/";
		
	}
	
	@PostMapping("/addPowerSource")
	public String addPowerSource(@ModelAttribute PowerSourceForm psForm, Model model) throws Exception {
		
		psService.addPowerSource(psForm.getName(), psForm.getType(), psForm.getDirection(), psForm.getPower(), psForm.getCode());

		return "redirect:/";
		
	}
	
	@PostMapping("/removePowerSource")
	public String removePowerSource(@ModelAttribute PowerSourceForm psForm, Model model) throws Exception {
		
		psService.removePowerSource(psForm.getId());

		return "redirect:/";
		
	}
	
	
}
