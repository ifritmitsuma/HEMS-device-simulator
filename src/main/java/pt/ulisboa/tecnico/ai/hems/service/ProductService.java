package pt.ulisboa.tecnico.ai.hems.service;

import java.util.List;

import pt.ulisboa.tecnico.ai.hems.model.Action;
import pt.ulisboa.tecnico.ai.hems.model.Product;
import pt.ulisboa.tecnico.ai.hems.model.ProductDesc;
import pt.ulisboa.tecnico.ai.hems.model.ProductType;

public interface ProductService {
	
	public Long addProductType(String name);
	
	public void removeProductType(Long id);
	
	public List<ProductType> getProductTypes();

	public Long addProductDesc(String description, String icon, ProductType type, Double consumption);
	
	public ProductDesc getProductDesc(Long id);
	
	public List<ProductDesc> getProductDescs();
	
	public void removeProductDesc(Long id);
	
	public Long addProduct(String name, String code, ProductDesc description);
	
	public Product getProduct(Long id);
	
	public List<Product> getProducts();
	
	public void removeProduct(Long id);
	
	public List<Product> getProductsByType(ProductType type);
	
	public void sendActionToProduct(Product product, Action action);

}
