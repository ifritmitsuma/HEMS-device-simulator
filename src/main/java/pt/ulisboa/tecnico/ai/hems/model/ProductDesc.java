package pt.ulisboa.tecnico.ai.hems.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class ProductDesc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6083355148210819125L;
	
	@Id
    @GeneratedValue
	private Long id;
	
	private String description;
	
	private BigDecimal consumption;
	
	private String icon;
	
	@OneToOne
	@JoinColumn(name = "TYPE_ID")
	private ProductType type;

}
