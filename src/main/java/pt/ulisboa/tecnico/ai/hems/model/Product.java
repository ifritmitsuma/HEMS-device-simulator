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
import pt.ulisboa.tecnico.ai.hems.ext.ProductAction;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833989494039308862L;
	
	@Id
    @GeneratedValue
	private Long id;

	private String name;
	
	@OneToOne
	@JoinColumn(name = "DESC_ID")
	private ProductDesc description;
	
	private BigDecimal consumption;

	private String code;

	@OneToOne
	@JoinColumn(name = "STATE")
	private State state;
	
	public ProductAction createProductAction(Action action) {
		ProductAction productAction = new ProductAction();
		productAction.setName(name);
		productAction.setCode(getCode());
		productAction.setConsumption(consumption);
		productAction.setRating(description.getRating());
		productAction.setAction(action.getCode());
		return productAction;
	}
	
}
