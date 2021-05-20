package pt.ulisboa.tecnico.ai.hems.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class PowerSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5059976664558215617L;
	
	@Id
    @GeneratedValue
	private Long id;

	private String name;
	
	@Enumerated(EnumType.STRING)
	private InputOutput io;
	
	private BigDecimal power;
	
	private String code;
	
	private String icon;
	
}
