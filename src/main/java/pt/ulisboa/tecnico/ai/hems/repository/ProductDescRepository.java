package pt.ulisboa.tecnico.ai.hems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulisboa.tecnico.ai.hems.model.ProductDesc;
import pt.ulisboa.tecnico.ai.hems.model.ProductType;

@Repository
public interface ProductDescRepository extends JpaRepository<ProductDesc, Long> {
	
	public List<ProductDesc> findByType(ProductType type);

}
