/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.controller;

import com.google.gson.GsonBuilder;
import com.syataco.demo1.bean.ProductBean;
import com.syataco.demo1.model.Product;
import com.syataco.demo1.service.ProductService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Saroff
 */
@RestController
@Log4j2
@RequestMapping("/service/product")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private GsonBuilder gsonBuilder;
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody ProductBean productBean) {
        try {
            if (productBean != null) {
                Product product = productService.createProduct(productBean);
                return new ResponseEntity<>(gsonBuilder.create().toJson(product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<String> getProduct(@PathVariable("id") Long id) {
        try {
            ProductBean productBean = productService.getProductById(id);
            if (productBean != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(productBean), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ResponseEntity<String> getAllProducts() {
        try {
            List<ProductBean> productBeanList = productService.getAllProducts();
            if (!productBeanList.isEmpty()) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(productBeanList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@RequestBody ProductBean productBean, @PathVariable("id") Long id) {
        try {
            ProductBean productBeanResponse = productService.updateProduct(id, productBean);
            if (productBeanResponse != null) {
                return new ResponseEntity<>(gsonBuilder.create().toJson(productBeanResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            if (id != null) {
                productService.deleteProduct(id);
                return new ResponseEntity<>(gsonBuilder.create().toJson("Delete successful"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
