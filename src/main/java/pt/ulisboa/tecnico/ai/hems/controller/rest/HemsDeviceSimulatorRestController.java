package pt.ulisboa.tecnico.ai.hems.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HemsDeviceSimulatorRestController {	

	@RequestMapping("/api/hello")
	public String helloWorld(@RequestParam(name="name", required=false, defaultValue="World") String name) {
		return "Hello, " + name;
	}
	
}
