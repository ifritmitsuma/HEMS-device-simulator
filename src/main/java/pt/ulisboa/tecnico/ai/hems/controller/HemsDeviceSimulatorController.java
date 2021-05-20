package pt.ulisboa.tecnico.ai.hems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HemsDeviceSimulatorController {

	@GetMapping("/")
	public String home(Model model) {
		
		return "home";
	
	}
	
}
