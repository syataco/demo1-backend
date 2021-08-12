/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.utils;

import com.syataco.demo1.bean.ProductBean;
import com.syataco.demo1.model.Product;

/**
 *
 * @author Saroff
 */
public class ProductUtils {
    
    public static ProductBean toBean(Product product) {
        if (product != null) {
            ProductBean productBean = new ProductBean();
            productBean.setId(product.getId());
            productBean.setName(product.getName());
            productBean.setPrice(product.getPrice());
            return productBean;
        }
        return null;
    }
}
