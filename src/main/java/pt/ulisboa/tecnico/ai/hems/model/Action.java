package pt.ulisboa.tecnico.ai.hems.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882390812269761932L;
	
	@Id
    @GeneratedValue
	private Long id;
	
	private String name;
	
	private String code;
	
	@OneToOne
	@JoinColumn(name = "STATE_AFTER")
	private State stateAfter;
	
}
