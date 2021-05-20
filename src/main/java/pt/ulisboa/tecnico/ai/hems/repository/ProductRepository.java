package pt.ulisboa.tecnico.ai.hems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulisboa.tecnico.ai.hems.model.Product;
import pt.ulisboa.tecnico.ai.hems.model.ProductDesc;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByDescriptionIn(List<ProductDesc> descriptionList);
	
	public Product findByCode(String code);

}
