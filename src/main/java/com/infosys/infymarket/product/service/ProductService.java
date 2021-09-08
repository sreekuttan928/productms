package com.infosys.infymarket.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.infymarket.product.dto.ProductDTO;
import com.infosys.infymarket.product.entity.Product;
import com.infosys.infymarket.product.repository.ProductRepository;
import com.infosys.infymarket.product.repository.SubscribedProdRepository;
import com.infosys.infymarket.user.exception.InfyMarketException;

@Service
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productrepo;
	
	@Autowired
	SubscribedProdRepository subscribedprodrepo;

	// Fetches BYPRODUCTNAME
	public List<ProductDTO> getProductByName(@PathVariable String productname) throws InfyMarketException {

		logger.info("Product request with name {}", productname);

		Iterable<Product> product = productrepo.findByproductname(productname);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new InfyMarketException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}
	//GET BY CATEGORY
	public List<ProductDTO> getProductBycategory(@PathVariable String category) throws InfyMarketException {
		logger.info("Product request of category {}", category);
		Iterable<Product> product = productrepo.findBycategory(category);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new InfyMarketException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}
	//GER BY SELLERID
	public List<ProductDTO> getProductBySellerId(@PathVariable String sellerid) throws InfyMarketException {
		logger.info("Product request of seller {}", sellerid);
		Iterable<Product> product = productrepo.findBysellerid(sellerid);
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();

		product.forEach(Pro -> {
			productDTO.add(ProductDTO.valueOf(Pro));
		});
		if (productDTO.isEmpty())
			throw new InfyMarketException("Service.PRODUCTS_NOT_FOUND");
		return productDTO;
	}
	//ALL PRODUCTS
	public List<ProductDTO> getAllProduct() throws InfyMarketException {

		Iterable<Product> products = productrepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();

		products.forEach(product -> {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		});
		if (productDTOs.isEmpty())
			throw new InfyMarketException("Service.PRODUCTS_NOT_FOUND");
		logger.info("Product Details : {}", productDTOs);
		return productDTOs;
	}
	//UPDATION OF STOCK
	 public Product updateProductStock(Product product, String prodid) throws InfyMarketException{
	        Product existingProduct = productrepo.findById(prodid).orElse(null);
	        if(existingProduct != null) {
	            existingProduct.setStock(product.getStock());
	            return productrepo.save(existingProduct); 
	        }
	        return null;
	    }
	// GET BY PRODID
	public ProductDTO getByProdid(String prodid) throws InfyMarketException{
		logger.info("Profile request for customer {}", prodid);
		Product pro = productrepo.findByProdid(prodid);
		ProductDTO proDTO = ProductDTO.valueOf(pro);

		logger.info("Profile for customer : {}", proDTO);
		return proDTO;
	}
}
