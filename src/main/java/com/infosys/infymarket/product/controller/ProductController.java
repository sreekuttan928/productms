package com.infosys.infymarket.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.infymarket.product.dto.ProductDTO;
import com.infosys.infymarket.product.dto.SubscribedProdDTO;
import com.infosys.infymarket.product.entity.Product;
import com.infosys.infymarket.product.service.ProductService;
import com.infosys.infymarket.product.service.SubscribedProdService;
import com.infosys.infymarket.user.exception.InfyMarketException;

@RestController
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	@Autowired
	ProductService productservice;
	
	@Autowired
	SubscribedProdService subproser;
	@Autowired
	Environment environment;

	// Fetches BY PRODUCTNAME-->GET
	@GetMapping(value = "/api/product/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String productname)
			throws InfyMarketException {
		try {
			logger.info("product request with name {}", productname);
			List<ProductDTO> productDTO = productservice.getProductByName(productname);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}

	}
	//FETCH BY CATEGORY-->GET
	@GetMapping(value = "/api/products/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductBycategory(@PathVariable String category)
			throws InfyMarketException {
		try {
			logger.info("product request with category {}", category);
			List<ProductDTO> productDTO = productservice.getProductBycategory(category);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	//GET ALL PRODUCTS-->GET
	@GetMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProduct() throws InfyMarketException {
		try {
			logger.info("Fetching all products");
			List<ProductDTO> buyerDTOs = productservice.getAllProduct();
			return new ResponseEntity<>(buyerDTOs, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	//GET BY SELLERID-->GET
	@GetMapping(value = "/apis/products/{sellerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductBysellerid(@PathVariable String sellerid)
			throws InfyMarketException {
		try {
			logger.info("product request with sellerid {}", sellerid);
			List<ProductDTO> productDTO = productservice.getProductBySellerId(sellerid);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	
	//GET PRODUCT BY ID-->GET
	@GetMapping(value = "/api/verifyprod/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getByProdid(@PathVariable String prodid) throws InfyMarketException {
		logger.info("productname request for product {}", prodid);
		System.out.println("product");
		return productservice.getByProdid(prodid);
	}
	
	//UPDATE STOCK-->UPDATE
	@PutMapping(value = "/api/product/{prodid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProductStock(@Valid @RequestBody Product product, @PathVariable String prodid) {
		try {
			//ProductDTO product = new ProductDTO();
			productservice.updateProductStock(product, prodid);
			logger.info("Update product with id {}", prodid);
			String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
			return new ResponseEntity<>(successMessage, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	//GET BY BUYERID-->GET
	@GetMapping(value = "/api/subs/{buyerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProdDTO>> getByBuyerid(@PathVariable String buyerid)
			throws InfyMarketException {
		try {
			logger.info("Subscribed products for buyer {}", buyerid);
			List<SubscribedProdDTO> subscribed = subproser.getByBuyerid(buyerid);
			return new ResponseEntity<>(subscribed, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	//GET ALL SUBSCRIPTIONS-->GET
	@GetMapping(value = "/api/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProdDTO>> getAllSubProduct() throws InfyMarketException {
		try {
			logger.info("Fetching all subscribed products");
			List<SubscribedProdDTO> subscribedDto = subproser.getAllSubProduct();
			return new ResponseEntity<>(subscribedDto, HttpStatus.OK);
		} catch (Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(exception.getMessage()),
					exception);
		}
	}
	

    }

