/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Saroff
 */
@Entity
@Table(name="APP_PRODUCTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper=false)
public class Product implements Serializable {
    
    @Id
    @Expose
    @Column(name="PROD_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APP_PRODUCTS_SEQ")
    @SequenceGenerator(sequenceName="APP_PRODUCTS_SEQ", allocationSize=1, name="APP_PRODUCTS_SEQ")
    private Long id;
    
    @Expose
    @Column(name="PROD_NAME")
    private String name;
    
    @Expose
    @Column(name="PROD_PRICE")
    private BigDecimal price;
}
