package pt.ulisboa.tecnico.ai.hems.controller.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductForm {
	
	private Long id;
	
	private String name;
	
	private String type;
	
	private String description;
	
	private Double rating;
	
	private String code;
	
	private String state;

	private String action;
	
}
