package pt.ulisboa.tecnico.ai.hems.controller.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.enums.PowerSourceType;

@Getter @Setter @NoArgsConstructor
public class PowerSourceForm {
	
	private Long id;
	
	private String name;
	
	private PowerSourceType type;
	
	private InputOutput direction;
	
	private Double power;
	
	private String code;
	
}
