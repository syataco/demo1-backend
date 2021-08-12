/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syataco.demo1.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="APP_BRANCHES")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id"}, callSuper=false)
public class Branch implements Serializable {
    
    @Id
    @Expose
    @Column(name="BRANCH_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APP_BRANCHES_SEQ")
    @SequenceGenerator(sequenceName="APP_BRANCHES_SEQ", allocationSize=1, name="APP_BRANCHES_SEQ")
    private Long id;
    
    @Expose
    @Column(name="BRANCH_NAME")
    private String name;
    
    @OneToMany(mappedBy="branch")
    private List<User> users;
}
