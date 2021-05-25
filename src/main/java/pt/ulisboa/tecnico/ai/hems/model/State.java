package pt.ulisboa.tecnico.ai.hems.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8447707034410977940L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String value;
	
	private String label;
	
	private String icon;
	
}
