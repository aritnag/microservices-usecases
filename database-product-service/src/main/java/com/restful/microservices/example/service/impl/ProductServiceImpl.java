package com.restful.microservices.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.microservices.example.repository.ProductRepository;
import com.restful.microservices.example.response.Product;
import com.restful.microservices.example.service.ProductService;
import com.restful.microservices.example.service.response.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public ProductResponse getProduct(Long id) {
		com.restful.microservices.example.entity.Product prod = prodRepo.getOne(id);
		ProductResponse returnProd = new ProductResponse();
		BeanUtils.copyProperties(prod, returnProd);
		return returnProd;
	}

	@Override
	public Long saveProduct(Product product) {
		com.restful.microservices.example.entity.Product prod = new com.restful.microservices.example.entity.Product();
		BeanUtils.copyProperties(product, prod);
		prod = prodRepo.save(prod);
		return prod.getId();
	}

	private com.restful.microservices.example.entity.Product checkifProductPresent(Product product) {
		List<com.restful.microservices.example.entity.Product> existingProducts=prodRepo.findAll();
		for(com.restful.microservices.example.entity.Product prd:existingProducts){
			if(null!=product.getProductName() &&
					prd.getProductName().equalsIgnoreCase(product.getProductName()) &&
					null!=product.getProductType() 
					&& prd.getProductType().equalsIgnoreCase(product.getProductType()))
				return prd;
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Product product) {
		com.restful.microservices.example.entity.Product prod = checkifProductPresent(product);
		if (null!=prod) {
			prodRepo.delete(prod);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductResponse> listAllProducts() {
		List<ProductResponse> prods = new ArrayList<ProductResponse>();
		List<com.restful.microservices.example.entity.Product> products = prodRepo.findAll();
		for (com.restful.microservices.example.entity.Product prd : products) {
			ProductResponse prod = new ProductResponse();
			BeanUtils.copyProperties(prd, prod);
			prods.add(prod);
		}
		return prods;
	}

}
 