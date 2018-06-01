package com.aws.xray.demo.microservicesxraydemo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aws.xray.demo.microservicesxraydemo.repository.ProductRepository;
import com.aws.xray.demo.microservicesxraydemo.response.Product;
import com.aws.xray.demo.microservicesxraydemo.service.ProductService;
import com.aws.xray.demo.microservicesxraydemo.service.response.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	Environment environment;

	@Override
	public ProductResponse getProduct(Long id) {
		com.aws.xray.demo.microservicesxraydemo.entity.Product prod = prodRepo.getOne(id);
		ProductResponse returnProd = new ProductResponse();
		BeanUtils.copyProperties(prod, returnProd);
		String deployedPort = (deployedPort = environment.getProperty("server.port")) == null ? deployedPort
				: environment.getProperty("local.server.port");
		returnProd.setPort(Integer.parseInt(deployedPort));
		return returnProd;
	}

	@Override
	public Long saveProduct(Product product) {
		com.aws.xray.demo.microservicesxraydemo.entity.Product prod = new com.aws.xray.demo.microservicesxraydemo.entity.Product();
		BeanUtils.copyProperties(product, prod);
		prod = prodRepo.save(prod);
		return prod.getId();
	}

	private com.aws.xray.demo.microservicesxraydemo.entity.Product checkifProductPresent(Product product) {
		List<com.aws.xray.demo.microservicesxraydemo.entity.Product> existingProducts = prodRepo.findAll();
		for (com.aws.xray.demo.microservicesxraydemo.entity.Product prd : existingProducts) {
			if (null != product.getProductName() && prd.getProductName().equalsIgnoreCase(product.getProductName())
					&& null != product.getProductType()
					&& prd.getProductType().equalsIgnoreCase(product.getProductType()))
				return prd;
			String deployedPort = (deployedPort = environment.getProperty("server.port")) == null ? deployedPort
					: environment.getProperty("local.server.port");
			prd.setPort(Integer.parseInt(deployedPort));
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Product product) {
		com.aws.xray.demo.microservicesxraydemo.entity.Product prod = checkifProductPresent(product);
		if (null != prod) {
			prodRepo.delete(prod);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductResponse> listAllProducts() {
		//List<ProductResponse> prods = new ArrayList<ProductResponse>();
		List<com.aws.xray.demo.microservicesxraydemo.entity.Product> products = prodRepo.findAll();
		List<ProductResponse> prods = products.stream().map(temp -> {
			ProductResponse obj = new ProductResponse();
			obj.setProductId(String.valueOf(temp.getId()));
			obj.setProductName(temp.getProductName());
			obj.setProductType(temp.getProductType());
			return obj;
		}).collect(Collectors.toList());
		return prods;
	}
	
	@Override
	public ProductResponse getProduct(String id) {
		ProductResponse response = null;
		if (!StringUtils.isEmpty(id)) {
			com.aws.xray.demo.microservicesxraydemo.entity.Product prd = prodRepo.getOne(Long.parseLong(id));
			response = new ProductResponse();
			BeanUtils.copyProperties(prd, response);
			response.setProductId(String.valueOf(prd.getId()));
		}
		return response;
	}

}
