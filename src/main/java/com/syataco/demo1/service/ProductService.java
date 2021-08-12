/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.service;

import com.syataco.demo1.bean.ProductBean;
import com.syataco.demo1.model.Product;
import com.syataco.demo1.repository.ProductRepository;
import com.syataco.demo1.utils.ProductUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saroff
 */
@Service
@Transactional
@Log4j2
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Product createProduct(ProductBean productBean) {
        if (productBean != null) {
            Product product = new Product();
            product.setName(productBean.getName());
            product.setPrice(productBean.getPrice());
            product = productRepository.save(product);
            log.info("An product whit id={} was created", product.getId());
            return product;
        }
        log.error("Cannot create a product");
        return null;        
    }
    
    public ProductBean getProductById(Long id) {
        if (id != null) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                ProductBean productBean = new ProductBean();
                productBean.setId(optionalProduct.get().getId());
                productBean.setName(optionalProduct.get().getName());
                productBean.setPrice(optionalProduct.get().getPrice());
                return productBean;
            }
        }
        return null;
    }
    
    public List<ProductBean> getAllProducts() {
        List<ProductBean> productBeanList = new ArrayList<>();
        productRepository.findAll().forEach(
                element -> productBeanList.add(ProductUtils.toBean(element))
        );
        return productBeanList;
    }
    
    public ProductBean updateProduct(Long id, ProductBean productBean) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productBean.getName());
            product.setPrice(productBean.getPrice());
            product = productRepository.save(product);
            return ProductUtils.toBean(product);
        }
        return null;
    }
    
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        }
    }
}
