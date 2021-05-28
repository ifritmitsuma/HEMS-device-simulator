package pt.ulisboa.tecnico.ai.hems.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pt.ulisboa.tecnico.ai.hems.ext.ProductAction;
import pt.ulisboa.tecnico.ai.hems.model.Action;
import pt.ulisboa.tecnico.ai.hems.model.Product;
import pt.ulisboa.tecnico.ai.hems.model.ProductDesc;
import pt.ulisboa.tecnico.ai.hems.model.ProductType;
import pt.ulisboa.tecnico.ai.hems.model.State;
import pt.ulisboa.tecnico.ai.hems.repository.ActionRepository;
import pt.ulisboa.tecnico.ai.hems.repository.ProductDescRepository;
import pt.ulisboa.tecnico.ai.hems.repository.ProductRepository;
import pt.ulisboa.tecnico.ai.hems.repository.ProductTypeRepository;
import pt.ulisboa.tecnico.ai.hems.repository.StateRepository;
import pt.ulisboa.tecnico.ai.hems.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${hems_ds.consumption_fluctuation:0.15}")
	private double consumptionFluctuation;

	@Autowired
	private ProductTypeRepository ptRepository;

	@Autowired
	private ProductDescRepository pdRepository;

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public Long addProductType(String name) {
		ProductType type = new ProductType();
		type.setType(name);
		type = ptRepository.save(type);
		return type.getId();
	}

	@Override
	public void removeProductType(Long id) {
		ptRepository.deleteById(id);
	}

	@Override
	public List<ProductType> getProductTypes() {
		return ptRepository.findAll();
	}

	@Override
	public Long addProductDesc(String description, String icon, ProductType type, Double rating) {
		ProductDesc desc = new ProductDesc();
		desc.setDescription(description);
		desc.setIcon(icon);
		desc.setType(type);
		desc.setRating(new BigDecimal(rating));
		desc = pdRepository.save(desc);
		return desc.getId();
	}

	@Override
	public ProductDesc getProductDesc(Long id) {
		return pdRepository.findById(id).get();
	}

	@Override
	public List<ProductDesc> getProductDescs() {
		return pdRepository.findAll();
	}

	@Override
	public void removeProductDesc(Long id) {
		pdRepository.deleteById(id);
	}

	@Override
	public Long addProduct(String name, String code, ProductDesc description) {
		Product product = new Product();
		product.setName(name);
		product.setCode(code);
		product.setDescription(description);
		product = prodRepository.save(product);
		return product.getId();
	}

	@Override
	public Product getProduct(Long id) {
		return prodRepository.findById(id).get();
	}

	@Override
	public List<Product> getProducts() {
		return prodRepository.findAll();
	}

	@Override
	public void removeProduct(Long id) {
		prodRepository.deleteById(id);
	}

	@Override
	public List<Product> getProductsByType(ProductType type) {

		List<ProductDesc> desc = pdRepository.findByType(type);

		return prodRepository.findByDescriptionIn(desc);
	}

	@Override
	public void sendActionToProduct(Product product, Action action) {

		if (action != null) {
			product = prodRepository.findById(product.getId()).get();

			product.setState(action.getStateAfter());
			// product.setConsumption(product.getState().getValue().equals("OFF") ?
			// BigDecimal.ZERO : product.getDescription().getRating());

			prodRepository.save(product);
		} else {
			action = actionRepository.findByStateAfter(product.getState());
		}
		
		jmsTemplate.convertAndSend("products", product.createProductAction(action));
	}

	@JmsListener(destination = "products_out", containerFactory = "jmsFactory")
	public void receiveProductNotification(ProductAction productAction) {

		Product product = prodRepository.findByCode(productAction.getCode());

		Action action = actionRepository.findByCode(productAction.getAction());

		product.setState(action.getStateAfter());
		product.setConsumption(
				product.getState().getValue().equals("OFF") ? BigDecimal.ZERO : product.getDescription().getRating());

		prodRepository.save(product);

	}

	@Override
	public void addProduct(String name, String type, String desc, Double rating, String code, String state)
			throws Exception {

		ProductType pType = ptRepository.findByType(type);

		if (pType == null) {
			pType = new ProductType();
			pType.setType(type);
			pType = ptRepository.save(pType);
		}

		ProductDesc pDesc = pdRepository.findByDescription(desc);

		if (pDesc == null) {
			pDesc = new ProductDesc();
			pDesc.setDescription(desc);
		}

		pDesc.setRating(new BigDecimal(rating));
		pDesc.setType(pType);
		pDesc = pdRepository.save(pDesc);

		State stateData = stateRepository.findByValue(state);

		if (state == null) {
			throw new Exception("Invalid State " + stateData);
		}

		Product prod = prodRepository.findByCode(code);

		if (prod == null) {
			prod = new Product();
		}

		prod.setDescription(pDesc);
		prod.setCode(code);
		prod.setName(name);
		prod.setConsumption(stateData.getValue().equals("OFF") ? BigDecimal.ZERO : new BigDecimal(rating));
		prod.setState(stateData);
		prodRepository.save(prod);

	}

	@Scheduled(fixedRateString = "${hems_ds.update_interval:3000}")
	public void updateProductStatus() {

		for (Product prod : getProducts()) {
			double max = prod.getDescription().getRating().doubleValue();
			double min = max - max * consumptionFluctuation;
			prod.setConsumption(new BigDecimal(min + Math.random() * (max - min)));
			prodRepository.save(prod);
			sendActionToProduct(prod, actionRepository.findByStateAfter(prod.getState()));
		}

	}

}
